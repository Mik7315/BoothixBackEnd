package be.boothix.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Entity
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue
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
