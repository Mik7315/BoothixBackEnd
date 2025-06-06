package be.boothix.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Entity
@Table(name = "formulas")
public class Formula {

    @Id
    @GeneratedValue
    private Long idFormula;
    private String name;
    private String description;
    private Long price;
    @ManyToOne
    @JoinColumn(name = "id_device")
    private Device device;

    public Formula() {

    }

    public Formula(Long idFormula, String name, String description, Long price, Device device) {
        this.idFormula = idFormula;
        this.name = name;
        this.description = description;
        this.price = price;
        this.device = device;
    }
}
