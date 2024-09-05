package nl.novi.FaunaFinder.dtos.mapper;
import nl.novi.FaunaFinder.dtos.input.DonationInputDto;
import nl.novi.FaunaFinder.dtos.output.DonationOutputDto;
import nl.novi.FaunaFinder.models.Donation;
import nl.novi.FaunaFinder.models.User;

public class DonationMapper {

    public static Donation fromInputToModel(DonationInputDto inputDto, User sender, User receiver) {
        Donation model = new Donation();
        model.setAmount(inputDto.amount);
        model.setFrequency(inputDto.frequency);
        model.setShelter(receiver);

        if (sender.getUsername() == null) {
            model.setUser(null);
        }
        else {
            model.setUser(sender);
        }

        return model;
    }

    public static DonationOutputDto fromModelToOutputDto (Donation model) {
        DonationOutputDto outputDto = new DonationOutputDto();
        outputDto.setId(model.getId());
        outputDto.setAmount(model.getAmount());
        outputDto.setFrequency(model.getFrequency());
        outputDto.setUser(model.getUser());
        outputDto.setShelter(model.getShelter());
        return outputDto;
    }
}
