package edu.iu.jsinnett.c322spring2024homework2.repository;

import edu.iu.jsinnett.c322spring2024homework2.model.Guitar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Guitar, String> {
    List<Guitar> findByModelContainingIgnoreCase(String model);
}