package co.renil.astro.kundli.entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "transits")
@EntityListeners(AuditingEntityListener.class)
public class Transit {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "kundli_id", nullable = false)
    private Kundli kundli;

    @Column(name = "date")
    private java.sql.Date date;

    @Column(name = "planet")
    private String planet;

    @Type(JsonBinaryType.class)
    @Column(name = "transit_position", columnDefinition = "jsonb")
    private String transitPosition;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
}
