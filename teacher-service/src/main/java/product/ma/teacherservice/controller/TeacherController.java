package product.ma.teacherservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.ma.teacherservice.model.Teacher;
import product.ma.teacherservice.service.TeacherService;

@CrossOrigin("*")
@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    // Créer un enseignant
    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    // Récupérer un enseignant par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> fetchTeacherById(@PathVariable Long id) {
        return teacherService.fetchTeacherById(id);
    }

    // Récupérer tous les enseignants
    @GetMapping
    public ResponseEntity<?> fetchTeachers() {
        return teacherService.fetchTeachers();
    }

    // Mettre à jour un enseignant
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        return teacherService.updateTeacher(id, teacher);
    }

    // Supprimer un enseignant
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        return teacherService.deleteTeacher(id);
    }
}