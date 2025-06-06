package be.boothix.repository;

import be.boothix.domain.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface FormulaRepository extends JpaRepository<Formula, Long> {

}
