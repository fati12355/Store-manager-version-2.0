package repository;

import entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom query to find Inventory items by name containing a string (case insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);

    // more custom queries to be added
}
