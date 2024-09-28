package co.renil.astro.kundli.repository;

import co.renil.astro.kundli.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    // You can add custom query methods here if needed
    Optional<User> findByEmail(String email);

}
