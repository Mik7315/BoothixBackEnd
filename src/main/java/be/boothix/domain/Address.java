package be.boothix.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;
    private String houseNumber;
    private String box;
    private String city;
    private String zipCode;
    private String country;


}
