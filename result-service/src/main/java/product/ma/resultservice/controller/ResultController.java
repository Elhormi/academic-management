package product.ma.resultservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.ma.resultservice.model.Result;
import product.ma.resultservice.service.ResultService;

@CrossOrigin("*")
@RestController
@RequestMapping("/results")
public class ResultController {
    @Autowired
    private ResultService resultService;

    // Enregistrer un résultat
    @PostMapping
    public ResponseEntity<?> createResult(@RequestBody Result result) {
        return resultService.createResult(result);
    }

    // Récupérer un résultat par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> fetchResultById(@PathVariable Long id) {
        return resultService.fetchResultById(id);
    }

    // Récupérer tous les résultats
    @GetMapping
    public ResponseEntity<?> fetchResults() {
        return resultService.fetchResults();
    }

    // Mettre à jour un résultat
    @PutMapping("/{id}")
    public ResponseEntity<?> updateResult(@PathVariable Long id, @RequestBody Result result) {
        return resultService.updateResult(id, result);
    }

    // Supprimer un résultat
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResult(@PathVariable Long id) {
        return resultService.deleteResult(id);
    }
}