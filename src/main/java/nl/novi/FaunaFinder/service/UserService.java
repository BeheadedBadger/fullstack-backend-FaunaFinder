package nl.novi.FaunaFinder.service;
import nl.novi.FaunaFinder.dtos.mapper.UserMapper;
import nl.novi.FaunaFinder.dtos.output.UserOutputDto;
import nl.novi.FaunaFinder.models.User;
import nl.novi.FaunaFinder.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository userRepository) {
        this.repo = userRepository;
    }

    public UserOutputDto getUserByUsername(String username) throws UsernameNotFoundException {
        UserOutputDto outputDto = new UserOutputDto();
        Optional<User> u = repo.findById(Long.valueOf(username));
        if (u.isPresent()){
            outputDto = UserMapper.fromModelToOutputDto(u.get());
        }else {
            throw new UsernameNotFoundException(username);
        }
        return outputDto;
    }

    public UserOutputDto GetUserByID(long id) throws Exception {
        Optional<User> t = repo.findById(id);
        if (t.isPresent()) {
            return UserMapper.fromModelToOutputDto(t.get());
        } else {
            //TODO //throw new UserNotFoundException();
            throw new Exception();
        }
    }
}
