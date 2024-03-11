package edu.iu.jsinnett.c322spring2024homework2.model;
import edu.iu.jsinnett.c322spring2024homework2.enums.Builder;
import edu.iu.jsinnett.c322spring2024homework2.enums.Type;
import edu.iu.jsinnett.c322spring2024homework2.enums.Wood;

import java.util.Locale;
import java.util.Objects;

public class Guitar {
    String serialNumber;
    double price;
    Builder builder;
    Type type;
    String model;
    Wood backWood;
    Wood topWood;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Builder getBuilder() {
        return builder;
    }
    public void setBuilder(Builder builder) {
        this.builder = builder;
    }
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public Wood getBackWood() {
        return backWood;
    }
    public void setBackWood(Wood backWood) {
        this.backWood = backWood;
    }
    public Wood getTopWood() {
        return topWood;
    }
    public void setTopWood(Wood topWood) {
        this.topWood = topWood;
    }
    public Guitar(String serialNumber, double price, Builder builder, Type type, String model, Wood backWood, Wood topWood) {
        this.serialNumber = serialNumber;
        this.price = price;
        this.builder = builder;
        this.type = type;
        this.model = model;
        this.backWood = backWood;
        this.topWood = topWood;
    }
    public boolean matches(Guitar other) {
        if (other.getSerialNumber() != null && !this.serialNumber.equals(other.getSerialNumber())) {
            return false;
        }
        if (other.getPrice() > 0 && this.price != other.getPrice()) {
            return false;
        }
        if (other.getBuilder() != null && this.builder != other.getBuilder()) {
            return false;
        }
        if (other.getType() != null && this.type != other.getType()) {
            return false;
        }
        if (other.getModel() != null && !this.model.equalsIgnoreCase(other.getModel())) {
            return false;
        }
        if (other.getBackWood() != null && this.backWood != other.getBackWood()) {
            return false;
        }
        if (other.getTopWood() != null && this.topWood != other.getTopWood()) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return serialNumber + "," +
                price + "," +
                builder.name() + "," + // Use name() for enum serialization
                type.name() + "," +
                model + "," +
                backWood.name() + "," +
                topWood.name();
    }
    private Guitar stringToGuitar(String str) {
        String[] props = str.split(",");
        if (props.length < 7) {
            throw new IllegalArgumentException("Invalid guitar string: " + str);
        }
        return new Guitar(
                props[0],
                Double.parseDouble(props[1]),
                Builder.valueOf(props[2].toUpperCase()), // Enums are in uppercase
                Type.valueOf(props[3].toUpperCase()),
                props[4],
                Wood.valueOf(props[5].toUpperCase()),
                Wood.valueOf(props[6].toUpperCase())
        );
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guitar guitar = (Guitar) o;
        return Double.compare(guitar.price, price) == 0 &&
                Objects.equals(serialNumber, guitar.serialNumber) &&
                Objects.equals(builder, guitar.builder) &&
                Objects.equals(type, guitar.type) &&
                Objects.equals(model, guitar.model) &&
                Objects.equals(backWood, guitar.backWood) &&
                Objects.equals(topWood, guitar.topWood);
    }
    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, price, builder, type, model, backWood, topWood);
    }
}
