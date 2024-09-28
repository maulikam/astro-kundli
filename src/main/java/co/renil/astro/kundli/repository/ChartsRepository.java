package co.renil.astro.kundli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.renil.astro.kundli.entity.Charts;
import java.util.UUID;

@Repository
public interface ChartsRepository extends JpaRepository<Charts, UUID> {
    // You can add custom query methods here if needed
}
