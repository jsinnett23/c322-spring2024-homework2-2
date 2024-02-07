package edu.iu.jsinnett.c322spring2024homework2.repository;


import edu.iu.jsinnett.c322spring2024homework2.enums.Builder;
import edu.iu.jsinnett.c322spring2024homework2.enums.Type;
import edu.iu.jsinnett.c322spring2024homework2.enums.Wood;
import edu.iu.jsinnett.c322spring2024homework2.model.Guitar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InventoryRepository {

    private String databasePath = "guitars_database.txt";

    public InventoryRepository(@Value("${inventory.database.path}") String databasePath) {
        this.databasePath = databasePath;
    }



    public synchronized boolean addGuitar(Guitar guitar) {
        try (FileWriter fw = new FileWriter(databasePath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(guitar.toString());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public Guitar getGuitar(String serialNumber) {
        try (BufferedReader br = new BufferedReader(new FileReader(databasePath))) {
            return br.lines()
                    .map(this::stringToGuitar)
                    .filter(g -> g.getSerialNumber().equals(serialNumber))
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            return null;
        }
    }

    public List<Guitar> search(Guitar searchCriteria) {
        try (BufferedReader br = new BufferedReader(new FileReader(databasePath))) {
            return br.lines()
                    .map(this::stringToGuitar)
                    .filter(g -> g.matches(searchCriteria))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private Guitar stringToGuitar(String str) {
        String[] props = str.split(",");
        if (props.length < 7) {
            throw new IllegalArgumentException("Invalid guitar string: " + str);
        }
        return new Guitar(
                props[0], // serialNumber
                Double.parseDouble(props[1]), // price
                Builder.valueOf(props[2].toUpperCase()), // builder
                Type.valueOf(props[3].toUpperCase()), // type
                props[4], // model
                Wood.valueOf(props[5].toUpperCase()), // backWood
                Wood.valueOf(props[6].toUpperCase()) // topWood
        );
    }
}
