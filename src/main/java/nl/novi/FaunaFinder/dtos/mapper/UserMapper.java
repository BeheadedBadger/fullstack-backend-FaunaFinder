package nl.novi.FaunaFinder.dtos.mapper;
import nl.novi.FaunaFinder.dtos.input.UserInputDto;
import nl.novi.FaunaFinder.dtos.output.UserOutputDto;
import nl.novi.FaunaFinder.models.Role;
import nl.novi.FaunaFinder.models.User;
import nl.novi.FaunaFinder.service.ImageService;

public class UserMapper {

    public static User fromInputDtoToModel(UserInputDto inputDto) {
        User model = new User();
        model.setUsername(inputDto.username);
        model.setPassword(inputDto.password);
        model.setRole(inputDto.role);
        model.setSpeciality(inputDto.speciality);
        return model;
    }

    public static UserOutputDto fromModelToOutputDto(User model) {
        UserOutputDto outputDto = new UserOutputDto();
        outputDto.setId(model.getId());
        outputDto.setUsername(model.getUsername());
        outputDto.setRole(model.getRole());
        outputDto.setFavouriteAnimals(model.getFavouriteAnimals());
        outputDto.setDonations(model.getDonations());
        outputDto.setSpeciality(model.getSpeciality());
        outputDto.setShelterAnimals(model.getShelterAnimals());
        if (model.getUserPhoto() != null) {
            outputDto.setUserPhoto(model.getUserPhoto().getFileName());
        }
        return outputDto;
    }
}
