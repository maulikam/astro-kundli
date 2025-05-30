package co.renil.astro.kundli.repository;

import co.renil.astro.kundli.entity.Transit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransitRepository extends JpaRepository<Transit, UUID> {
    // You can add custom query methods here if needed
}
