package be.boothix.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue
    private Long idDevice;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private DeviceTypeEnum type;
}
