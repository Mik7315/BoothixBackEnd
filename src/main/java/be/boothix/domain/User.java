package be.boothix.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long idUser;
    private String username;
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;
}
