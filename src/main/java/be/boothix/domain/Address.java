package be.boothix.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@JsonSerialize
@Embeddable
public class Address {

    private String street;
    private String houseNumber;
    private String box;
    private String city;
    private String zipCode;
    private String country;

    public Address() {}
}
