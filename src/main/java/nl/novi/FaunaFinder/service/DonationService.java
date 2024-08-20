package nl.novi.FaunaFinder.service;
import nl.novi.FaunaFinder.dtos.input.DonationInputDto;
import nl.novi.FaunaFinder.dtos.mapper.AnimalMapper;
import nl.novi.FaunaFinder.dtos.output.DonationOutputDto;
import nl.novi.FaunaFinder.dtos.mapper.DonationMapper;
import nl.novi.FaunaFinder.models.Donation;
import nl.novi.FaunaFinder.models.User;
import nl.novi.FaunaFinder.repositories.DonationRepository;
import nl.novi.FaunaFinder.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DonationService {
    private final DonationRepository repo;
    private final UserRepository userRepo;

    public DonationService(DonationRepository donationRepository, UserRepository userRepo) {
        this.repo = donationRepository;
        this.userRepo = userRepo;
    }

    public DonationOutputDto create (DonationInputDto inputDto) {
        Optional<User> sender = userRepo.findByUsername(inputDto.sending);
        Optional<User> receiver = userRepo.findByUsername(inputDto.receiving);
        Donation model = new Donation();

        if (sender.isPresent() && receiver.isPresent()) {
            model = DonationMapper.fromInputToModel(inputDto, sender.get(), receiver.get());
        }

        if (sender.isEmpty() && receiver.isPresent()) {
            User anon = new User();
            model = DonationMapper.fromInputToModel(inputDto, anon, receiver.get());
        }

        repo.save(model);
        return DonationMapper.fromModelToOutputDto(model);
    }
}
