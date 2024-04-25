package edu.iu.jsinnett.c322spring2024homework2.controller;

import edu.iu.jsinnett.c322spring2024homework2.model.Guitar;
import edu.iu.jsinnett.c322spring2024homework2.repository.InventoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/guitars")
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping("/search")
    public List<Guitar> search(@RequestParam(required = false) String model) {
        return inventoryRepository.findByModelContainingIgnoreCase(model);
    }

    @PostMapping("/add")
    public Guitar add(@RequestBody Guitar guitar) {
        return inventoryRepository.save(guitar);
    }


    @GetMapping("/find/{serialNumber}")
    public Guitar find(@PathVariable String serialNumber) {
        return inventoryRepository.findById(serialNumber).orElse(null);
    }
}
