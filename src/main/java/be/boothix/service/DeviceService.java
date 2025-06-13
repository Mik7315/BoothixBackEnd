package be.boothix.service;

import be.boothix.domain.Device;
import be.boothix.domain.Formula;
import be.boothix.dto.DeviceDTO;
import be.boothix.repository.DeviceRepository;
import be.boothix.repository.FormulaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final FormulaRepository formulaRepository;

    public DeviceService(DeviceRepository deviceRepository, FormulaRepository formulaRepository) {
        this.deviceRepository = deviceRepository;
        this.formulaRepository = formulaRepository;
    }

    public Device createDevice(DeviceDTO deviceDTO) {
        Device device = new Device(null, deviceDTO.getName(), deviceDTO.getDescription(), deviceDTO.getType());

        return deviceRepository.save(device);
    }

    public Device updateDevice(DeviceDTO deviceDTO) {
        if (deviceDTO == null || deviceDTO.getIdDevice() == null) {
            throw new IllegalStateException("Device ID is required for update.");
        }

        Device existingDevice = deviceRepository.findById(deviceDTO.getIdDevice()).orElseThrow(() ->
                new EntityNotFoundException("Device not found with ID: " + deviceDTO.getIdDevice()));

        existingDevice.setName(deviceDTO.getName());
        existingDevice.setDescription(deviceDTO.getDescription());
        existingDevice.setType(deviceDTO.getType());

        return deviceRepository.save(existingDevice);
    }

    public List<DeviceDTO> getAllDevices() {
        return this.deviceRepository.findAll().stream().sorted(Comparator.comparing((Device::getIdDevice))).map(DeviceDTO::new).collect(Collectors.toList());
    }

    public DeviceDTO getDeviceById(Long id) {
        return this.deviceRepository.findById(id).stream().map(DeviceDTO::new).findFirst().get();
    }

    public void deleteDevice(Long deviceId) {
        if (deviceId != null) {
            List<Formula> formulas = formulaRepository.findFormulaByDevice_IdDevice(deviceId);

            if(formulas.isEmpty()){
                deviceRepository.deleteById(deviceId);
            }
        }
    }
}
