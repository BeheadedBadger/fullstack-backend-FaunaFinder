package nl.novi.FaunaFinder.service;
import nl.novi.FaunaFinder.dtos.input.UserInputDto;
import nl.novi.FaunaFinder.dtos.mapper.UserMapper;
import nl.novi.FaunaFinder.dtos.output.AuthenticationResponse;
import nl.novi.FaunaFinder.exceptions.TokenGenerationFailedException;
import nl.novi.FaunaFinder.models.Token;
import nl.novi.FaunaFinder.models.User;
import nl.novi.FaunaFinder.repositories.FileUploadRepository;
import nl.novi.FaunaFinder.repositories.TokenRepository;
import nl.novi.FaunaFinder.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final ImageService imgService;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthenticationService(UserRepository repository, FileUploadRepository fileRepo, ImageService imgService,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 TokenRepository tokenRepository, AuthenticationManager authenticationManager, UserService userService) {
        this.repository = repository;
        this.imgService = imgService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    public AuthenticationResponse register(UserInputDto request) throws Exception {
        User user = UserMapper.fromInputDtoToModel(request);

        // check if user already exist. if exist than authenticate the user
        if (repository.findByUsername(user.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, null, "User already exist");
        }

        user.setPassword(passwordEncoder.encode(request.password));
        user.setRole(request.role);

        user = repository.save(user);
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        saveUserToken(accessToken, refreshToken, user);

        return new AuthenticationResponse(accessToken, refreshToken, "User registration was successful");
    }

    public AuthenticationResponse authenticate(UserInputDto request) {
        Optional<User> user;

        authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.username,
                            request.password
                    ));

        try {
            user = repository.findByUsername(request.username);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }

        if (user.isPresent()) {
            try {
                String accessToken = jwtService.generateAccessToken(user.get());
                String refreshToken = jwtService.generateRefreshToken(user.get());

                revokeAllTokenByUser(user.get());
                saveUserToken(accessToken, refreshToken, user.get());

                return new AuthenticationResponse(accessToken, refreshToken, "User login was successful");
            }
            catch(TokenGenerationFailedException e){
                throw new TokenGenerationFailedException(e.getCause());
            }
        }
        return null;
    }

    private void revokeAllTokenByUser(User user) {
        List<Token> validTokens = tokenRepository.findAllAccessTokensByUser(user.getUsername());
        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }
    private void saveUserToken(String accessToken, String refreshToken, User user) {
        Token token = new Token();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }

    public ResponseEntity<Object> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String token = authHeader.substring(7);

        // extract username from token
        String username = jwtService.extractUsername(token);

        // check if the user exist in database
        User user = repository.findByUsername(username)
                .orElseThrow(()->new RuntimeException("No user found"));

        // check if the token is valid
        if(jwtService.isValidRefreshToken(token, user)) {
            // generate access token
            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            revokeAllTokenByUser(user);
            saveUserToken(accessToken, refreshToken, user);

            return new ResponseEntity<>(new AuthenticationResponse(accessToken, refreshToken, "New token generated"), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
