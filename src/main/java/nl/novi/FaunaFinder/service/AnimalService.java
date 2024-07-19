package nl.novi.FaunaFinder.service;
import nl.novi.FaunaFinder.dtos.input.AnimalInputDto;
import nl.novi.FaunaFinder.dtos.mapper.AnimalMapper;
import nl.novi.FaunaFinder.dtos.output.AnimalOutputDto;
import nl.novi.FaunaFinder.exceptions.AnimalCreationException;
import nl.novi.FaunaFinder.exceptions.AnimalNotFoundException;
import nl.novi.FaunaFinder.models.Animal;
import nl.novi.FaunaFinder.models.Image;
import nl.novi.FaunaFinder.repositories.AnimalRepository;
import nl.novi.FaunaFinder.repositories.FileUploadRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository repo;
    private final FileUploadRepository fileRepo;

    public AnimalService(AnimalRepository animalRepository, FileUploadRepository fileRepo) {
        this.repo = animalRepository;
        this.fileRepo = fileRepo;
    }

    public AnimalOutputDto create (AnimalInputDto inputDto) {
        try {
            Animal model = repo.save(AnimalMapper.fromInputDtoToModel(inputDto));
            return AnimalMapper.fromModelToOutputDto(model);
        }
        catch (AnimalCreationException e) {
            throw new AnimalCreationException(e.getCause());
        }

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
            repo.save(animal.get());
            return animal.get();
        }

        throw new Exception("Failed to add image");

        //TODO
        //throw new ImageToFoundException(ImageToFoundException.getCause);
    }
}
