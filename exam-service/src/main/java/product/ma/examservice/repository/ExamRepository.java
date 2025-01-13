package product.ma.examservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import product.ma.examservice.model.Exam;

@Repository
public interface ExamRepository extends MongoRepository<Exam, String> {
}