package product.ma.examservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import product.ma.examservice.repository.ExamRepository;
import product.ma.examservice.model.Exam;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    // Créer un examen
    public ResponseEntity<?> createExam(Exam exam) {
        try {
            return new ResponseEntity<Exam>(examRepository.save(exam), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Récupérer un examen par ID
    public ResponseEntity<?> fetchExamById(String id) {
        Optional<Exam> exam = examRepository.findById(id);
        if (exam.isPresent()) {
            return new ResponseEntity<>(exam.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Exam not found", HttpStatus.NOT_FOUND);
        }
    }

    // Récupérer tous les examens
    public ResponseEntity<?> fetchExams() {
        List<Exam> exams = examRepository.findAll();
        if (exams.size() > 0) {
            return new ResponseEntity<List<Exam>>(exams, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No exams found", HttpStatus.NOT_FOUND);
        }
    }

    // Mettre à jour un examen
    public ResponseEntity<?> updateExam(String id, Exam exam) {
        Optional<Exam> existingExam = examRepository.findById(id);
        if (existingExam.isPresent()) {
            Exam updatedExam = existingExam.get();
            updatedExam.setName(exam.getName());
            updatedExam.setDate(exam.getDate());
            updatedExam.setRoom(exam.getRoom());
            updatedExam.setSupervisors(exam.getSupervisors());
            updatedExam.setExamPapers(exam.getExamPapers());
            return new ResponseEntity<Exam>(examRepository.save(updatedExam), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Exam not found", HttpStatus.NOT_FOUND);
        }
    }

    // Supprimer un examen
    public ResponseEntity<?> deleteExam(String id) {
        try {
            examRepository.deleteById(id);
            return new ResponseEntity<>("Exam deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}