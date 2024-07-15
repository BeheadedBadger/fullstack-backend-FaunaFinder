package nl.novi.FaunaFinder.service;

import nl.novi.FaunaFinder.models.User;
import nl.novi.FaunaFinder.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepos;

    public CustomUserDetailService(UserRepository repos) {
        this.userRepos = repos;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> ou = userRepos.findByUsername(username);
        if (ou.isPresent()) {
            return ou.get();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
