#!/bin/bash

# Directory for entities
ENTITY_DIR="src/main/java/co/renil/astro/kundli/entity"

# Create directory if it doesn't exist
mkdir -p $ENTITY_DIR

# Function to create an entity file
create_entity() {
    ENTITY_NAME=$1
    FILENAME="$ENTITY_DIR/$ENTITY_NAME.java"

    # Write entity to file
    cat <<EOF > $FILENAME
package co.renil.astro.kundli.entity;

import javax.persistence.*;
import java.util.UUID;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "${ENTITY_NAME,,}")
public class $ENTITY_NAME {

    @Id
    @GeneratedValue
    private UUID id;

EOF

    # Additional fields based on table name
    case $ENTITY_NAME in
    User)
        cat <<EOF >> $FILENAME
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phoneNumber;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

EOF
        ;;
    Person)
        cat <<EOF >> $FILENAME
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String fullName;

    private String gender;

    private String relationship;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

EOF
        ;;
    Kundli)
        cat <<EOF >> $FILENAME
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private LocalTime birthTime;

    private String birthPlace;

    private float latitude;

    private float longitude;

    private String timezone;

    private String nakshatra;

    private boolean manglikStatus;

    @Column(columnDefinition = "jsonb")
    private String chartData;

    @Column(columnDefinition = "jsonb")
    private String dasaPeriods;

    private String bhavaPredictions;

    private String language;

    private boolean sadeSatiStatus;

    private String ayanamsa;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

EOF
        ;;
    MatchMaking)
        cat <<EOF >> $FILENAME
    @ManyToOne
    @JoinColumn(name = "kundli_1_id", nullable = false)
    private Kundli kundli1;

    @ManyToOne
    @JoinColumn(name = "kundli_2_id", nullable = false)
    private Kundli kundli2;

    private int nakshatraCompatibilityScore;

    private boolean manglikCompatibility;

    private boolean dasaSandhiCompatibility;

    private int overallCompatibilityScore;

    private String language;

    @Column(nullable = false)
    private LocalDateTime createdAt;

EOF
        ;;
    Chart)
        cat <<EOF >> $FILENAME
    @ManyToOne
    @JoinColumn(name = "kundli_id", nullable = false)
    private Kundli kundli;

    private String chartType;

    @Column(columnDefinition = "jsonb")
    private String chartData;

    @Column(nullable = false)
    private LocalDateTime createdAt;

EOF
        ;;
    City)
        cat <<EOF >> $FILENAME
    @Column(nullable = false)
    private String cityName;

    @Column(nullable = false)
    private String country;

    private float latitude;

    private float longitude;

    private String timezone;

EOF
        ;;
    esac

    # Closing brace for the class
    echo "}" >> $FILENAME

    echo "Created entity: $FILENAME"
}

# List of entities to be created
entities=("User" "Person" "Kundli" "MatchMaking" "Chart" "City")

# Loop through each entity and create the corresponding Java file
for entity in "${entities[@]}"; do
    create_entity $entity
done
