package nl.novi.FaunaFinder.controllers;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nl.novi.FaunaFinder.dtos.input.UserInputDto;
import nl.novi.FaunaFinder.dtos.output.AuthenticationResponse;
import nl.novi.FaunaFinder.models.User;
import nl.novi.FaunaFinder.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register( @RequestBody UserInputDto request ) throws Exception {
        try {
            return ResponseEntity.ok(authService.register(request));
        }
        catch (Exception e) {
            //TODO return AuthenticationFailedException;
            throw new Exception(e.getCause());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserInputDto request) throws Exception {


        try {
            return ResponseEntity.ok(authService.authenticate(request));
        }
        catch(Exception e) {
           //TODO return AuthenticationFailedException;
            throw new Exception(e.getCause());
        }
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<Object> refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            return ResponseEntity.ok(authService.refreshToken(request, response));
        }
        catch (Exception e) {
            //TODO return AuthenticationFailedException;
            throw new Exception(e.getCause());
        }
    }
}