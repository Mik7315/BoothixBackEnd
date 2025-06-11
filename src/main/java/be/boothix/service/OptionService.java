package be.boothix.service;

import be.boothix.domain.Option;
import be.boothix.dto.OptionDTO;
import be.boothix.repository.OptionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class OptionService {
    private final OptionRepository optionRepository;

    public OptionService(OptionRepository optionRepository) { this.optionRepository = optionRepository; }

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
}
