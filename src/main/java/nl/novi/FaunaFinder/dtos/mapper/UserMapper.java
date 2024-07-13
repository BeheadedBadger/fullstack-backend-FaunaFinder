package nl.novi.FaunaFinder.dtos.mapper;

import nl.novi.FaunaFinder.dtos.input.UserInputDto;
import nl.novi.FaunaFinder.dtos.output.UserOutputDto;
import nl.novi.FaunaFinder.models.User;

import java.util.ArrayList;

public class UserMapper {

    public static User fromInputDtoToModel(UserInputDto inputDto) {
        User model = new User();
        model.setUserName(inputDto.username);
        model.setPassword(inputDto.password);
        model.setPostalCode(inputDto.postalCode);
        return model;
    }

    public static UserOutputDto fromModelToOutputDto(User model) {
        UserOutputDto outputDto = new UserOutputDto();
        outputDto.setUserName(model.getUserName());
        outputDto.setPassword(model.getPassword());
        outputDto.setPostalCode(model.getPostalCode());
        outputDto.setAuthority(model.getAuthorities());
        outputDto.setFavouriteAnimals(model.getFavouriteAnimals());
        outputDto.setDonations(model.getDonations());
        return outputDto;
    }
}
