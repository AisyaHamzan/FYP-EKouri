package com.example.myekouri.Domain;

public class AllProductDomain {
    String Name, Brand, Description, Image, Category;
    int Price;

    public AllProductDomain() {
    }

    public AllProductDomain(String name, String brand, String description, String image, String category, int price) {
        Name = name;
        Brand = brand;
        Description = description;
        Image = image;
        Category = category;
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
}
