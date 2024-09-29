package co.renil.astro.kundli.repository;

import co.renil.astro.kundli.entity.HoroscopeDetails;
import co.renil.astro.kundli.entity.Kundli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HoroscopeDetailsRepository extends JpaRepository<HoroscopeDetails, UUID> {

    List<HoroscopeDetails> findAllByKundli(Kundli kundli);
}
