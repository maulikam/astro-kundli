package co.renil.astro.kundli.service;

import co.renil.astro.kundli.entity.Cities;
import co.renil.astro.kundli.repository.CitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CitiesRepository citiesRepository;

    /**
     * Create a new city or update an existing one.
     * If a new city is created or an existing one is updated, the cache will be evicted.
     *
     * @param city City entity to be saved.
     * @return Saved City entity.
     */
    @CacheEvict(value = {"cityCache", "cityByNameCache", "citiesCache"}, allEntries = true)
    public Cities createOrUpdateCity(Cities city) {
        return citiesRepository.save(city);
    }

    /**
     * Retrieve a city by its ID.
     * The result is cached to improve performance.
     *
     * @param cityId UUID of the city.
     * @return Optional containing the City entity, if found.
     */
    @Cacheable(value = "cityCache", key = "#cityId")
    public Optional<Cities> getCityById(UUID cityId) {
        return citiesRepository.findById(cityId);
    }

    /**
     * Retrieve a city by its name.
     * The result is cached to improve performance.
     *
     * @param cityName The name of the city.
     * @return Optional containing the City entity, if found.
     */
    @Cacheable(value = "cityByNameCache", key = "#cityName")
    public Optional<Cities> getCityByName(String cityName) {
        return citiesRepository.findByCityName(cityName);
    }

    /**
     * Get all cities.
     * The result is cached to improve performance.
     *
     * @return List of all City entities.
     */
    @Cacheable(value = "citiesCache")
    public List<Cities> getAllCities() {
        return citiesRepository.findAll();
    }

    /**
     * Delete a city by its ID.
     * If a city is deleted, the cache will be evicted.
     *
     * @param cityId UUID of the city to be deleted.
     */
    @CacheEvict(value = {"cityCache", "cityByNameCache", "citiesCache"}, allEntries = true)
    public void deleteCity(UUID cityId) {
        citiesRepository.deleteById(cityId);
    }
}
