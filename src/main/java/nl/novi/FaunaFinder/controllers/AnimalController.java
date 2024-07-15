package nl.novi.FaunaFinder.controllers;
import nl.novi.FaunaFinder.dtos.input.AnimalInputDto;
import nl.novi.FaunaFinder.dtos.output.AnimalOutputDto;
import nl.novi.FaunaFinder.service.AnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalService animalService;
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<AnimalOutputDto> addAnimal (@Valid @RequestBody AnimalInputDto inputDto) {
        AnimalOutputDto outPutDto = animalService.create(inputDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(outPutDto.getId()).toUri();
        return ResponseEntity.created(uri).body(outPutDto);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<AnimalOutputDto>> addAnimals (@RequestBody ArrayList<AnimalInputDto> inputDtos) {
        for (AnimalInputDto inputDto : inputDtos) {
            AnimalOutputDto outputDto = animalService.create(inputDto);
            ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(outputDto.getId()).toUri();
        }

        return ResponseEntity.ok().body(animalService.getAll());
    }

    @GetMapping
    public ResponseEntity<List<AnimalOutputDto>> getAllAnimals() {
        return ResponseEntity.ok().body(animalService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AnimalOutputDto> getUser(@PathVariable("id") Long animalid) {
        AnimalOutputDto optionalAnimal = animalService.get(animalid);
        
        return ResponseEntity.ok().body(optionalAnimal);
    }
}
