package nl.novi.FaunaFinder.controllers;
import nl.novi.FaunaFinder.dtos.output.AuthenticationResponse;
import nl.novi.FaunaFinder.models.Authority;
import nl.novi.FaunaFinder.service.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import nl.novi.FaunaFinder.dtos.input.AuthenticationRequest;
import nl.novi.FaunaFinder.security.JwtService;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
        private final AuthenticationManager authenticationManager;

        private final JwtService jwtService;

        private final UserDetailsService userDetailsService;


    public AuthenticationController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping()
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {


        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        }
        catch (Exception ex) { throw new Exception(ex.getCause()); }
        /*catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username or password", ex);
        }*/


        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String jwt = jwtService.generateToken((User) userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}