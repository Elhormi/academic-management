package product.ma.teacherservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import product.ma.teacherservice.repository.TeacherRepository;
import product.ma.teacherservice.model.Teacher;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    // Créer un enseignant
    public ResponseEntity<?> createTeacher(Teacher teacher) {
        try {
            return new ResponseEntity<Teacher>(teacherRepository.save(teacher), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Récupérer un enseignant par ID
    public ResponseEntity<?> fetchTeacherById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent()) {
            return new ResponseEntity<>(teacher.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Teacher not found", HttpStatus.NOT_FOUND);
        }
    }

    // Récupérer tous les enseignants
    public ResponseEntity<?> fetchTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        if (teachers.size() > 0) {
            return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No teachers found", HttpStatus.NOT_FOUND);
        }
    }

    // Mettre à jour un enseignant
    public ResponseEntity<?> updateTeacher(Long id, Teacher teacher) {
        Optional<Teacher> existingTeacher = teacherRepository.findById(id);
        if (existingTeacher.isPresent()) {
            Teacher updatedTeacher = existingTeacher.get();
            updatedTeacher.setName(teacher.getName());
            updatedTeacher.setEmail(teacher.getEmail());
            updatedTeacher.setSubject(teacher.getSubject());
            updatedTeacher.setAvailability(teacher.getAvailability());
            return new ResponseEntity<Teacher>(teacherRepository.save(updatedTeacher), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Teacher not found", HttpStatus.NOT_FOUND);
        }
    }

    // Supprimer un enseignant
    public ResponseEntity<?> deleteTeacher(Long id) {
        try {
            teacherRepository.deleteById(id);
            return new ResponseEntity<>("Teacher deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}