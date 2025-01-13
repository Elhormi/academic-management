package product.ma.resultservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import product.ma.resultservice.repository.ResultRepository;
import product.ma.resultservice.model.Result;

import java.util.List;
import java.util.Optional;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;

    // Enregistrer un résultat
    public ResponseEntity<?> createResult(Result result) {
        try {
            return new ResponseEntity<Result>(resultRepository.save(result), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Récupérer un résultat par ID
    public ResponseEntity<?> fetchResultById(Long id) {
        Optional<Result> result = resultRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Result not found", HttpStatus.NOT_FOUND);
        }
    }

    // Récupérer tous les résultats
    public ResponseEntity<?> fetchResults() {
        List<Result> results = resultRepository.findAll();
        if (results.size() > 0) {
            return new ResponseEntity<List<Result>>(results, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No results found", HttpStatus.NOT_FOUND);
        }
    }

    // Mettre à jour un résultat
    public ResponseEntity<?> updateResult(Long id, Result result) {
        Optional<Result> existingResult = resultRepository.findById(id);
        if (existingResult.isPresent()) {
            Result updatedResult = existingResult.get();
            updatedResult.setStudentId(result.getStudentId());
            updatedResult.setCourseId(result.getCourseId());
            updatedResult.setGrade(result.getGrade());
            updatedResult.setRemarks(result.getRemarks());
            return new ResponseEntity<Result>(resultRepository.save(updatedResult), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Result not found", HttpStatus.NOT_FOUND);
        }
    }

    // Supprimer un résultat
    public ResponseEntity<?> deleteResult(Long id) {
        try {
            resultRepository.deleteById(id);
            return new ResponseEntity<>("Result deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}