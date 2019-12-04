package com.example.food_oasis_app;

public class VendorInformation {
    private String item;
    private String price;
    private String quantity;
    private String itemKey;

    public VendorInformation() {

    }
    public VendorInformation(String item, String price, String quantity) {

        this.item = item;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public  void setItemKey(String key) {
        this.itemKey = key;
    }
    public String getItemKey() {
        return this.itemKey;
    }
}
