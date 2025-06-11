package be.boothix.controller;

import be.boothix.domain.Reservation;
import be.boothix.dto.ReservationDTO;
import be.boothix.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", exposedHeaders = "Location")
@RestController
@RequestMapping(value = "/api/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) { this.reservationService = reservationService; }

    @PostMapping
    public ResponseEntity<ReservationDTO> create(@RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = this.reservationService.createReservation(reservationDTO);

        ReservationDTO createdReservationDTO = new ReservationDTO(reservation);

        return ResponseEntity.ok(createdReservationDTO);
    }

    @PutMapping
    public ResponseEntity<ReservationDTO> update(@RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = this.reservationService.updateReservation(reservationDTO);

        ReservationDTO createdReservationDTO = new ReservationDTO(reservation);

        return ResponseEntity.ok(createdReservationDTO);
    }

    @GetMapping
    public List<ReservationDTO> getAll() { return this.reservationService.getAllReservations(); }

    @GetMapping("/{id}")
    public ReservationDTO getById(@PathVariable Long id) {
        return this.reservationService.getReservationById(id);
    }
}
