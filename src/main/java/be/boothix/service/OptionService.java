package be.boothix.service;

import be.boothix.domain.Option;
import be.boothix.domain.Reservation;
import be.boothix.dto.OptionDTO;
import be.boothix.repository.OptionRepository;
import be.boothix.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional
@Service
public class OptionService {
    private final OptionRepository optionRepository;
    private final ReservationRepository reservationRepository;

    public OptionService(OptionRepository optionRepository, ReservationRepository reservationRepository) {
        this.optionRepository = optionRepository;
        this.reservationRepository = reservationRepository;
    }

    public Option createOption(OptionDTO optionDTO) {
        Option option = new Option(null, optionDTO.getName(), optionDTO.getDescription(), optionDTO.getPrice());

        return optionRepository.save(option);
    }

    public Option updateOption(OptionDTO optionDTO) {
        if (optionDTO == null || optionDTO.getIdOption() == null) {
            throw new IllegalStateException("Option ID is required for update.");
        }

        Option existingOption = optionRepository.findById(optionDTO.getIdOption()).orElseThrow(() ->
                new EntityNotFoundException("Option not found with ID: " + optionDTO.getIdOption()));

        existingOption.setName(optionDTO.getName());
        existingOption.setDescription(optionDTO.getDescription());
        existingOption.setPrice(optionDTO.getPrice());

        return optionRepository.save(existingOption);
    }

    public List<OptionDTO> getAllOptions() {
        return this.optionRepository.findAll().stream().sorted(Comparator.comparing((Option::getIdOption))).map(OptionDTO::new).collect(Collectors.toList());
    }

    public OptionDTO getOptionById(Long id) {
        return this.optionRepository.findById(id).stream().map(OptionDTO::new).findFirst().get();
    }

    public void delete(Long idOption) {
        // Récupère toute les réservations de la DB
        List<Reservation> reservations = this.reservationRepository.findAll();

        // Récupère les reservations qui contienent un lien vers la formules que je veux supprimer
        List<Reservation> filteredReservation = reservations
                .stream()
                .filter(reservation -> {
                    // Si la formule que je veux supprimer est présente dans une des formules lié à une réservation, je renvoi true
                    List<Option> options = reservation.getOptions()
                            .stream()
                            .filter(option -> Objects.equals(option.getIdOption(), idOption))
                            .toList();
                    return !options.isEmpty();
                }).toList();

        if (!filteredReservation.isEmpty()) {
            throw new RuntimeException("L'option est liée à une réservation");
        }
        optionRepository.deleteById(idOption);
    }
}
