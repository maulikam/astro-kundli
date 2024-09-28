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
@Table(name = "charts")
@EntityListeners(AuditingEntityListener.class)
public class Charts {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "kundli_id", nullable = false)
    private Kundli kundli;

    @Column(name = "chart_type")
    private String chartType;

    @Type(JsonBinaryType.class)
    @Column(name = "chart_data", columnDefinition = "jsonb")
    private String chartData;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
}
