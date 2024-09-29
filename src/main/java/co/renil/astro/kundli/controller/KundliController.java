package co.renil.astro.kundli.controller;

import co.renil.astro.kundli.entity.Kundli;
import co.renil.astro.kundli.service.KundliService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/kundlis")
@RequiredArgsConstructor
public class KundliController {

    private final KundliService kundliService;

    @PostMapping
    public ResponseEntity<Kundli> createKundli(@RequestBody Kundli kundli) {
        Kundli newKundli = kundliService.createKundli(kundli);
        return ResponseEntity.status(HttpStatus.CREATED).body(newKundli);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kundli> getKundliById(@PathVariable UUID id) {
        Optional<Kundli> kundli = kundliService.getKundliById(id);
        return kundli.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kundli> updateKundli(@PathVariable UUID id, @RequestBody Kundli kundliDetails) {
        Kundli updatedKundli = kundliService.updateKundli(id, kundliDetails);
        return ResponseEntity.ok(updatedKundli);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKundli(@PathVariable UUID id) {
        kundliService.deleteKundli(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Kundli>> getAllKundlis() {
        List<Kundli> kundlis = kundliService.getAllKundlis();
        return ResponseEntity.ok(kundlis);
    }
}
