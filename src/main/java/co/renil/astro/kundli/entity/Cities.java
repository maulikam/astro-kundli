package co.renil.astro.kundli.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "cities")
public class Cities {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;

    @Column(name = "timezone")
    private String timezone;
}
