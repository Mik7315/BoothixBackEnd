package be.boothix.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "reservations")
public class Reservation extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @SequenceGenerator(name = "global_seq", sequenceName = "hibernate_sequence", allocationSize = 1)
    private Long idReservation;
    private String phoneNumber;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private ReservationStatusEnum status;
    private LocalDate eventDate;
    private String eventTime;
    private LocalDate installationDate;
    private String installationTime;
    private LocalDate pickUpDate;
    private String pickUpTime;
    private Long deliveryCost;
    private Long discount;
    private Long totalPrice;
    private Long deposit;
    private String comment;
    private String galleryLink;
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

    public Reservation() {

    }

    public Reservation(Long idReservation, String phoneNumber, Address address, ReservationStatusEnum status, LocalDate eventDate, String eventTime, LocalDate installationDate, String installationTime, LocalDate pickUpDate, String pickUpTime, Long deliveryCost, Long discount, Long totalPrice, Long deposit, String comment, String galleryLink, Client client, List<Option> options, List<Formula> formulas) {
        this.idReservation = idReservation;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = status;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.installationDate = installationDate;
        this.installationTime = installationTime;
        this.pickUpDate = pickUpDate;
        this.pickUpTime = pickUpTime;
        this.deliveryCost = deliveryCost;
        this.discount = discount;
        this.totalPrice = totalPrice;
        this.deposit = deposit;
        this.comment = comment;
        this.galleryLink = galleryLink;
        this.client = client;
        this.options = options;
        this.formulas = formulas;
    }

    public void close(String galleryLink) {
        this.galleryLink = galleryLink;
        this.status = ReservationStatusEnum.CLOSED;
    }

    public void cancel() {
        this.status = ReservationStatusEnum.CANCELLED;
    }
}
