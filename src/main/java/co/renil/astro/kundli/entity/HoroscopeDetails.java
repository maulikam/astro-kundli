package co.renil.astro.kundli.entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
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

    @Column(name = "current_date")
    private LocalDateTime currentDate;

    @Type(JsonBinaryType.class)
    @Column(name = "planetary_positions", columnDefinition = "jsonb")
    private String planetaryPositions;

    @Type(JsonBinaryType.class)
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
