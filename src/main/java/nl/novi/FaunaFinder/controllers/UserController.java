package nl.novi.FaunaFinder.controllers;
import jakarta.servlet.http.HttpServletRequest;
import nl.novi.FaunaFinder.dtos.mapper.UserMapper;
import nl.novi.FaunaFinder.dtos.output.UserOutputDto;
import nl.novi.FaunaFinder.models.User;
import nl.novi.FaunaFinder.service.ImageService;
import nl.novi.FaunaFinder.service.UserService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ImageService imageService;

    public UserController(UserService userService, ImageService imageService) {
        this.userService = userService;
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<List<UserOutputDto>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserOutputDto> getUser(@PathVariable("username") String username) {
        UserOutputDto user = userService.get(username);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/{username}/photo")
    public ResponseEntity<User> addPhotoToUser(@PathVariable("username") String id, @RequestBody MultipartFile file) throws Exception {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/users/")
                .path(id)
                .path("/photo")
                .toString();
        String fileName = imageService.storeFile(id, file);
        User user = userService.assignPhotoToUser(fileName, id);
        return ResponseEntity.created(URI.create(url)).body(user);
    }

    @GetMapping("/{id}/photo")
    public ResponseEntity<Resource> getPhoto(@PathVariable("id") String username, HttpServletRequest request) throws Exception {
        Resource resource = userService.getImage(username);

        String mimeType;

        try{
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename())
                .body(resource);
    }

    @PutMapping("/{id}/{animalId}")
    public ResponseEntity<UserOutputDto> addAnimalToShelter(@PathVariable("id") String id, @PathVariable("animalId") Long animalId) throws Exception {
        Optional<User> shelter = userService.assignAnimalToShelter(id, animalId);
        if (shelter.isPresent()) {
            UserOutputDto outputDto = UserMapper.fromModelToOutputDto(shelter.get());
            return ResponseEntity.ok().body(outputDto);
        }

        throw new Exception("Failed to add animal to shelter");
    }

    @PutMapping("fav/{id}/{animalId}")
    public ResponseEntity<UserOutputDto> addAnimalToFavs(@PathVariable("id") String id, @PathVariable("animalId") Long animalId) throws Exception {
        Optional<User> user = userService.assignAnimalToFavourites(id, animalId);
        if (user.isPresent()) {
            UserOutputDto outputDto = UserMapper.fromModelToOutputDto(user.get());
            return ResponseEntity.ok().body(outputDto);
        }

        throw new Exception("Failed to add animal to favourites");
    }

    @DeleteMapping("fav/{id}/{animalId}")
    public ResponseEntity<UserOutputDto> removeAnimalFromFavs(@PathVariable("id") String id, @PathVariable("animalId") Long animalId) throws Exception {
        Optional<User> user = userService.removeAnimalFromFavourites(id, animalId);
        if (user.isPresent()) {
            UserOutputDto outputDto = UserMapper.fromModelToOutputDto(user.get());
            return ResponseEntity.ok().body(outputDto);
        }
        throw new Exception("Failed to remove animal from favourites");
    }
}
