package nl.novi.FaunaFinder.controllers;
import nl.novi.FaunaFinder.dtos.output.UserOutputDto;
import nl.novi.FaunaFinder.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserOutputDto> getUser(@PathVariable("username") String username) {
        UserOutputDto user = userService.get(username);
        return ResponseEntity.ok().body(user);
    }

    //TODO edit user
    //TODO delete user
    //TODO upgrade user roles
}
