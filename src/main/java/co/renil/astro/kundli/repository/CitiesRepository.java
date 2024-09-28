package co.renil.astro.kundli.repository;

import co.renil.astro.kundli.entity.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CitiesRepository extends JpaRepository<Cities, UUID> {
    // You can add custom query methods here if needed
}
