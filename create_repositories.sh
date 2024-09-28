#!/bin/bash

# Define the package names
ENTITY_PACKAGE="co.renil.astro.kundli.entity"
REPO_PACKAGE="co.renil.astro.kundli.repository"

# Define the directories
ENTITY_DIR="src/main/java/co/renil/astro/kundli/entity"
REPO_DIR="src/main/java/co/renil/astro/kundli/repository"

# Create the repository directory if it doesn't exist
mkdir -p "$REPO_DIR"

# Loop through all Java files in the entity directory
for file in $ENTITY_DIR/*.java; do
    # Extract the class name (remove path and .java extension)
    class_name=$(basename "$file" .java)

    # Create the repository interface file
    cat > "$REPO_DIR/${class_name}Repository.java" << EOF
package $REPO_PACKAGE;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import $ENTITY_PACKAGE.$class_name;
import java.util.UUID;

@Repository
public interface ${class_name}Repository extends JpaRepository<$class_name, UUID> {
    // You can add custom query methods here if needed
}
EOF

    echo "Created ${class_name}Repository.java"
done

echo "Repository interfaces created successfully!"