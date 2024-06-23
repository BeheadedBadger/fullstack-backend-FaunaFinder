package nl.novi.FaunaFinder.dtos.mapper;

import nl.novi.FaunaFinder.dtos.input.DonationInputDto;
import nl.novi.FaunaFinder.dtos.output.DonationOutputDto;
import nl.novi.FaunaFinder.models.Donation;

public class DonationMapper {

    public static Donation fromInputToModel(DonationInputDto inputDto) {
        Donation model = new Donation();
        model.setAmount(inputDto.amount);

        return model;
    }

    public static DonationOutputDto fromModelToOutputDto (Donation model) {
        DonationOutputDto outputDto = new DonationOutputDto();
        outputDto.setId(model.getId());
        outputDto.setAmount(model.getAmount());

        return outputDto;
    }
}
