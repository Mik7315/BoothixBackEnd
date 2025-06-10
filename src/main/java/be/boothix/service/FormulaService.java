package be.boothix.service;

import be.boothix.domain.Formula;
import be.boothix.dto.FormulaDTO;
import be.boothix.repository.FormulaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class FormulaService {
    private final FormulaRepository formulaRepository;

    public FormulaService(FormulaRepository formulaRepository) { this.formulaRepository = formulaRepository; }

    public Formula createFormula(FormulaDTO formulaDTO) {
        Formula formula = new Formula(null, formulaDTO.getName(), formulaDTO.getDescription(), formulaDTO.getPrice(), formulaDTO.getDevice());

        return formulaRepository.save(formula);
    }

    public Formula updateFormula(FormulaDTO formulaDTO) {
        if (formulaDTO == null || formulaDTO.getIdFormula() == null) {
            throw new IllegalStateException("Formula ID is required for update.");
        }

        Formula existingFormula = formulaRepository.findById(formulaDTO.getIdFormula()).orElseThrow(() ->
                new EntityNotFoundException("Formula not found with ID: " + formulaDTO.getIdFormula()));

        existingFormula.setName(formulaDTO.getName());
        existingFormula.setDescription(formulaDTO.getDescription());
        existingFormula.setPrice(formulaDTO.getPrice());
        existingFormula.setDevice(formulaDTO.getDevice());

        return formulaRepository.save(existingFormula);
    }

    public List<FormulaDTO> getAllFormulas() {
        return formulaRepository.findAll().stream().sorted(Comparator.comparing((Formula::getIdFormula))).map(FormulaDTO::new).collect(Collectors.toList());
    }

    public FormulaDTO getFormulaById(Long id) {
        return this.formulaRepository.findById(id).stream().map(FormulaDTO::new).findFirst().get();
    }
}
