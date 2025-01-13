package product.ma.studentservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import product.ma.studentservice.model.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
}