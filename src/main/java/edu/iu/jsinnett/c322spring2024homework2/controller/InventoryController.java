package edu.iu.jsinnett.c322spring2024homework2.controller;

import edu.iu.jsinnett.c322spring2024homework2.enums.Builder;
import edu.iu.jsinnett.c322spring2024homework2.enums.Type;
import edu.iu.jsinnett.c322spring2024homework2.enums.Wood;
import edu.iu.jsinnett.c322spring2024homework2.model.Guitar;
import edu.iu.jsinnett.c322spring2024homework2.repository.InventoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guitars")
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping("/search")
    public List<Guitar> search(@RequestParam(required = false) String serialNumber,
                               @RequestParam(required = false) Double price,
                               @RequestParam(required = false) Builder builder,
                               @RequestParam(required = false) Type type,
                               @RequestParam(required = false) String model,
                               @RequestParam(required = false) Wood backWood,
                               @RequestParam(required = false) Wood topWood) {
        // Create a new Guitar object using enums for builder, type, backWood, and topWood
        Guitar searchCriteria = new Guitar(serialNumber, price, builder, type, model, backWood, topWood);
        return inventoryRepository.search(searchCriteria);
    }

    @PostMapping("/add")
    public boolean add(@RequestBody Guitar guitar) {
        return inventoryRepository.addGuitar(guitar);
    }

    @GetMapping("/find/{serialNumber}")
    public Guitar find(@PathVariable String serialNumber) {
        return inventoryRepository.getGuitar(serialNumber);
    }
}
