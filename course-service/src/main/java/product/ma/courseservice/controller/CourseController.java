package product.ma.courseservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.ma.courseservice.model.Course;
import product.ma.courseservice.service.CourseService;

@CrossOrigin("*")
@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    // Créer un cours
    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    // Récupérer un cours par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> fetchCourseById(@PathVariable String id) {
        return courseService.fetchCourseById(id);
    }

    // Récupérer tous les cours
    @GetMapping
    public ResponseEntity<?> fetchCourses() {
        return courseService.fetchCourses();
    }

    // Mettre à jour un cours
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable String id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    // Supprimer un cours
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable String id) {
        return courseService.deleteCourse(id);
    }
}