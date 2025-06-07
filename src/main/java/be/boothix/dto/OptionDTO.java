package be.boothix.dto;

import be.boothix.domain.Option;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

@JsonSerialize
@Getter
public class OptionDTO {
    private Long idOption;
    private String name;
    private String description;
    private long price;

    public OptionDTO() { }

    public OptionDTO(Option option) {
        this.idOption = option.getIdOption();
        this.name = option.getName();
        this.description = option.getDescription();
        this.price = option.getPrice();
    }
}
