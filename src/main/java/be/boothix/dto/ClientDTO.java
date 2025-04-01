package be.boothix.dto;

import be.boothix.domain.Address;
import be.boothix.domain.Client;
import be.boothix.domain.ClientTypeEnum;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

@JsonSerialize
@Getter
public class ClientDTO {
    private Long idClient;
    private ClientTypeEnum type;
    private String firstName;
    private String lastName;
    private String denomination;
    private String vatNumber;
    private String bceNumber;
    private String phoneNumber;
    private String email;
    private Address address;

    public ClientDTO() {

    }

    public ClientDTO(Client client) {
        this.idClient = client.getIdClient();
        this.type = client.getType();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.denomination = client.getDenomination();
        this.vatNumber = client.getVatNumber();
        this.bceNumber = client.getBceNumber();
        this.phoneNumber = client.getPhoneNumber();
        this.email = client.getEmail();
        this.address = client.getAddress();
    }
}
