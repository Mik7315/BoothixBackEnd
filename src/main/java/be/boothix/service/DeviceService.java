package be.boothix.service;

import be.boothix.domain.Device;
import be.boothix.dto.DeviceDTO;
import be.boothix.repository.DeviceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) { this.deviceRepository = deviceRepository; }

    public Device createDevice(DeviceDTO deviceDTO) {
        Device device = new Device(null, deviceDTO.getName(), deviceDTO.getDescription(), deviceDTO.getType());

        return deviceRepository.save(device);
    }

    public List<DeviceDTO> getAllDevices() {
        return this.deviceRepository.findAll().stream().map(DeviceDTO::new).collect(Collectors.toList());
    }
}
