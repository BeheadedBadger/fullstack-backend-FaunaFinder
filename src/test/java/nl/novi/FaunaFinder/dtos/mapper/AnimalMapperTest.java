package nl.novi.FaunaFinder.dtos.mapper;

import nl.novi.FaunaFinder.dtos.input.AnimalInputDto;
import nl.novi.FaunaFinder.dtos.output.AnimalOutputDto;
import nl.novi.FaunaFinder.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalMapperTest {

    AnimalInputDto unmappedAnimalInputDto = new AnimalInputDto();
    Animal unmappedAnimalModel = new Animal();
    User shelter = new User();

    @BeforeEach
    void setUp() {
        shelter.setUsername("testShelter");
        shelter.setPassword("fakePassword");
        shelter.setRole(Role.SHELTER);
        shelter.setSpeciality("Mammal");

        unmappedAnimalInputDto.name = "TestAnimal";
        unmappedAnimalInputDto.age = 3;
        unmappedAnimalInputDto.commonSpeciesName = "Cat";
        unmappedAnimalInputDto.scientificSpeciesName = "Felis Cattus";
        unmappedAnimalInputDto.category = "Mammal";
        unmappedAnimalInputDto.sex = Sex.F;
        unmappedAnimalInputDto.shelter = shelter;

        unmappedAnimalModel.setName("TestModel");
        unmappedAnimalModel.setAge(33);
        unmappedAnimalModel.setCommonSpeciesName("Royal Python");
        unmappedAnimalModel.setScientificSpeciesName("Pyhton Regius");
        unmappedAnimalModel.setSpeciesCategory("Reptile");
        unmappedAnimalModel.setSex(Sex.U);
        unmappedAnimalModel.setWarning(true);
        unmappedAnimalModel.setShelter(shelter);
    }

    @AfterEach
    void tearDown() {
        unmappedAnimalInputDto = null;
        unmappedAnimalModel = null;
        shelter = null;
    }

    @Test
    @DisplayName("From AnimalInputDto to Animal model")
    public void fromInputDtoToModel() {
        Animal animalModel = AnimalMapper.fromInputDtoToModel(unmappedAnimalInputDto);
        assertEquals(3, animalModel.getAge());
        assertEquals("TestAnimal", animalModel.getName());
        assertEquals(Sex.F, animalModel.getSex());
        assertEquals("Mammal", animalModel.getSpeciesCategory());
        assertEquals(Role.SHELTER, animalModel.getShelter().getRole());
    }

    @Test
    @DisplayName("From Animal model to AnimalOutputDto")
    public void fromModelToOutputDto() {
        AnimalOutputDto animalOutputDto = AnimalMapper.fromModelToOutputDto(unmappedAnimalModel);
        assertEquals(33, animalOutputDto.getAge());
        assertEquals("TestModel", animalOutputDto.getName());
        assertEquals(Sex.U, animalOutputDto.getSex());
        assertEquals(true, animalOutputDto.getWarning());
        assertEquals("testShelter", animalOutputDto.getShelter().getUsername());
    }
}