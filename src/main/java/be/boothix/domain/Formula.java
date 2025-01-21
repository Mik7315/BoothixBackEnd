package be.boothix.domain;


import jakarta.persistence.*;

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
}
