package product.ma.batchservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product.ma.batchservice.model.ReportCard;

@Repository
public interface ReportCardRepository extends JpaRepository<ReportCard, Long> {
}