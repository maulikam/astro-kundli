package co.renil.astro.kundli.repository;

import co.renil.astro.kundli.entity.UsersSavedKundli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersSavedKundliRepository extends JpaRepository<UsersSavedKundli, UUID> {
    // You can add custom query methods here if needed
}
