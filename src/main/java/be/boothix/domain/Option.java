package be.boothix.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue
    @SequenceGenerator(name = "global_seq", sequenceName = "hibernate_sequence", allocationSize = 1)
    private Long idOption;
    private String name;
    private String description;
    private Long price;

    public Option() { }

    public Option(Long idOption, String name, String description, Long price) {
        this.idOption = idOption;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
