package nl.novi.FaunaFinder.controllers;

import jakarta.persistence.*;
import nl.novi.FaunaFinder.models.Animal;
import nl.novi.FaunaFinder.models.Role;
import nl.novi.FaunaFinder.models.User;
import nl.novi.FaunaFinder.repositories.AnimalRepository;
import nl.novi.FaunaFinder.repositories.UserRepository;
import nl.novi.FaunaFinder.service.AnimalService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalControllerTest {
    @Mock
    @InjectMocks
    AnimalRepository animalRepository;
    @Mock
    @InjectMocks
    UserRepository userRepository;
    @Mock
    @InjectMocks
    AnimalService animalService;

    @Captor
    ArgumentCaptor<Animal> captor;

    @BeforeEach
    void setUp() {
        User fakeTestShelter = new User();
        fakeTestShelter.setRole(Role.SHELTER);
        fakeTestShelter.setUsername("fakeTestShelter");
        fakeTestShelter.setPassword("Password");

        User fakeTestUser = new User();
        fakeTestUser.setRole(Role.USER);
        fakeTestUser.setUsername("fakeTestUser");
        fakeTestUser.setPassword("Password");

        List<User> favs = new ArrayList<>();
        favs.add(fakeTestUser);

        Animal cat = new Animal();
        cat.setName("Mittens");
        cat.setAge(1);
        cat.setSex(Animal.Sex.X);
        cat.setCommonSpeciesName("cat");
        cat.setScientificSpeciesName("Felis Cattus");
        cat.setSpeciesCategory("Mammals");
        cat.setWarning(false);
        cat.setDescription("Mittens is a very friendly cat");
        cat.setShelter(fakeTestShelter);
        cat.setFavourites(favs);

        Animal ferret = new Animal();
        ferret.setName("Freddy");
        ferret.setAge(2);
        ferret.setSex(Animal.Sex.M);
        ferret.setCommonSpeciesName("Ferret");
        ferret.setScientificSpeciesName("Mustela Furo");
        ferret.setSpeciesCategory("Mammals");
        ferret.setWarning(true);
        ferret.setWarningExplanation("Freddy can and will bite. Experienced owners only");
        ferret.setDescription("Freddy is a lovely ferret, but can play bite. He'll make a lovely pet for someone who knows how to handle him");
        ferret.setShelter(fakeTestShelter);
        ferret.setFavourites(favs);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Submit a new animal")
    void addAnimal() {
    }

    @Test
    @DisplayName("")
    void getAnimal() {
    }
}