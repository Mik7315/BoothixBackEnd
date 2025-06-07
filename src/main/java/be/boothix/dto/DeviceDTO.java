package be.boothix.dto;

import be.boothix.domain.Device;
import be.boothix.domain.DeviceTypeEnum;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

@JsonSerialize
@Getter
public class DeviceDTO {
    private Long idDevice;
    private String name;
    private String description;
    private DeviceTypeEnum type;

    public DeviceDTO() { }

    public DeviceDTO(Device device) {
        this.idDevice = device.getIdDevice();
        this.name = device.getName();
        this.description = device.getDescription();
        this.type = device.getType();
    }
}
