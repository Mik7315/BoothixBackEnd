package be.boothix.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "formulas")
public class Formula {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @SequenceGenerator(name = "global_seq", sequenceName = "hibernate_sequence", allocationSize = 1)
    private Long idFormula;
    private String name;
    private String description;
    private Long price;
    @ManyToOne
    @JoinColumn(name = "id_device")
    private Device device;

    public Formula() { }

    public Formula(Long idFormula, String name, String description, Long price, Device device) {
        this.idFormula = idFormula;
        this.name = name;
        this.description = description;
        this.price = price;
        this.device = device;
    }
}
