package co.renil.astro.kundli.service;

import co.renil.astro.kundli.entity.Cities;
import co.renil.astro.kundli.repository.CitiesRepository;
import lombok.RequiredArgsConstructor;
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
     *
     * @param city City entity to be saved.
     * @return Saved City entity.
     */
    public Cities createOrUpdateCity(Cities city) {
        return citiesRepository.save(city);
    }

    /**
     * Retrieve a city by its ID.
     *
     * @param cityId UUID of the city.
     * @return Optional containing the City entity, if found.
     */
    public Optional<Cities> getCityById(UUID cityId) {
        return citiesRepository.findById(cityId);
    }

    /**
     * Retrieve a city by its name.
     *
     * @param cityName The name of the city.
     * @return Optional containing the City entity, if found.
     */
    public Optional<Cities> getCityByName(String cityName) {
        return citiesRepository.findByCityName(cityName);
    }

    /**
     * Get all cities.
     *
     * @return List of all City entities.
     */
    public List<Cities> getAllCities() {
        return citiesRepository.findAll();
    }

    /**
     * Delete a city by its ID.
     *
     * @param cityId UUID of the city to be deleted.
     */
    public void deleteCity(UUID cityId) {
        citiesRepository.deleteById(cityId);
    }
}
