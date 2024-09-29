package co.renil.astro.kundli.service;

import co.renil.astro.kundli.entity.Kundli;
import co.renil.astro.kundli.repository.KundliRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KundliService {

    private final KundliRepository kundliRepository;

    // Create a new Kundli
    public Kundli createKundli(Kundli kundli) {
        return kundliRepository.save(kundli);
    }

    // Retrieve Kundli by ID
    public Optional<Kundli> getKundliById(UUID kundliId) {
        return kundliRepository.findById(kundliId);
    }

    // Update an existing Kundli
    public Kundli updateKundli(UUID kundliId, Kundli kundliDetails) {
        Optional<Kundli> existingKundli = kundliRepository.findById(kundliId);
        if (existingKundli.isPresent()) {
            Kundli kundli = existingKundli.get();
            // Update properties as needed
            kundli.setBhavaPredictions(kundliDetails.getBhavaPredictions());
            kundli.setNakshatra(kundliDetails.getNakshatra());
            kundli.setLatitude(kundliDetails.getLatitude());
            // Continue updating other fields...
            return kundliRepository.save(kundli);
        }
        throw new RuntimeException("Kundli not found with id " + kundliId);
    }

    // Delete a Kundli by ID
    public void deleteKundli(UUID kundliId) {
        kundliRepository.deleteById(kundliId);
    }

    // Get all Kundlis
    public List<Kundli> getAllKundlis() {
        return kundliRepository.findAll();
    }
}
