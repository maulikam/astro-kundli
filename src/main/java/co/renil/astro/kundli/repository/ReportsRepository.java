package co.renil.astro.kundli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.renil.astro.kundli.entity.Reports;
import java.util.UUID;

@Repository
public interface ReportsRepository extends JpaRepository<Reports, UUID> {
    // You can add custom query methods here if needed
}
