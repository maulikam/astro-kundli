package co.renil.astro.kundli.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "varshphal")
@EntityListeners(AuditingEntityListener.class)
public class Varshphal {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "kundli_id", nullable = false)
    private Kundli kundli;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "predictions")
    private String predictions;

    @Column(name = "language")
    private String language;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
}
