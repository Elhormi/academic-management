package product.ma.courseservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import product.ma.courseservice.repository.CourseRepository;
import product.ma.courseservice.model.Course;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    // Créer un cours
    public ResponseEntity<?> createCourse(Course course) {
        try {
            return new ResponseEntity<Course>(courseRepository.save(course), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Récupérer un cours par ID
    public ResponseEntity<?> fetchCourseById(String id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            return new ResponseEntity<>(course.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }
    }

    // Récupérer tous les cours
    public ResponseEntity<?> fetchCourses() {
        List<Course> courses = courseRepository.findAll();
        if (courses.size() > 0) {
            return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No courses found", HttpStatus.NOT_FOUND);
        }
    }

    // Mettre à jour un cours
    public ResponseEntity<?> updateCourse(String id, Course course) {
        Optional<Course> existingCourse = courseRepository.findById(id);
        if (existingCourse.isPresent()) {
            Course updatedCourse = existingCourse.get();
            updatedCourse.setName(course.getName());
            updatedCourse.setDescription(course.getDescription());
            updatedCourse.setRoom(course.getRoom());
            updatedCourse.setSchedule(course.getSchedule());
            return new ResponseEntity<Course>(courseRepository.save(updatedCourse), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }
    }

    // Supprimer un cours
    public ResponseEntity<?> deleteCourse(String id) {
        try {
            courseRepository.deleteById(id);
            return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}