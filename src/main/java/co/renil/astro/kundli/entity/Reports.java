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
@Table(name = "reports")
@EntityListeners(AuditingEntityListener.class)
public class Reports {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "kundli_id", nullable = false)
    private Kundli kundli;

    @Type(JsonBinaryType.class)
    @Column(name = "report_data", columnDefinition = "jsonb")
    private String reportData;

    @Column(name = "party_name")
    private String partyName;

    @Column(name = "party_address")
    private String partyAddress;

    @Column(name = "print_style")
    private String printStyle;

    @Column(name = "language")
    private String language;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
}

