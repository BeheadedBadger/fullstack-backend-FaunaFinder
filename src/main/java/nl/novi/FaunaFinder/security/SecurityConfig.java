package nl.novi.FaunaFinder.security;
import nl.novi.FaunaFinder.filter.JwtRequestFilter;
import nl.novi.FaunaFinder.repositories.AnimalRepository;
import nl.novi.FaunaFinder.repositories.DonationRepository;
import nl.novi.FaunaFinder.repositories.ShelterRepository;
import nl.novi.FaunaFinder.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final ShelterRepository shelterRepository;
    private final DonationRepository donationRepository;
    private final AnimalRepository animalRepository;

    public final UserDetailsService userDetailsService;

    private final JwtRequestFilter jwtRequestFilter;


    public SecurityConfig(JwtService jwtService, UserRepository userRepos, ShelterRepository shelterRepository, DonationRepository donationRepository, AnimalRepository animalRepository, UserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.jwtService = jwtService;
        this.userRepository = userRepos;
        this.shelterRepository = shelterRepository;
        this.donationRepository = donationRepository;
        this.animalRepository = animalRepository;
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    // PasswordEncoderBean. Deze kun je overal in je applicatie injecteren waar nodig.
    // Je kunt dit ook in een aparte configuratie klasse zetten.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Authentication with passwordEncoder
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(userDetailsService);
        return new ProviderManager(auth);
    }

    // Authorization met jwt
    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/animals/batch").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/animals").permitAll()
                        .requestMatchers(HttpMethod.POST, "/animals").authenticated()
                        .anyRequest().denyAll())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}