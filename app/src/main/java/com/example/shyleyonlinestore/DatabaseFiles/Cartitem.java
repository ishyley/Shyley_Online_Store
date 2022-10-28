package com.example.shyleyonlinestore.DatabaseFiles;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_items")
public class Cartitem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int groceryItemId;


    public Cartitem(int groceryItemId){
        this.groceryItemId = groceryItemId;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public int getGroceryItemId() {
        return groceryItemId;
    }
}
