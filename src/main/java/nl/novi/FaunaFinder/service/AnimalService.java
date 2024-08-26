package nl.novi.FaunaFinder.service;
import nl.novi.FaunaFinder.dtos.input.AnimalInputDto;
import nl.novi.FaunaFinder.dtos.input.UserInputDto;
import nl.novi.FaunaFinder.dtos.mapper.AnimalMapper;
import nl.novi.FaunaFinder.dtos.mapper.UserMapper;
import nl.novi.FaunaFinder.dtos.output.AnimalOutputDto;
import nl.novi.FaunaFinder.dtos.output.AuthenticationResponse;
import nl.novi.FaunaFinder.exceptions.AnimalCreationException;
import nl.novi.FaunaFinder.exceptions.AnimalNotFoundException;
import nl.novi.FaunaFinder.exceptions.AuthenticationFailedException;
import nl.novi.FaunaFinder.models.Animal;
import nl.novi.FaunaFinder.models.Image;
import nl.novi.FaunaFinder.models.User;
import nl.novi.FaunaFinder.repositories.AnimalRepository;
import nl.novi.FaunaFinder.repositories.FileUploadRepository;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository repo;
    private final FileUploadRepository fileRepo;
    private final ImageService imgService;

    public AnimalService(AnimalRepository animalRepository, FileUploadRepository fileRepo, ImageService imgService) {
        this.repo = animalRepository;
        this.fileRepo = fileRepo;
        this.imgService = imgService;
    }

    public Optional<Animal> update(long id, Animal animal) {
        Optional<Animal> model = repo.findById(id);
        if(model.isPresent()) {
            model.get().setName(animal.getName());
            return model;
        }
        else {
            throw new AnimalNotFoundException(id);
        }
    }

    public String delete (long id) {
        Optional<Animal> animal = repo.findById(id);
        if (animal.isPresent()) {
            repo.delete(animal.get());
            return "Animal removed successfully";
        }
        else {
            return "Could not find animal with id: " + id;
        }
    }
    
    public AnimalOutputDto create (AnimalInputDto inputDto) {
        Animal model = AnimalMapper.fromInputDtoToModel(inputDto);
        repo.save(model);
        return AnimalMapper.fromModelToOutputDto(model);
    }
    public AnimalOutputDto get (Long id) {
        Optional<Animal> model = repo.findById(id);
        if(model.isPresent()) {
            return AnimalMapper.fromModelToOutputDto(model.get());
        }
        else {
            throw new AnimalNotFoundException(id);
        }
    }

    public List<AnimalOutputDto> getAll() {
        List<Animal> models = repo.findAll();
        List<AnimalOutputDto> outputDtos = new ArrayList<>();

        for (Animal a : models) {
            outputDtos.add(AnimalMapper.fromModelToOutputDto(a));
        }

        return outputDtos;
    }

    //TODO make this actually update
    public Animal update(Long id, Animal animal) {
        return animal;
    }

    public Animal assignPhotoToAnimal(String fileName, Long id) throws Exception {
        Optional<Animal> animal = repo.findById(id);
        Optional<Image> image = fileRepo.findById(fileName);
        if (image.isPresent() && animal.isPresent()) {
            (animal.get()).setAnimalPhoto(image.get());
            fileRepo.save(image.get());
            repo.save(animal.get());
            return animal.get();
        }

        throw new Exception("Failed to add image");

        //TODO
        //throw new ImageToFoundException(ImageToFoundException.getCause);
    }

    public Resource getImage(long id) throws Exception {
        Optional<Animal> optionalAnimal = repo.findById(id);
        if(optionalAnimal.isEmpty()){
            throw new UsernameNotFoundException("Can't find animal with id " + id);
        }
        Image photo = optionalAnimal.get().getAnimalPhoto();
        if(photo == null){
            throw new Exception("No photo found");
        }
        return imgService.downLoadFile(photo.getFileName());
    }
}
