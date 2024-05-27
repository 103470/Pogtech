package com.pogtech.pogtech.Cars;
import java.time.LocalDate;

public class Cars {
    private int id;
    private String brand;
    private String type;
    private int year;
    private String design;
    private String extra;
    private double price;
    private LocalDate rendezvousDate;

    public Cars(int id, String brand, String type, int year, String design, String extra, double price, LocalDate rendezvousDate) {
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.year = year;
        this.design = design;
        this.extra = extra;
        this.price = price;
        this.rendezvousDate = rendezvousDate;
    }

    // Getters and setters for all fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getRendezvousDate() {
        return rendezvousDate;
    }

    public void setRendezvousDate(LocalDate rendezvousDate) {
        this.rendezvousDate = rendezvousDate;
    }
}