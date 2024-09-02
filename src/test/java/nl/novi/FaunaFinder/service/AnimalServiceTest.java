package nl.novi.FaunaFinder.service;
import nl.novi.FaunaFinder.dtos.input.AnimalInputDto;
import nl.novi.FaunaFinder.dtos.output.AnimalOutputDto;
import nl.novi.FaunaFinder.models.*;
import nl.novi.FaunaFinder.repositories.AnimalRepository;
import nl.novi.FaunaFinder.repositories.FileUploadRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class AnimalServiceTest {

    //Service that's being tested
    @InjectMocks
    AnimalService animalService;

    //Dependencies
    @Mock
    private AnimalRepository repo;
    @Mock
    private FileUploadRepository fileRepo;
    @Mock
    private ImageService imgService;

    AnimalInputDto animalInputDto = new AnimalInputDto();
    Animal animal = new Animal();
    AnimalOutputDto animalOutputDto = new AnimalOutputDto();
    User shelter = new User();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        shelter.setUsername("testShelter");
        shelter.setPassword("fakePassword");
        shelter.setRole(Role.SHELTER);
        shelter.setSpeciality("Mammal");

        Long animalId = 1L;

        animalInputDto.name = "TestAnimal";
        animalInputDto.age = 3;
        animalInputDto.commonSpeciesName = "Cat";
        animalInputDto.scientificSpeciesName = "Felis Cattus";
        animalInputDto.category = "Mammal";
        animalInputDto.sex = Sex.F;
        animalInputDto.shelter = shelter;

        animal.setName("TestAnimal");
        animal.setAge(3);
        animal.setCommonSpeciesName("Cat");
        animal.setScientificSpeciesName("Felis Cattus");
        animal.setSpeciesCategory("Mammal");
        animal.setSex(Sex.F);
        animal.setShelter(shelter);
        animal.setId(animalId);

        animalOutputDto.setName("TestAnimal");
        animalOutputDto.setAge(3);
        animalOutputDto.setCommonSpeciesName("Cat");
        animalOutputDto.setScientificSpeciesName("Felis Cattus");
        animalOutputDto.setCategory("Mammal");
        animalOutputDto.setSex(Sex.F);
        animalOutputDto.setShelter(shelter);
        animalOutputDto.setId(animalId);

        List<Animal> animalList = new ArrayList<>();
        animalList.add(animal);

        //Mock calls
        when(repo.save(animal)).thenReturn(animal);
        when(repo.findById(anyLong())).thenReturn(Optional.of(animal));
        when(repo.findAll()).thenReturn(animalList);
    }

    @AfterEach
    void tearDown() {
        animalInputDto = null;
        animal = null;
        animalOutputDto = null;
        shelter = null;
    }

    @Test
    @DisplayName("Delete an animal")
    void delete() {
        List<Animal> animals = repo.findAll();
        assertEquals(1, animals.size());

        animals.remove(animal);
        assertEquals(0, animals.size());
    }

    @Test
    @DisplayName("Create and save a new animal")
    void create() {
        AnimalOutputDto createdAnimal = animalService.create(animalInputDto);
        assertEquals(animalInputDto.age, createdAnimal.getAge());
        assertEquals(animalInputDto.name, createdAnimal.getName());
        assertEquals(animalInputDto.sex, createdAnimal.getSex());
        assertEquals(animalInputDto.shelter, createdAnimal.getShelter());
    }

    @Test
    @DisplayName("Find a specific animal")
    void get() {
        Animal savedAnimal = repo.save(animal);
        Optional<Animal> foundAnimal = repo.findById(savedAnimal.getId());
        if (foundAnimal.isPresent()) {
            assertEquals(savedAnimal.getCommonSpeciesName(), foundAnimal.get().getCommonSpeciesName());
        }
        else { System.out.println("Animal not found"); }
    }

    @Test
    @DisplayName("Find all animals")
    void getAll() {
        Animal savedAnimal = repo.save(animal);
        List<Animal> animals = repo.findAll();

        assertEquals(savedAnimal.getAge(), animals.get(0).getAge());
        assertEquals(savedAnimal.getShelter().getUsername(), animals.get(0).getShelter().getUsername());
    }

    @Test
    @DisplayName("Set animal photo")
    void assignPhotoToAnimal() {
        Image photo = new Image("fake.png");
        animal.setAnimalPhoto(photo);
        repo.save(animal);
        assertEquals(photo, animal.getAnimalPhoto());
    }
}