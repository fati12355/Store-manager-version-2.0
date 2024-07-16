package repository;

import entity.Operations;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OperationsRepository extends JpaRepository<Operations, Long> {
}
