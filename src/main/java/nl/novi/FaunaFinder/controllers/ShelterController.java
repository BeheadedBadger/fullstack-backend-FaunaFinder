package nl.novi.FaunaFinder.controllers;
import nl.novi.FaunaFinder.service.ShelterService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShelterController {

    private final ShelterService shelterService;
    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

}
