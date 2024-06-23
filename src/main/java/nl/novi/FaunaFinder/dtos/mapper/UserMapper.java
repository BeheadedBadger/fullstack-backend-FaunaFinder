package nl.novi.FaunaFinder.dtos.mapper;

import nl.novi.FaunaFinder.dtos.input.UserInputDto;
import nl.novi.FaunaFinder.dtos.output.UserOutputDto;
import nl.novi.FaunaFinder.models.User;

public class UserMapper {

    public static User fromInputDtoToModel(UserInputDto inputDto) {
        User model = new User();
        model.setUserName(inputDto.userName);
        model.setPassword(inputDto.password);
        model.setPostalCode(inputDto.postalCode);
        return model;
    }

    public static UserOutputDto fromModelToOutputDto(User model) {
        UserOutputDto outputDto = new UserOutputDto();
        outputDto.setId(model.getId());
        outputDto.setUserName(model.getUserName());
        outputDto.setPassword(model.getPassword());
        outputDto.setPostalCode(model.getPostalCode());
        outputDto.setIsModerator(model.getIsModerator());
        outputDto.setFavouriteAnimals(model.getFavouriteAnimals());
        outputDto.setDonations(model.getDonations());
        return outputDto;
    }
}
