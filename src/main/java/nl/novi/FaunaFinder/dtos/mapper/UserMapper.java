package nl.novi.FaunaFinder.dtos.mapper;

import nl.novi.FaunaFinder.dtos.input.UserInputDto;
import nl.novi.FaunaFinder.dtos.output.UserOutputDto;
import nl.novi.FaunaFinder.models.User;

public class UserMapper {

    public static User fromInputDtoToModel(UserInputDto inputDto) {
        User model = new User();
        model.setUsername(inputDto.username);
        model.setPassword(inputDto.password);
        return model;
    }

    public static UserOutputDto fromModelToOutputDto(User model) {
        UserOutputDto outputDto = new UserOutputDto();
        outputDto.setUsername(model.getUsername());
        outputDto.setPassword(model.getPassword());
        outputDto.setAuthorities(model.getAuthorities());
        outputDto.setFavouriteAnimals(model.getFavouriteAnimals());
        outputDto.setDonations(model.getDonations());
        return outputDto;
    }
}
