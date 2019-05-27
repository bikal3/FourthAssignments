package com.example.fourthassigment.Model;

public class DashModel {
    private String name;
    private String price;
    private String desc;
    private String image;

    public DashModel(String name, String price, String desc, String image) {
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
