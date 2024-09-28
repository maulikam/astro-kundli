package co.renil.astro.kundli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.renil.astro.kundli.entity.DivisionalChart;
import java.util.UUID;

@Repository
public interface DivisionalChartRepository extends JpaRepository<DivisionalChart, UUID> {
    // You can add custom query methods here if needed
}
