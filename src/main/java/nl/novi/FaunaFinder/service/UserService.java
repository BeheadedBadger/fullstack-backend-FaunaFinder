package nl.novi.FaunaFinder.service;
import nl.novi.FaunaFinder.dtos.mapper.UserMapper;
import nl.novi.FaunaFinder.dtos.output.UserOutputDto;
import nl.novi.FaunaFinder.models.User;
import nl.novi.FaunaFinder.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repo;

    public UserService(UserRepository repository) {
        this.repo = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    public UserOutputDto get(String username) {
        Optional<User> model = repo.findByUsername(username);
        if(model.isPresent()) {
            return UserMapper.fromModelToOutputDto(model.get());
        }
        else {
            throw new UsernameNotFoundException(username);
        }
    }
}
