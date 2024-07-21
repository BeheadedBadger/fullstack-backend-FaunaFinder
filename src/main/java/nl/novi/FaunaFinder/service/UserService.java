package nl.novi.FaunaFinder.service;
import nl.novi.FaunaFinder.dtos.mapper.UserMapper;
import nl.novi.FaunaFinder.dtos.output.UserOutputDto;
import nl.novi.FaunaFinder.models.Animal;
import nl.novi.FaunaFinder.models.Image;
import nl.novi.FaunaFinder.models.User;
import nl.novi.FaunaFinder.repositories.FileUploadRepository;
import nl.novi.FaunaFinder.repositories.UserRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repo;
    private final FileUploadRepository fileRepo;
    private final ImageService imgService;

    public UserService(UserRepository repository, FileUploadRepository fileRepo, ImageService imgService) {
        this.repo = repository;
        this.fileRepo = fileRepo;
        this.imgService = imgService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserOutputDto get(String username) {
        Optional<User> model = repo.findByUsername(username);
        if (model.isPresent()) {
            return UserMapper.fromModelToOutputDto(model.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public User assignPhotoToUser(String fileName, String id) throws Exception {
        Optional<User> user = repo.findByUsername(id);
        Optional<Image> image = fileRepo.findById(fileName);
        if (image.isPresent() && user.isPresent()) {
            (user.get()).setUserPhoto(image.get());
            fileRepo.save(image.get());
            repo.save(user.get());
            return user.get();
        }
        return null;
    }

    public Resource getImage(String username) throws Exception {
            Optional<User> optionalUser = repo.findByUsername(username);
            if(optionalUser.isEmpty()){
                throw new UsernameNotFoundException("Can't find user with username " + username);
            }
            Image photo = optionalUser.get().getUserPhoto();
            if(photo == null){
                throw new Exception("No photo found");
            }
            return imgService.downLoadFile(photo.getFileName());
        }
}