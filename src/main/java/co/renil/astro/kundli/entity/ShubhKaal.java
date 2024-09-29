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
@Table(name = "shubh_kaal")
@EntityListeners(AuditingEntityListener.class)
public class ShubhKaal {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "date")
    private java.sql.Date date;

    @Column(name = "rahu_kaal")
    private java.sql.Time rahuKaal;

    @Column(name = "gulik")
    private java.sql.Time gulik;

    @Column(name = "mandi")
    private java.sql.Time mandi;

    @Convert(converter = JsonConverter.class)
    @Column(name = "hora", columnDefinition = "jsonb")
    private String hora;

    @Convert(converter = JsonConverter.class)
    @Column(name = "chogadia", columnDefinition = "jsonb")
    private String chogadia;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
}
