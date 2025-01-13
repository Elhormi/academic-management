package product.ma.examservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.ma.examservice.model.Exam;
import product.ma.examservice.service.ExamService;

@CrossOrigin("*")
@RestController
@RequestMapping("/exams")
public class ExamController {
    @Autowired
    private ExamService examService;

    // Créer un examen
    @PostMapping
    public ResponseEntity<?> createExam(@RequestBody Exam exam) {
        return examService.createExam(exam);
    }

    // Récupérer un examen par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> fetchExamById(@PathVariable String id) {
        return examService.fetchExamById(id);
    }

    // Récupérer tous les examens
    @GetMapping
    public ResponseEntity<?> fetchExams() {
        return examService.fetchExams();
    }

    // Mettre à jour un examen
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExam(@PathVariable String id, @RequestBody Exam exam) {
        return examService.updateExam(id, exam);
    }

    // Supprimer un examen
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExam(@PathVariable String id) {
        return examService.deleteExam(id);
    }
}