package be.boothix.service;

import be.boothix.domain.Formula;
import be.boothix.dto.FormulaDTO;
import be.boothix.repository.FormulaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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

    //public Formula getFormulaById(Long id) {
    //    return formulaRepository.findById(id).orElse(null);
    //}

    public List<FormulaDTO> getAllFormulas() {
        return formulaRepository.findAll().stream().map(FormulaDTO::new).collect(Collectors.toList());
    }
}
