package be.boothix.repository;

import be.boothix.domain.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FormulaRepository extends JpaRepository<Formula, Long> {
    List<Formula> findFormulaByDevice_IdDevice(Long deviceIdDevice);
}
