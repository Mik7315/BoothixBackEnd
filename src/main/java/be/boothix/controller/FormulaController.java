package be.boothix.controller;

import be.boothix.domain.Formula;
import be.boothix.dto.FormulaDTO;
import be.boothix.service.FormulaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", exposedHeaders = "Location")
@RestController
@RequestMapping(value = "/api/formula")
public class FormulaController {
    private final FormulaService formulaService;

    public FormulaController(FormulaService formulaService) { this.formulaService = formulaService; }

    @PostMapping
    public ResponseEntity<FormulaDTO> create(@RequestBody FormulaDTO formulaDTO) {
        Formula formula = this.formulaService.createFormula(formulaDTO);

        FormulaDTO createdFormulaDTO = new FormulaDTO(formula);

        return ResponseEntity.ok(createdFormulaDTO);
    }

    @PutMapping
    public ResponseEntity<FormulaDTO> update(@RequestBody FormulaDTO formulaDTO) {
        Formula formula = this.formulaService.updateFormula(formulaDTO);

        FormulaDTO createdFormulaDTO = new FormulaDTO(formula);

        return ResponseEntity.ok(createdFormulaDTO);
    }

    @GetMapping
    public List<FormulaDTO> getAll() { return this.formulaService.getAllFormulas(); }

    @GetMapping("/{id}")
    public FormulaDTO getById(@PathVariable Long id) { return this.formulaService.getFormulaById(id); }
}
