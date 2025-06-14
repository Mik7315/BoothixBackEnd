package be.boothix.service;

import be.boothix.domain.Reservation;
import be.boothix.dto.ReservationDTO;
import be.boothix.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) { this.reservationRepository = reservationRepository; }

    public Reservation createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation(null, reservationDTO.getPhoneNumber(), reservationDTO.getAddress(),
                reservationDTO.getStatus(), reservationDTO.getEventDate(), reservationDTO.getEventTime(), reservationDTO.getInstallationDate(),
                reservationDTO.getInstallationTime(), reservationDTO.getPickUpDate(), reservationDTO.getPickUpTime(), reservationDTO.getDeliveryCost(),
                reservationDTO.getDiscount(), reservationDTO.getTotalPrice(), reservationDTO.getDeposit(),
                reservationDTO.getComment(), reservationDTO.getGalleryLink(), reservationDTO.getClient(),
                reservationDTO.getOptions(), reservationDTO.getFormulas());

        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(ReservationDTO reservationDTO) {
        if (reservationDTO == null || reservationDTO.getIdReservation() == null) {
            throw new IllegalStateException("Reservation ID is required for update.");
        }

        Reservation existingReservation = reservationRepository.findById(reservationDTO.getIdReservation()).orElseThrow(() ->
                new EntityNotFoundException("Reservation not found with ID: " + reservationDTO.getIdReservation()));

        existingReservation.setPhoneNumber(reservationDTO.getPhoneNumber());
        existingReservation.setAddress(reservationDTO.getAddress());
        existingReservation.setStatus(reservationDTO.getStatus());
        existingReservation.setEventDate(reservationDTO.getEventDate());
        existingReservation.setEventTime(reservationDTO.getEventTime());
        existingReservation.setInstallationDate(reservationDTO.getInstallationDate());
        existingReservation.setInstallationTime(reservationDTO.getInstallationTime());
        existingReservation.setPickUpDate(reservationDTO.getPickUpDate());
        existingReservation.setPickUpTime(reservationDTO.getPickUpTime());
        existingReservation.setDeliveryCost(reservationDTO.getDeliveryCost());
        existingReservation.setDiscount(reservationDTO.getDiscount());
        existingReservation.setTotalPrice(reservationDTO.getTotalPrice());
        existingReservation.setDeposit(reservationDTO.getDeposit());
        existingReservation.setComment(reservationDTO.getComment());
        existingReservation.setGalleryLink(reservationDTO.getGalleryLink());
        existingReservation.setClient(reservationDTO.getClient());
        existingReservation.setOptions(reservationDTO.getOptions());
        existingReservation.setFormulas(reservationDTO.getFormulas());

        return reservationRepository.save(existingReservation);
    }

    public List<ReservationDTO> getAllReservations() {
        return this.reservationRepository.findAll().stream()
                .sorted(Comparator.comparing((Reservation::getIdReservation)))
                .map(ReservationDTO::new)
                .collect(Collectors.toList());
    }

    public ReservationDTO getReservationById(Long id) {
        return this.reservationRepository.findById(id).stream()
                .map(ReservationDTO::new).findFirst().get();
    }

    public void close(Long id, String galleryLink) {
        this.reservationRepository.findById(id).ifPresent(reservation -> reservation.close(galleryLink));
    }

    public void cancel(Long id) {
        this.reservationRepository.findById(id).ifPresent(reservation -> reservation.cancel());
    }

    public void delete(Long id) {
        this.reservationRepository.deleteById(id);
    }
}
