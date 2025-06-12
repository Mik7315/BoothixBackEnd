package be.boothix.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue
    @SequenceGenerator(name = "global_seq", sequenceName = "hibernate_sequence", allocationSize = 1)
    private Long idDevice;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private DeviceTypeEnum type;

    public Device() { }

    public Device(Long idDevice, String name, String description, DeviceTypeEnum type) {
        this.idDevice = idDevice;
        this.name = name;
        this.description = description;
        this.type = type;
    }
}
