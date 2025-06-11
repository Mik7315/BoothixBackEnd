package be.boothix.dto;

import be.boothix.domain.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@JsonSerialize
@Getter
public class ReservationDTO {
    private Long idReservation;
    private String phoneNumber;
    private Address address;
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
    private Client client;
    private List<Option> options;
    private List<Formula> formulas;

    public ReservationDTO() {

    }

    public ReservationDTO(Reservation reservation) {
        this.idReservation = reservation.getIdReservation();
        this.phoneNumber = reservation.getPhoneNumber();
        this.address = reservation.getAddress();
        this.status = reservation.getStatus();
        this.eventDate = reservation.getEventDate();
        this.eventTime = reservation.getEventTime();
        this.installationDate = reservation.getInstallationDate();
        this.installationTime = reservation.getInstallationTime();
        this.pickUpDate = reservation.getPickUpDate();
        this.pickUpTime = reservation.getPickUpTime();
        this.deliveryCost = reservation.getDeliveryCost();
        this.discount = reservation.getDiscount();
        this.totalPrice = reservation.getTotalPrice();
        this.deposit = reservation.getDeposit();
        this.comment = reservation.getComment();
        this.galleryLink = reservation.getGalleryLink();
        this.client = reservation.getClient();
        this.options = reservation.getOptions();
        this.formulas = reservation.getFormulas();
    }
}
