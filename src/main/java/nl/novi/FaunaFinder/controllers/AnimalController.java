package nl.novi.FaunaFinder.controllers;
import nl.novi.FaunaFinder.dtos.input.AnimalInputDto;
import nl.novi.FaunaFinder.dtos.output.AnimalOutputDto;
import nl.novi.FaunaFinder.exceptions.AuthenticationFailedException;
import nl.novi.FaunaFinder.models.Animal;
import nl.novi.FaunaFinder.service.ImageService;
import nl.novi.FaunaFinder.service.AnimalService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.validation.Valid;

import javax.swing.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalService animalService;
    private final ImageService imageService;

    public AnimalController(AnimalService animalService, ImageService imageService) {
        this.animalService = animalService;
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<AnimalOutputDto> addAnimal(@RequestBody AnimalInputDto inputDto ) throws Exception {
        try {
            return ResponseEntity.ok(animalService.create(inputDto));
        }
        catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/batch")
    public ResponseEntity<List<AnimalOutputDto>> addAnimals(@RequestBody ArrayList<AnimalInputDto> inputDtos) {
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

    @GetMapping("/{id}")
    public ResponseEntity<AnimalOutputDto> getAnimal(@PathVariable("id") Long animalid) {
        AnimalOutputDto optionalAnimal = animalService.get(animalid);

        return ResponseEntity.ok().body(optionalAnimal);
    }

    @PostMapping("/{id}/photo")
    public ResponseEntity<Animal> addPhotoToAnimal(@PathVariable("id") Long id, @RequestBody MultipartFile file) throws Exception {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/animals/")
                .path(id.toString())
                .path("/photo")
                .toString();
        String name = id.toString();
        System.out.println(name);
        String fileName = imageService.storeFile(id.toString(), file);
        Animal animal = animalService.assignPhotoToAnimal(fileName, id);
        return ResponseEntity.created(URI.create(url)).body(animal);
    }

    @GetMapping("/{id}/photo")
    public ResponseEntity<Resource> getPhoto (@PathVariable long id) throws Exception {
        return ResponseEntity.ok().body(animalService.getImage(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Animal>> UpdateAnimal (@PathVariable long id, @RequestBody Animal animal) {
       Optional<Animal> optionalAnimal = animalService.update(id, animal);
       return ResponseEntity.ok().body(optionalAnimal);
    }
}
