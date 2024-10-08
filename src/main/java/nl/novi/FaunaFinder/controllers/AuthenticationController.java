package nl.novi.FaunaFinder.controllers;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nl.novi.FaunaFinder.dtos.input.UserInputDto;
import nl.novi.FaunaFinder.dtos.output.AuthenticationResponse;
import nl.novi.FaunaFinder.exceptions.AuthenticationFailedException;
import nl.novi.FaunaFinder.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserInputDto request ) throws Exception {
        try {
            return ResponseEntity.ok(authService.register(request));
        }
        catch(AuthenticationFailedException e) {
            throw new AuthenticationFailedException(e.getCause());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserInputDto request) throws AuthenticationFailedException {

        try {
            return ResponseEntity.ok(authService.authenticate(request));
        }
        catch(AuthenticationFailedException e) {
            throw new AuthenticationFailedException(e.getCause());
        }
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<Object> refreshToken(HttpServletRequest request, HttpServletResponse response) throws AuthenticationFailedException {
        try {
            return ResponseEntity.ok(authService.refreshToken(request, response));
        }
        catch(AuthenticationFailedException e) {
            throw new AuthenticationFailedException(e.getCause());
        }
    }
}