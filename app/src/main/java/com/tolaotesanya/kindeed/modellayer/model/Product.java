package com.tolaotesanya.kindeed.modellayer.model;

import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Product {

    @PrimaryKey
    @NonNull
    @ColumnInfo
    private String itemId;
    @ColumnInfo
    private String itemName;
    @ColumnInfo
    private String description;
    @ColumnInfo
    private String category;
    @ColumnInfo
    private double price;
    @ColumnInfo
    private String image;

    public Product() {
    }

    @Ignore
    public Product(String itemId, String itemName, String description, String catergory, double price, String image) {
        if(itemId == null){
            itemId = UUID.randomUUID().toString();
        }

        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.category = catergory;
        this.price = price;
        this.image = image;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
