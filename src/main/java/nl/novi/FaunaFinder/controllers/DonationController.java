package nl.novi.FaunaFinder.controllers;
import nl.novi.FaunaFinder.dtos.input.AnimalInputDto;
import nl.novi.FaunaFinder.dtos.input.DonationInputDto;
import nl.novi.FaunaFinder.dtos.output.AnimalOutputDto;
import nl.novi.FaunaFinder.dtos.output.DonationOutputDto;
import nl.novi.FaunaFinder.exceptions.AuthenticationFailedException;
import nl.novi.FaunaFinder.service.DonationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/donate")
public class DonationController {

    private final DonationService donationService;
    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping
    public ResponseEntity<DonationOutputDto> addDonation(@RequestBody DonationInputDto inputDto) throws Exception {
        try {
            return ResponseEntity.ok(donationService.create(inputDto));
        }
        catch(AuthenticationFailedException e) {
            throw new Exception(e.getCause());
        }
    }
}
