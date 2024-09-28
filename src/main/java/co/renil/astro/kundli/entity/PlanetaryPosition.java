package co.renil.astro.kundli.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "planetary_positions")
@EntityListeners(AuditingEntityListener.class)
public class PlanetaryPosition {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "kundli_id", nullable = false)
    private Kundli kundli;

    @Column(name = "planet", nullable = false)
    private String planet;

    @Column(name = "zodiac_sign")
    private String zodiacSign;

    @Column(name = "degree")
    private Float degree;

    @Column(name = "retrograde")
    private Boolean retrograde;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
}
