package nl.novi.FaunaFinder.controllers;

import jakarta.persistence.*;
import nl.novi.FaunaFinder.dtos.input.AnimalInputDto;
import nl.novi.FaunaFinder.dtos.input.UserInputDto;
import nl.novi.FaunaFinder.dtos.mapper.AnimalMapper;
import nl.novi.FaunaFinder.dtos.mapper.UserMapper;
import nl.novi.FaunaFinder.dtos.output.AnimalOutputDto;
import nl.novi.FaunaFinder.dtos.output.AuthenticationResponse;
import nl.novi.FaunaFinder.models.Animal;
import nl.novi.FaunaFinder.models.Role;
import nl.novi.FaunaFinder.models.Sex;
import nl.novi.FaunaFinder.models.User;
import nl.novi.FaunaFinder.repositories.AnimalRepository;
import nl.novi.FaunaFinder.repositories.UserRepository;
import nl.novi.FaunaFinder.service.AnimalService;
import nl.novi.FaunaFinder.service.AuthenticationService;
import nl.novi.FaunaFinder.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class AnimalControllerTest {
    @Mock
    AnimalRepository animalRepository;
    @InjectMocks
    AnimalService animalService;

    @Captor
    ArgumentCaptor<Animal> captor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Submit a new animal")
    void addAnimal() throws Exception {
        //Arrange
        AnimalInputDto ferret = new AnimalInputDto();
        ferret.name = "Freddy";
        ferret.age = 3;
        ferret.sex = Sex.M;
        ferret.commonSpeciesName = "Ferret";
        ferret.scientificSpeciesName = "Mustela Furo";
        ferret.warning = true;
        ferret.warningExplanation = "Can and will bite";
        ferret.description = "Freddy is a lovely ferret, but can play bite. He'll make a lovely pet for someone who knows how to handle him";
        ferret.category = "Mammals";

        AnimalOutputDto created = animalService.create(ferret);
        Animal newFerret = AnimalMapper.fromInputDtoToModel(ferret);
        animalRepository.save(newFerret);

        //Act
        List<Animal> found = animalRepository.findAll();

        //Assert
        for (Animal animal : found)
        {
            if (Objects.equals(animal.getName(), "Freddy")) {
                assertEquals(created.getCategory(), animal.getSpeciesCategory());
                assertEquals(created.getCommonSpeciesName(), animal.getCommonSpeciesName());
                assertEquals(created.getAge(), animal.getAge());
            }
        }
    }

    @Test
    @DisplayName("Get a specific animal")
    void getAnimal() throws Exception {
        //Arrange
        AnimalInputDto ferret = new AnimalInputDto();
        ferret.name = "Freddy";
        ferret.age = 3;
        ferret.sex = Sex.M;
        ferret.commonSpeciesName = "Ferret";
        ferret.scientificSpeciesName = "Mustela Furo";
        ferret.warning = true;
        ferret.warningExplanation = "Can and will bite";
        ferret.description = "Freddy is a lovely ferret, but can play bite. He'll make a lovely pet for someone who knows how to handle him";
        ferret.category = "Mammals";

        Animal newFerret = AnimalMapper.fromInputDtoToModel(ferret);
        animalRepository.save(newFerret);

        AnimalInputDto cat = new AnimalInputDto();
        cat.name = "Mittens";
        cat.age = 1;
        cat.sex = Sex.X;
        cat.commonSpeciesName = "Cat";
        cat.scientificSpeciesName = "Felis Cattus";
        cat.category = "Mammals";
        cat.warning = false;
        cat.description = "Mittens is a very friendly cat";

        Animal newCat = AnimalMapper.fromInputDtoToModel(ferret);
        animalRepository.save(newCat);

        //Act
        List<Animal> found = animalRepository.findAll();

        //Assert
        for (Animal animal : found) {
            if (Objects.equals(animal.getName(), "Mittens")) {
                assertEquals("Cat", animal.getCommonSpeciesName());
                assertEquals(1, animal.getAge());
                assertEquals("Mammals", animal.getSpeciesCategory());
            }
        }
    }
}