package co.renil.astro.kundli.entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "kundlis")
@EntityListeners(AuditingEntityListener.class)
public class Kundli {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "birth_date", nullable = false)
    private java.sql.Date birthDate;

    @Column(name = "birth_time", nullable = false)
    private java.sql.Time birthTime;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "nakshatra")
    private String nakshatra;

    @Column(name = "manglik_status")
    private Boolean manglikStatus;

    @Type(JsonBinaryType.class)
    @Column(name = "chart_data", columnDefinition = "jsonb")
    private String chartData;

    @Type(JsonBinaryType.class)
    @Column(name = "dasa_periods", columnDefinition = "jsonb")
    private String dasaPeriods;

    @Column(name = "bhava_predictions")
    private String bhavaPredictions;

    @Column(name = "language")
    private String language;

    @Column(name = "sade_sati_status")
    private Boolean sadeSatiStatus;

    @Column(name = "ayanamsa")
    private String ayanamsa;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
