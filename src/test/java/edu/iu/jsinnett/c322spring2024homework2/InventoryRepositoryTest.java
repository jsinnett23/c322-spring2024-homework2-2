package edu.iu.jsinnett.c322spring2024homework2;

import edu.iu.jsinnett.c322spring2024homework2.model.Guitar;
import edu.iu.jsinnett.c322spring2024homework2.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryRepositoryTest {

    private String databasePath = "temp_guitars_database.txt"; // simulate the file with a temporary file name

    @Test
    void addGuitar() throws IOException {
        // arrange
        Path tempFile = Files.createTempFile(databasePath, ".txt"); // create a temp file
        InventoryRepository inventoryRepository = new InventoryRepository(tempFile.toString()); // ese temp file for the repository
        Guitar guitar = new Guitar("SN124", 1299.99, "Gibson", "Electric", "Les Paul", "Maple", "Maple");
        String expectedOutput = guitar.toString() + System.lineSeparator(); // expected output to write in the file

        // act
        boolean isGuitarAdded = inventoryRepository.addGuitar(guitar); // Attempt to add guitar

        // assert
        assertTrue(isGuitarAdded, "Guitar should be added successfully");

        // read from the temp file and check if the guitar was added
        String fileContent = Files.readString(tempFile);
        assertTrue(fileContent.contains(expectedOutput), "File should contain the added guitar details.");

        // Cleanup
        Files.delete(tempFile); // clean up the temporary file
    }


    @Test
    void getGuitar() throws IOException {
        // arrange
        Path tempFile = Files.createTempFile(databasePath, ".txt");
        InventoryRepository inventoryRepository = new InventoryRepository(tempFile.toString());
        Guitar guitar = new Guitar("SN123", 999.99, "Fender", "Acoustic", "ModelX", "Mahogany", "Spruce");
        String guitarString = guitar.toString() + System.lineSeparator();
        Files.writeString(tempFile, guitarString);

        // act
        Guitar foundGuitar = inventoryRepository.getGuitar("SN123");

        // assert
        assertNotNull(foundGuitar, "Guitar should be found");
        assertEquals("SN123", foundGuitar.getSerialNumber(), "Serial number should match");

        // cleanup
        Files.delete(tempFile);
    }
    @Test
    void searchGuitars() throws IOException {
        // arrange
        Path tempFile = Files.createTempFile(databasePath, ".txt");
        InventoryRepository inventoryRepository = new InventoryRepository(tempFile.toString());

        // add multiple guitars to the file
        Guitar guitar1 = new Guitar("SN123", 999.99, "Fender", "Acoustic", "ModelX", "Mahogany", "Spruce");
        Guitar guitar2 = new Guitar("SN124", 1299.99, "Gibson", "Electric", "Les Paul", "Maple", "Maple");
        Guitar guitar3 = new Guitar("SN125", 1099.99, "Fender", "Electric", "Stratocaster", "Alder", "Maple");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile)) {
            writer.write(guitar1.toString() + System.lineSeparator());
            writer.write(guitar2.toString() + System.lineSeparator());
            writer.write(guitar3.toString() + System.lineSeparator());
        }

        // act
        Guitar searchCriteria = new Guitar(null, 0, "Fender", null, null, null, null);
        List<Guitar> foundGuitars = inventoryRepository.search(searchCriteria);

        // assert
        assertEquals(2, foundGuitars.size(), "Should find two Fender guitars");
        assertTrue(foundGuitars.contains(guitar1), "Should contain first Fender guitar");
        assertTrue(foundGuitars.contains(guitar3), "Should contain second Fender guitar");

        // cleanup
        Files.delete(tempFile);
    }

    //one more add test

    @Test
    void addGetSearchGuitars() throws IOException {
        // arrange
        Path tempFile = Files.createTempFile(databasePath, ".txt");
        InventoryRepository inventoryRepository = new InventoryRepository(tempFile.toString());

        Guitar guitar1 = new Guitar("SN123", 999.99, "Fender", "Acoustic", "ModelX", "Mahogany", "Spruce");
        Guitar guitar2 = new Guitar("SN124", 1299.99, "Gibson", "Electric", "Les Paul", "Maple", "Maple");
        Guitar guitar3 = new Guitar("SN125", 1099.99, "Fender", "Electric", "Stratocaster", "Alder", "Maple");

        // add Guitars
        assertTrue(inventoryRepository.addGuitar(guitar1), "Guitar 1 should be added successfully");
        assertTrue(inventoryRepository.addGuitar(guitar2), "Guitar 2 should be added successfully");
        assertTrue(inventoryRepository.addGuitar(guitar3), "Guitar 3 should be added successfully");

        // get Guitar
        Guitar foundGuitar = inventoryRepository.getGuitar("SN123");
        assertNotNull(foundGuitar, "Guitar should be found");
        assertEquals("SN123", foundGuitar.getSerialNumber(), "Serial number of found guitar should match");

        // search Guitars
        Guitar searchCriteria = new Guitar(null, 0, "Fender", null, null, null, null);
        List<Guitar> foundGuitars = inventoryRepository.search(searchCriteria);
        assertEquals(2, foundGuitars.size(), "Should find two Fender guitars");
        assertTrue(foundGuitars.contains(guitar1), "Should contain first Fender guitar");
        assertTrue(foundGuitars.contains(guitar3), "Should contain second Fender guitar");

        // cleanup
        Files.delete(tempFile);
    }

}