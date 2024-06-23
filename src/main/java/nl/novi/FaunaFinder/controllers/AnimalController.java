package nl.novi.FaunaFinder.controllers;
import nl.novi.FaunaFinder.dtos.input.AnimalInputDto;
import nl.novi.FaunaFinder.dtos.output.AnimalOutputDto;
import nl.novi.FaunaFinder.service.AnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class AnimalController {
    AnimalService animalService;

    @PostMapping
    public ResponseEntity<AnimalOutputDto> addAnimal (@Valid @RequestBody AnimalInputDto inputDto) {
        AnimalOutputDto outPutDto = animalService.create(inputDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(outPutDto.getId()).toUri();
        return ResponseEntity.created(uri).body(outPutDto);
    }

    @GetMapping
    public ResponseEntity<List<AnimalOutputDto>> getAllAnimals() {
        return ResponseEntity.ok().body(animalService.getAll());
    }
}
