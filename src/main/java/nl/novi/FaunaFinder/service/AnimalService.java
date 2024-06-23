package nl.novi.FaunaFinder.service;
import nl.novi.FaunaFinder.dtos.input.AnimalInputDto;
import nl.novi.FaunaFinder.dtos.mapper.AnimalMapper;
import nl.novi.FaunaFinder.dtos.output.AnimalOutputDto;
import nl.novi.FaunaFinder.models.Animal;
import nl.novi.FaunaFinder.repositories.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository repo;

    public AnimalService(AnimalRepository animalRepository) {
        this.repo = animalRepository;
    }

    public AnimalOutputDto create (AnimalInputDto inputDto) {
        Animal model = repo.save(AnimalMapper.fromInputDtoToModel(inputDto));
        return AnimalMapper.fromModelToOutputDto(model);
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
