package be.boothix.repository;

import be.boothix.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
