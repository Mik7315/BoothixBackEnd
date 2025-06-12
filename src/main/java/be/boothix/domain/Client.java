package be.boothix.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @SequenceGenerator(name = "global_seq", sequenceName = "hibernate_sequence", allocationSize = 1)
    private Long idClient;
    @Enumerated(EnumType.STRING)
    private ClientTypeEnum type;
    private String firstName;
    private String lastName;
    private String denomination;
    private String vatNumber;
    private String bceNumber;
    private String phoneNumber;
    private String email;
    @Embedded
    private Address address;

    public Client() { }

    public Client(Long idClient, ClientTypeEnum type, String firstName, String lastName, String denomination, String vatNumber, String bceNumber, String phoneNumber, String email, Address address) {
        this.idClient = idClient;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.denomination = denomination;
        this.vatNumber = vatNumber;
        this.bceNumber = bceNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }
}
