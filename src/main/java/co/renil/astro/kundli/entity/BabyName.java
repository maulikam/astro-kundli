package co.renil.astro.kundli.entity;

package co.renil.astro.kundli.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "baby_names")
public class BabyName {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "meaning")
    private String meaning;

    @Column(name = "language")
    private String language;
}

