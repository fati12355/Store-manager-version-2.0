package service;

import entity.Operations;
import repository.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationsService {

    private final OperationsRepository operationsRepository;

    @Autowired
    public OperationsService(OperationsRepository operationsRepository) {
        this.operationsRepository = operationsRepository;
    }

    public List<Operations> getAllOperations() {
        return operationsRepository.findAll();
    }

    public Optional<Operations> getOperationById(Long id) {
        return operationsRepository.findById(id);
    }

    public Operations saveOperation(Operations operation) {
        //Add any logic needed before saving the operation
        return operationsRepository.save(operation);
    }

    public Operations updateOperation(Long id, Operations operationDetails) {
        return operationsRepository.findById(id)
                .map(operation -> {
                    operation.setProduct(operationDetails.getProduct());
                    operation.setOperator(operationDetails.getOperator());
                    operation.setOperationType(operationDetails.getOperationType());
                    operation.setQuantity(operationDetails.getQuantity());
                    operation.setPrice(operationDetails.getPrice());
                    operation.setWeight(operationDetails.getWeight());
                    operation.setExpiryDate(operationDetails.getExpiryDate());
                    operation.setTimestamp(operationDetails.getTimestamp());
                    return operationsRepository.save(operation);
                })
                .orElseThrow(() -> new IllegalStateException("Operation with id " + id + " not found"));
    }

    public void deleteOperation(Long id) {
        operationsRepository.deleteById(id);
    }

    // Additional methods for business logic
}
