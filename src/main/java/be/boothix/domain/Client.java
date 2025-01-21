package be.boothix.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "clients")
public class Client extends AuditEntity {

    @Id
    @GeneratedValue
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
}
