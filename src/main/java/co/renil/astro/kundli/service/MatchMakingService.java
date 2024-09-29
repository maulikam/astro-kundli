package co.renil.astro.kundli.service;

import co.renil.astro.kundli.entity.Kundli;
import co.renil.astro.kundli.entity.MatchMaking;
import co.renil.astro.kundli.repository.KundliRepository;
import co.renil.astro.kundli.repository.MatchMakingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MatchMakingService {

    private final MatchMakingRepository matchMakingRepository;
    private final KundliRepository kundliRepository;

    /**
     * Match compatibility between two Kundlis.
     * This will calculate compatibility based on Nakshatra, Manglik status, etc.
     *
     * @param kundli1Id UUID of the first Kundli.
     * @param kundli2Id UUID of the second Kundli.
     * @return MatchMaking entity containing the compatibility result.
     */
    @Transactional
    public MatchMaking matchKundlis(UUID kundli1Id, UUID kundli2Id) {
        Optional<Kundli> optionalKundli1 = kundliRepository.findById(kundli1Id);
        Optional<Kundli> optionalKundli2 = kundliRepository.findById(kundli2Id);

        if (optionalKundli1.isEmpty() || optionalKundli2.isEmpty()) {
            throw new IllegalArgumentException("One or both Kundlis not found");
        }

        Kundli kundli1 = optionalKundli1.get();
        Kundli kundli2 = optionalKundli2.get();

        // Calculate compatibility based on Nakshatra, Manglik, etc.
        int nakshatraCompatibilityScore = calculateNakshatraCompatibility(kundli1, kundli2);
        boolean manglikCompatibility = checkManglikCompatibility(kundli1, kundli2);
        boolean dasaSandhiCompatibility = checkDasaSandhiCompatibility(kundli1, kundli2);
        int overallCompatibilityScore = calculateOverallCompatibilityScore(nakshatraCompatibilityScore, manglikCompatibility, dasaSandhiCompatibility);

        // Create and save the MatchMaking entity
        MatchMaking matchMaking = new MatchMaking();
        matchMaking.setKundli1(kundli1);
        matchMaking.setKundli2(kundli2);
        matchMaking.setNakshatraCompatibilityScore(nakshatraCompatibilityScore);
        matchMaking.setManglikCompatibility(manglikCompatibility);
        matchMaking.setDasaSandhiCompatibility(dasaSandhiCompatibility);
        matchMaking.setOverallCompatibilityScore(overallCompatibilityScore);
        matchMaking.setLanguage("English");  // You can customize this or pass as a parameter

        return matchMakingRepository.save(matchMaking);
    }

    /**
     * Calculate Nakshatra compatibility score between two Kundlis.
     *
     * @param kundli1 The first Kundli.
     * @param kundli2 The second Kundli.
     * @return Compatibility score as an integer.
     */
    private int calculateNakshatraCompatibility(Kundli kundli1, Kundli kundli2) {
        // Placeholder logic for nakshatra compatibility calculation
        // You would add your actual astrology logic here
        return 80; // Assume a score out of 100 for demo purposes
    }

    /**
     * Check Manglik compatibility between two Kundlis.
     *
     * @param kundli1 The first Kundli.
     * @param kundli2 The second Kundli.
     * @return True if Mangliks are compatible.
     */
    private boolean checkManglikCompatibility(Kundli kundli1, Kundli kundli2) {
        // Placeholder logic to check manglik compatibility
        return kundli1.getManglikStatus() == kundli2.getManglikStatus();
    }

    /**
     * Check Dasa Sandhi compatibility between two Kundlis.
     *
     * @param kundli1 The first Kundli.
     * @param kundli2 The second Kundli.
     * @return True if dasa sandhi periods are compatible.
     */
    private boolean checkDasaSandhiCompatibility(Kundli kundli1, Kundli kundli2) {
        // Placeholder logic for dasa sandhi compatibility
        return true; // Assume compatible for now
    }

    /**
     * Calculate the overall compatibility score.
     *
     * @param nakshatraScore Nakshatra compatibility score.
     * @param manglikCompatibility Manglik compatibility status.
     * @param dasaSandhiCompatibility Dasa Sandhi compatibility status.
     * @return Overall compatibility score as an integer.
     */
    private int calculateOverallCompatibilityScore(int nakshatraScore, boolean manglikCompatibility, boolean dasaSandhiCompatibility) {
        int score = nakshatraScore;

        // Adjust score based on Manglik and Dasa Sandhi compatibility
        if (!manglikCompatibility) {
            score -= 10;
        }
        if (!dasaSandhiCompatibility) {
            score -= 5;
        }

        return Math.max(score, 0); // Ensure score is not negative
    }

    /**
     * Retrieve MatchMaking result by ID.
     *
     * @param matchMakingId The ID of the matchmaking record.
     * @return MatchMaking entity.
     */
    public Optional<MatchMaking> getMatchMakingResult(UUID matchMakingId) {
        return matchMakingRepository.findById(matchMakingId);
    }
}
