package nl.novi.FaunaFinder.dtos.mapper;

import nl.novi.FaunaFinder.dtos.input.ShelterInputDto;
import nl.novi.FaunaFinder.dtos.output.ShelterOutputDto;
import nl.novi.FaunaFinder.models.Shelter;

public class ShelterMapper {

    public static Shelter fromInputDtoToModel (ShelterInputDto inputDto) {
        Shelter model = new Shelter();
        model.setUsername(inputDto.username);
        model.setPassword(inputDto.password);
        model.setName(inputDto.name);
        model.setSpeciality(inputDto.speciality);
        model.setCity(inputDto.city);
        model.setPostalCode(inputDto.postalCode);
        model.setAddress(inputDto.address);
        model.setPhoneNumber(inputDto.phoneNumber);
        return model;
    }

    public static ShelterOutputDto fromModelToOutputDto (Shelter model) {
        ShelterOutputDto outputDto = new ShelterOutputDto();
        outputDto.setUsername(model.getUsername());
        outputDto.setPassword(model.getPassword());
        outputDto.setName(model.getName());
        outputDto.setSpeciality(model.getSpeciality());
        outputDto.setCity(model.getCity());
        outputDto.setPostalCode(model.getPostalCode());
        outputDto.setAddress(model.getAddress());
        outputDto.setPhoneNumber(model.getPhoneNumber());
        outputDto.setAnimals(model.getAnimals());
        outputDto.setDonations(model.getDonations());
        return outputDto;
    }
}
