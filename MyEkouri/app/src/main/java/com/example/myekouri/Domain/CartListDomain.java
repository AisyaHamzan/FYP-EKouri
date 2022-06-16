package com.example.myekouri.Domain;

public class CartListDomain {

    String Image;
    String ProductName;
    String ProductPrice;
    String totalQuantity;
    String currentDate;
    String currentTime;
    int totalPrice;
    String documentId;


    public CartListDomain() {
    }

    public CartListDomain(String image, String productName, String productPrice, String totalQuantity, String currentDate, String currentTime, int totalPrice) {
        Image = image;
        ProductName = productName;
        ProductPrice = productPrice;
        this.totalQuantity = totalQuantity;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.totalPrice = totalPrice;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
