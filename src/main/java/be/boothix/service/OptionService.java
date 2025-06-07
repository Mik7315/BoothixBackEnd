package be.boothix.service;

import be.boothix.domain.Option;
import be.boothix.dto.OptionDTO;
import be.boothix.repository.OptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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

    public List<OptionDTO> getAllOptions() {
        return this.optionRepository.findAll().stream().map(OptionDTO::new).collect(Collectors.toList());
    }
}
