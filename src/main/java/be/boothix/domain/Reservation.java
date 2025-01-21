package be.boothix.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reservations")
public class Reservation extends AuditEntity {

    @Id
    @GeneratedValue
    private Long idReservation;
    private String phoneNumber;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private ReservationStatusEnum status;
    private LocalDateTime eventDate;
    private LocalDateTime installationDate;
    private LocalDateTime pickUpDate;
    private Long deliveryCost;
    private Long discount;
    private Long totalPrice;
    private Long deposit;
    private String comment;
    private String galleryLink;
    @ManyToOne
    @JoinColumn(name = "id_device")
    private Device device;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
    @ManyToMany
    @JoinTable(name = "options_reservations",
                joinColumns = @JoinColumn(name = "id_reservation"),
                inverseJoinColumns = @JoinColumn(name = "id_option"))
    private List<Option> options;
    @ManyToMany
    @JoinTable(name = "formulas_reservations",
            joinColumns = @JoinColumn(name = "id_reservation"),
            inverseJoinColumns = @JoinColumn(name = "id_formula"))
    private List<Formula> formulas;
}
