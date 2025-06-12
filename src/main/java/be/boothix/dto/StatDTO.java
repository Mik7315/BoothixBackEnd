package be.boothix.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StatDTO {

    private String totalClient;
    private String totalClientTitle;
    private String totalReservation;
    private String totalReservationTitle;
    private String totalRevenus;
    private String totalRevenusTitle;
    private String totalRevenusExtra;

}
