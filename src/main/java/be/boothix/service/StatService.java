package be.boothix.service;

import be.boothix.dto.StatDTO;
import be.boothix.repository.ClientRepository;
import be.boothix.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StatService {

    private final ClientRepository clientRepository;

    private final ReservationRepository reservationRepository;

    private static final String CLIENT_TITLE = "Nombre de clients inscrits";
    private static final String RESERVATION_TITLE = "Nombre total de réservations";
    private static final String REVENU_TITLE = "Revenus total %s";
    private static final String REVENU_EXTRA = "%s année précédente";

    public StatService(ClientRepository clientRepository, ReservationRepository reservationRepository) {
        this.clientRepository = clientRepository;
        this.reservationRepository = reservationRepository;
    }

    public StatDTO getAllStat() {
        long clientTotal = clientRepository.count();
        long reservationTotal = reservationRepository.count();

        int currentYear = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonthValue();
        int lastYear = currentYear - 1;

        Double totalCurrentYear = reservationRepository.sumAllReservationForYear(currentYear, currentMonth);
        Double totalLastYear = reservationRepository.sumAllReservationForYear(lastYear, currentMonth);

        StatDTO.StatDTOBuilder builder = StatDTO.builder()
                .totalClientTitle(CLIENT_TITLE)
                .totalClient(String.valueOf(clientTotal))
                .totalReservationTitle(RESERVATION_TITLE)
                .totalReservation(String.valueOf(reservationTotal))
                .totalRevenusTitle(String.format(REVENU_TITLE, currentYear))
                .totalRevenus(String.valueOf(totalCurrentYear))
                .totalRevenusExtra("");


        if (totalCurrentYear != null && totalLastYear != null) {
            int percent = (int) Math.round(totalCurrentYear / totalLastYear * 100);

            if (totalCurrentYear < totalLastYear) {
                builder.totalRevenusExtra(String.format(REVENU_EXTRA, "-" + percent + "%"));
            } else {
                builder.totalRevenusExtra(String.format(REVENU_EXTRA, "+" + percent + "%"));
            }
        }

        return builder.build();
    }

}
