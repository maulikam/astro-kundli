package co.renil.astro.kundli.entity;

// import co.renil.astro.kundli.config.JsonConverter;
import co.renil.astro.kundli.config.JsonConverter;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "horoscope_details")
@EntityListeners(AuditingEntityListener.class)
public class HoroscopeDetails {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "kundli_id", nullable = false)
    private Kundli kundli;

    @Column(name = "horoscope_date")
    private LocalDateTime horoscopeDate;

    @Convert(converter = JsonConverter.class)
    @Column(name = "planetary_positions", columnDefinition = "jsonb")
    private String planetaryPositions;

    @Convert(converter = JsonConverter.class)
    @Column(name = "rahu_ketu_positions", columnDefinition = "jsonb")
    private String rahuKetuPositions;

    @Column(name = "predictions")
    private String predictions;

    @Column(name = "language")
    private String language;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
}
