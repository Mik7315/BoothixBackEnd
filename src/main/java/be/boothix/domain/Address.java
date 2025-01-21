package be.boothix.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;
    private String house_number;
    private String box;
    private String city;
    private String zip_code;
    private String country;


}
