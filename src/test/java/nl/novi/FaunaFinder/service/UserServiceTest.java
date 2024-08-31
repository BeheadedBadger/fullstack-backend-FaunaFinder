package nl.novi.FaunaFinder.service;
import nl.novi.FaunaFinder.models.Animal;
import nl.novi.FaunaFinder.models.Image;
import nl.novi.FaunaFinder.models.Role;
import nl.novi.FaunaFinder.repositories.AnimalRepository;
import nl.novi.FaunaFinder.repositories.FileUploadRepository;
import nl.novi.FaunaFinder.repositories.UserRepository;
import nl.novi.FaunaFinder.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserServiceTest {
    //Service that's being tested
    @InjectMocks
    UserService userService;

    //Dependencies
    @Mock
    private UserRepository repo;
    @Mock
    private FileUploadRepository fileRepo;
    @Mock
    private ImageService imgService;
    @Mock
    private AnimalRepository animalRepo;

    User user = new User();
    List<User> users = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user.setUsername("testUser");
        user.setPassword("fakePassword");
        user.setRole(Role.USER);

        users.add(user);

        when(repo.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(repo.findAll()).thenReturn(users);
        when(repo.save(user)).thenReturn(user);
    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    @DisplayName("Get a specific user")
    void get() {
        Optional<User> foundUser = repo.findByUsername(user.getUsername());
        String foundUserPassword = "";
        Role foundUserRole = null;
        if (foundUser.isPresent()) {
            foundUserRole = foundUser.get().getRole();
            foundUserPassword = foundUser.get().getPassword();
        }
        assertEquals(user.getRole(), foundUserRole);
        assertEquals(user.getPassword(), foundUserPassword);
    }

    @Test
    @DisplayName("Find all users")
    void getAll() {
        List<User> foundUsers = repo.findAll();

        assertEquals(user.getUsername(), foundUsers.get(0).getUsername());
        assertEquals(user.getPassword(), foundUsers.get(0).getPassword());
    }

    @Test
    @DisplayName("Add photo to User")
    void assignPhotoToUser() {
        Image photo = new Image("fake.png");
        user.setUserPhoto(photo);
        User savedUser = repo.save(user);
        assertEquals(photo, savedUser.getUserPhoto());
    }

    @Test
    @DisplayName("Assign animal to User(shelter)")
    void assignAnimalToShelter() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal = new Animal();
        animal.setName("name");
        animalList.add(animal);

        user.setShelterAnimals(new ArrayList<>());
        user.getShelterAnimals().add(animal);
        animal.setShelter(user);

        assertEquals(animalList, user.getShelterAnimals());
        assertEquals(animalList.get(0).getName(), user.getShelterAnimals().get(0).getName());
    }

    @Test
    @DisplayName("Assign animal to Favourites")
    void assignAnimalToFavourites() {
        Set<Animal> animalList = new HashSet<>();
        Animal animal = new Animal();
        animal.setName("name");
        animalList.add(animal);
        Set<Animal> favourites = new HashSet<>();

        user.setFavouriteAnimals(favourites);
        user.getFavouriteAnimals().add(animal);

        Set<User> animalFavourites = new HashSet<>();
        animal.setFavourites(animalFavourites);
        animal.getFavourites().add(user);

        List<Animal> AnimalSetToList = new ArrayList<>(animalList);
        List<Animal> UserFavouritesToList = new ArrayList<>(user.getFavouriteAnimals());

        assertEquals(AnimalSetToList, UserFavouritesToList);
        assertEquals(AnimalSetToList.get(0).getName(), UserFavouritesToList.get(0).getName());
    }
}