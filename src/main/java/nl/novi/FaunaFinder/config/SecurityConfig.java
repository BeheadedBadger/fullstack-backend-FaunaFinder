package nl.novi.FaunaFinder.config;
import nl.novi.FaunaFinder.filter.JwtAuthenticationFilter;
import nl.novi.FaunaFinder.repositories.UserRepository;
import nl.novi.FaunaFinder.service.JwtService;
import nl.novi.FaunaFinder.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userDetailsService;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final CustomLogoutHandler logoutHandler;

    private final UserRepository userRepository;

    private final JwtService jwtService;



    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
                          CustomLogoutHandler logoutHandler, UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.userDetailsService = new UserService(this.userRepository);
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.logoutHandler = logoutHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/animals").permitAll()
                        .requestMatchers(HttpMethod.GET, "/animals/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "users/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/animals").hasAuthority("SHELTER")
                        .requestMatchers(HttpMethod.DELETE, "/animals/**").hasAnyAuthority("ADMIN", "SHELTER")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/**").authenticated()
                        .anyRequest().denyAll()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(new JwtAuthenticationFilter(jwtService, userDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(userDetailsService);
        return new ProviderManager(auth);
    }
}
