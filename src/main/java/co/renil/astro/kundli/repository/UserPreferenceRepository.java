package co.renil.astro.kundli.repository;

import co.renil.astro.kundli.entity.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreference, UUID> {
    // You can add custom query methods here if needed
}
