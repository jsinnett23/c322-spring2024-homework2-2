package edu.iu.jsinnett.c322spring2024homework2.model;


import java.util.Objects;

public class Guitar {




    //main variables
    String serialNumber;
    double price;
    String builder;
    String type;
    String model;
    String backWood;
    String topWood;

    public Guitar(String serialNumber, double price, String builder, String type, String model, String backWood, String topWood) {
        this.serialNumber = serialNumber;
        this.price = price;
        this.builder = builder;
        this.type = type;
        this.model = model;
        this.backWood = backWood;
        this.topWood = topWood;
    }

    //getter and setter functions
    public String getSerialNumber() {
        return serialNumber;
    }

    public double getPrice() {
        return price;
    }

    public String getBuilder() {
        return builder;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public String getBackWood() {
        return backWood;
    }

    public String getTopWood() {
        return topWood;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    //testing

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBackWood(String backWood) {
        this.backWood = backWood;
    }

    public void setTopWood(String topWood) {
        this.topWood = topWood;
    }

    //constructors


// inside the Guitar class


    //new model function for the restAPI
    public boolean matches(Guitar other) {
        if (other.getSerialNumber() != null && !this.serialNumber.equals(other.getSerialNumber())) {
            return false;
        }
        if (other.getPrice() > 0 && this.price != other.getPrice()) {
            return false;
        }
        if (other.getBuilder() != null && !this.builder.equalsIgnoreCase(other.getBuilder())) {
            return false;
        }
        if (other.getType() != null && !this.type.equalsIgnoreCase(other.getType())) {
            return false;
        }
        if (other.getModel() != null && !this.model.equalsIgnoreCase(other.getModel())) {
            return false;
        }
        if (other.getBackWood() != null && !this.backWood.equalsIgnoreCase(other.getBackWood())) {
            return false;
        }
        if (other.getTopWood() != null && !this.topWood.equalsIgnoreCase(other.getTopWood())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return serialNumber + "," +
                price + "," +
                builder + "," +
                type + "," +
                model + "," +
                backWood + "," +
                topWood;
    }

    private Guitar stringToGuitar(String str) {
        String[] props = str.split(",");
        if (props.length < 7) {
            throw new IllegalArgumentException("Invalid guitar string: " + str);
        }
        return new Guitar(props[0],
                Double.parseDouble(props[1]),
                props[2],
                props[3],
                props[4],
                props[5],
                props[6]);
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
