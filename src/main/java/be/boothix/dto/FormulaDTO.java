package be.boothix.dto;

import be.boothix.domain.Device;
import be.boothix.domain.Formula;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

@JsonSerialize
@Getter
public class FormulaDTO {
    private Long idFormula;
    private String name;
    private String description;
    private Long price;
    private Device device;

    public FormulaDTO() {

    }

    public FormulaDTO(Formula formula) {
        this.idFormula = formula.getIdFormula();
        this.name = formula.getName();
        this.description = formula.getDescription();
        this.price = formula.getPrice();
        this.device = formula.getDevice();
    }
}
