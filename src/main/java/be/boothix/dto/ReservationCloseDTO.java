package be.boothix.dto;

import be.boothix.domain.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@JsonSerialize
@Getter
public class ReservationCloseDTO {
    private Long idReservation;
    private String galleryLink;

    public ReservationCloseDTO() {}
}
