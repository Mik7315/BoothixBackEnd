package be.boothix.controller;

import be.boothix.domain.Device;
import be.boothix.dto.DeviceDTO;
import be.boothix.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", exposedHeaders = "Location")
@RestController
@RequestMapping(value = "/api/device")
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) { this.deviceService = deviceService; }

    @PostMapping
    public ResponseEntity<DeviceDTO> create(@RequestBody DeviceDTO deviceDTO) {
        Device device = this.deviceService.createDevice(deviceDTO);

        DeviceDTO createdDeviceDTO = new DeviceDTO(device);

        return ResponseEntity.ok(createdDeviceDTO);
    }

    @GetMapping
    public List<DeviceDTO> getAllDevices() { return this.deviceService.getAllDevices(); }
}
