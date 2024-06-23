package nl.novi.FaunaFinder.service;

import nl.novi.FaunaFinder.controllers.ShelterController;
import nl.novi.FaunaFinder.repositories.ShelterRepository;
import org.springframework.stereotype.Service;

@Service
public class ShelterService {
    private final ShelterRepository repo;

    public ShelterService(ShelterRepository shelterRepository) {
        this.repo = shelterRepository;
    }
}
