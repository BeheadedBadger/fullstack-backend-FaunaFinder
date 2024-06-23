package nl.novi.FaunaFinder.service;

import nl.novi.FaunaFinder.repositories.DonationRepository;
import org.springframework.stereotype.Service;

@Service
public class DonationService {
    private final DonationRepository repo;

    public DonationService(DonationRepository donationRepository) {
        this.repo = donationRepository;
    }
}
