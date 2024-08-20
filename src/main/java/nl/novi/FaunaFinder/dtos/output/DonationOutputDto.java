package nl.novi.FaunaFinder.dtos.output;

import lombok.Getter;
import lombok.Setter;
import nl.novi.FaunaFinder.models.User;

@Setter
@Getter
public class DonationOutputDto {
    Long id;
    int amount;
    String frequency;
    User shelter;
    User user;

}
