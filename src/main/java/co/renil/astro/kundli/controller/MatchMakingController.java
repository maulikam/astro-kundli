package co.renil.astro.kundli.controller;

import co.renil.astro.kundli.entity.MatchMaking;
import co.renil.astro.kundli.service.MatchMakingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/matchmaking")
@RequiredArgsConstructor
public class MatchMakingController {

    private final MatchMakingService matchMakingService;

    /**
     * Endpoint to create a new match-making record between two Kundlis.
     * @param kundli1Id The ID of the first Kundli.
     * @param kundli2Id The ID of the second Kundli.
     * @return ResponseEntity containing the MatchMaking result.
     */
    @PostMapping("/match")
    public ResponseEntity<MatchMaking> matchKundlis(
            @RequestParam UUID kundli1Id,
            @RequestParam UUID kundli2Id) {
        MatchMaking matchMaking = matchMakingService.matchKundlis(kundli1Id, kundli2Id);
        return ResponseEntity.ok(matchMaking);
    }

    /**
     * Endpoint to retrieve a match-making result by its ID.
     * @param id The ID of the match-making record.
     * @return ResponseEntity containing the MatchMaking result.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MatchMaking> getMatchMakingResult(@PathVariable UUID id) {
        Optional<MatchMaking> matchMaking = matchMakingService.getMatchMakingResult(id);
        return matchMaking
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
