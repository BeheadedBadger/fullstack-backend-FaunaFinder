package nl.novi.FaunaFinder.controllers;
import nl.novi.FaunaFinder.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

}
