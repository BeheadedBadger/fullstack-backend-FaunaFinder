package nl.novi.FaunaFinder.service;

import nl.novi.FaunaFinder.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository userRepository) {
        this.repo = userRepository;
    }

}
