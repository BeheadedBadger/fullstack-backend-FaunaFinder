package nl.novi.FaunaFinder.controllers;
import nl.novi.FaunaFinder.service.DonationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class DonationController {

    private final DonationService donationService;
    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }


}
