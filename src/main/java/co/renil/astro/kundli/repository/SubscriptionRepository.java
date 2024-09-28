package co.renil.astro.kundli.repository;

import co.renil.astro.kundli.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
    // You can add custom query methods here if needed
}
