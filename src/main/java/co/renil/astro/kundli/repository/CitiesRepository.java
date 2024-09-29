package co.renil.astro.kundli.repository;

import co.renil.astro.kundli.entity.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CitiesRepository extends JpaRepository<Cities, UUID> {

    /**
     * Custom query method to find a city by its name.
     *
     * @param cityName The name of the city to search for.
     * @return Optional containing the City entity, if found.
     */
    Optional<Cities> findByCityName(String cityName);
}
