package co.renil.astro.kundli.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "match_making")
@EntityListeners(AuditingEntityListener.class)
public class MatchMaking {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "kundli_1_id", nullable = false)
    private Kundli kundli1;

    @ManyToOne
    @JoinColumn(name = "kundli_2_id", nullable = false)
    private Kundli kundli2;

    @Column(name = "nakshatra_compatibility_score")
    private Integer nakshatraCompatibilityScore;

    @Column(name = "manglik_compatibility")
    private Boolean manglikCompatibility;

    @Column(name = "dasa_sandhi_compatibility")
    private Boolean dasaSandhiCompatibility;

    @Column(name = "overall_compatibility_score")
    private Integer overallCompatibilityScore;

    @Column(name = "language")
    private String language;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
}
