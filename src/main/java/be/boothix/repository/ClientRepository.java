package be.boothix.repository;

import be.boothix.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);
    Optional<Client> findByDenomination(String denomination);
    Optional<Client> findByVatNumber(String vatNumber);
    Optional<Client> findByBceNumber(String bceNumber);
}
