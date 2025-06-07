package be.boothix.repository;

import be.boothix.domain.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OptionRepository extends JpaRepository<Option, Long> {

}
