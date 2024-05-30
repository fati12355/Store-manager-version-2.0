package service;

import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository inventoryRepository;

    @Autowired
    public ProductService(ProductRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Product> getAllInventoryItems() {
        return inventoryRepository.findAll();
    }

    public Product getInventoryItemById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory item not found"));
    }

    public List<Product> searchInventoryByName(String name) {
        return inventoryRepository.findByNameContainingIgnoreCase(name);
    }

    public Product addInventoryItem(Product item) {
        // Additional logic before saving can be added here
        return inventoryRepository.save(item);
    }

    public Product updateInventoryItem(Long id, Product itemDetails) {
        return inventoryRepository.findById(id)
                .map(item -> {
                    item.setName(itemDetails.getName());
                    item.setQuantity(itemDetails.getQuantity());
                    item.setPrice(itemDetails.getPrice());
                    item.setWeight(itemDetails.getWeight());
                    item.setExpiryDate(itemDetails.getExpiryDate());
                    return inventoryRepository.save(item);
                })
                .orElseThrow(() -> new RuntimeException("Inventory item not found"));
    }

    public void deleteInventoryItem(Long id) {
        inventoryRepository.deleteById(id);
    }

    // More methods can be added as needed
}
