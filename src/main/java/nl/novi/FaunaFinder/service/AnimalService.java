package nl.novi.FaunaFinder.service;
import nl.novi.FaunaFinder.dtos.input.AnimalInputDto;
import nl.novi.FaunaFinder.dtos.mapper.AnimalMapper;
import nl.novi.FaunaFinder.dtos.output.AnimalOutputDto;
import nl.novi.FaunaFinder.exceptions.AnimalCreationException;
import nl.novi.FaunaFinder.exceptions.AnimalNotFoundException;
import nl.novi.FaunaFinder.models.Animal;
import nl.novi.FaunaFinder.repositories.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository repo;

    public AnimalService(AnimalRepository animalRepository) {
        this.repo = animalRepository;
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
}
