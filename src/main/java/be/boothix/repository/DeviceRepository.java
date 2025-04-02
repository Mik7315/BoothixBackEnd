package be.boothix.repository;

import be.boothix.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DeviceRepository extends JpaRepository<Device, Long> {

}
