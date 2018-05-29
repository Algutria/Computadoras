package com.computadoras.computadoras;

class Computer {
    private String id;

    private int ram;
    private int brand;
    private int color;
    private int type;
    private int OS;
    private int image;

    public Computer(){

    }

    public Computer(String id, int ram, int brand, int color, int type, int OS, int image) {
        this.id = id;
        this.ram = ram;
        this.brand = brand;
        this.color = color;
        this.type = type;
        this.OS = OS;
        this.image = image;
    }

    public Computer(int ram, int brand, int color, int type, int OS, int image) {
        this.id = id;
        this.ram = ram;
        this.brand = brand;
        this.color = color;
        this.type = type;
        this.OS = OS;
        this.image = image;
    }

    public Computer(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOS() {
        return OS;
    }

    public void setOS(int OS) {
        this.OS = OS;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public void save(){
        Data.saveComputer(this);
    }

    public void delete(){
        Data.deleteComputer(this);
    }
}
