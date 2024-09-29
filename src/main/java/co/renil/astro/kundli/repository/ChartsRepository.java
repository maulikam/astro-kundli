package co.renil.astro.kundli.repository;

import co.renil.astro.kundli.entity.Charts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

import java.util.List;

public interface ChartsRepository extends JpaRepository<Charts, UUID> {
    List<Charts> findByKundliId(UUID kundliId);
}

