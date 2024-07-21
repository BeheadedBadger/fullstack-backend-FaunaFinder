package nl.novi.FaunaFinder.dtos.mapper;
import nl.novi.FaunaFinder.dtos.input.AnimalInputDto;
import nl.novi.FaunaFinder.dtos.output.AnimalOutputDto;
import nl.novi.FaunaFinder.models.Animal;

public class AnimalMapper {
    public static Animal fromInputDtoToModel (AnimalInputDto inputDto) {
        Animal model = new Animal();
        model.setName(inputDto.name);
        model.setAge(inputDto.age);
        model.setSex(inputDto.sex);
        model.setCommonSpeciesName(inputDto.commonSpeciesName);
        model.setScientificSpeciesName(inputDto.scientificSpeciesName);
        model.setWarning(inputDto.warning);
        model.setWarningExplanation(inputDto.warningExplanation);
        model.setDescription(inputDto.description);

        return model;
    }

    public static AnimalOutputDto fromModelToOutputDto (Animal model) {
        AnimalOutputDto outputDto = new AnimalOutputDto();
        outputDto.setId(model.getId());
        outputDto.setName(model.getName());
        outputDto.setAge(model.getAge());
        outputDto.setSex(model.getSex());
        outputDto.setCommonSpeciesName(model.getCommonSpeciesName());
        outputDto.setScientificSpeciesName(model.getScientificSpeciesName());
        outputDto.setWarning(model.getWarning());
        outputDto.setWarningExplanation(model.getWarningExplanation());
        outputDto.setDescription(model.getDescription());
        outputDto.setShelter(model.getShelter().getUsername());

        return outputDto;
    }
}
