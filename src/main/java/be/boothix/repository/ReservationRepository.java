package be.boothix.repository;

import be.boothix.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT sum(totalPrice) from Reservation WHERE extract(YEAR FROM eventDate) = :year AND extract(MONTH FROM eventDate) <= :month AND status = 'CLOSED'")
    Double sumAllReservationForYear(@Param("year") int year, @Param("month") int month);

    List<Reservation> findReservationByClient_IdClient(Long clientIdClient);

}
