package co.renil.astro.kundli.repository;

import co.renil.astro.kundli.entity.MatchMaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MatchMakingRepository extends JpaRepository<MatchMaking, UUID> {
    // You can add custom query methods here if needed
}
