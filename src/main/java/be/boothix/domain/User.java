package be.boothix.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @SequenceGenerator(name = "global_seq", sequenceName = "hibernate_sequence", allocationSize = 1)
    private Long idUser;
    private String username;
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;
}
