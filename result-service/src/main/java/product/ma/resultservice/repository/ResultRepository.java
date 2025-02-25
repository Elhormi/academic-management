package product.ma.resultservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product.ma.resultservice.model.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
}   