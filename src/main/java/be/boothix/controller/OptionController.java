package be.boothix.controller;

import be.boothix.domain.Option;
import be.boothix.dto.OptionDTO;
import be.boothix.service.OptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", exposedHeaders = "Location")
@RestController
@RequestMapping(value = "/api/option")
public class OptionController {
    private final OptionService optionService;

    public OptionController(OptionService optionService) { this.optionService = optionService; }

    @PostMapping
    public ResponseEntity<OptionDTO> createOption(@RequestBody OptionDTO optionDTO) {
        Option option = this.optionService.createOption(optionDTO);

        OptionDTO createdOptionDTO = new OptionDTO(option);

        return ResponseEntity.ok(createdOptionDTO);
    }

    @PutMapping
    public ResponseEntity<OptionDTO> update(@RequestBody OptionDTO optionDTO) {
        Option option = this.optionService.updateOption(optionDTO);

        OptionDTO createdOptionDTO = new OptionDTO(option);

        return ResponseEntity.ok(createdOptionDTO);
    }

    @GetMapping
    public List<OptionDTO> getAllOptions() { return this.optionService.getAllOptions(); }

    @GetMapping("/{id}")
    public OptionDTO getById(@PathVariable Long id) { return this.optionService.getOptionById(id); }
}
