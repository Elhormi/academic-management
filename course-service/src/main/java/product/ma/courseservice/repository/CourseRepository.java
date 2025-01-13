package product.ma.courseservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import product.ma.courseservice.model.Course;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
}