package co.renil.astro.kundli.controller;

import co.renil.astro.kundli.entity.Cities;
import co.renil.astro.kundli.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    /**
     * Create or update a city.
     *
     * @param city The city entity to create or update.
     * @return The saved city entity.
     */
    @PostMapping
    public ResponseEntity<Cities> createOrUpdateCity(@RequestBody Cities city) {
        Cities newCity = cityService.createOrUpdateCity(city);
        return ResponseEntity.ok(newCity);
    }

    /**
     * Retrieve a city by its ID.
     *
     * @param id UUID of the city.
     * @return The city entity, if found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cities> getCityById(@PathVariable UUID id) {
        Optional<Cities> city = cityService.getCityById(id);
        return city.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieve a city by its name.
     *
     * @param name The name of the city.
     * @return The city entity, if found.
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<Cities> getCityByName(@PathVariable String name) {
        Optional<Cities> city = cityService.getCityByName(name);
        return city.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Get all cities.
     *
     * @return List of all city entities.
     */
    @GetMapping
    public ResponseEntity<List<Cities>> getAllCities() {
        List<Cities> cities = cityService.getAllCities();
        return ResponseEntity.ok(cities);
    }

    /**
     * Delete a city by its ID.
     *
     * @param id UUID of the city to delete.
     * @return Response indicating the status of the deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable UUID id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}
