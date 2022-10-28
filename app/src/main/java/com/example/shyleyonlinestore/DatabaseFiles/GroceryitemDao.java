package com.example.shyleyonlinestore.DatabaseFiles;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.shyleyonlinestore.GroceryItem;

import java.util.List;

@Dao
public interface GroceryitemDao {
    @Insert
    void insert (GroceryItem groceryItem);

    @Query("SELECT * FROM grocery_items")
    List<GroceryItem> getAllItems();


    @Query("UPDATE grocery_items SET rate = :newRate WHERE id=:id")
    void updateRate (int id, int newRate);

    @Query("SELECT * FROM grocery_items WHERE id=:id")
    GroceryItem getItemById (int id);

    @Query("UPDATE grocery_items SET reviews=:reviews WHERE id = :id")
    void updateReviews (int id, String reviews);

//    @Query("UPDATE grocery_items SET reviews=:reviews WHERE id = :id")
//    void updateReviews (int id, String reviews);

    @Query("SELECT * FROM grocery_items WHERE name LIKE :text")
    List<GroceryItem> searchForItem(String text);

    @Query("SELECT DISTINCT category FROM grocery_items")
    List<String> getCategories ();

    @Query("SELECT * FROM grocery_items WHERE category = :category")
    List<GroceryItem> getItemsByCategory(String category);

    @Query("UPDATE grocery_items SET popolarityPoint = :point WHERE id =:id")
    void updatePopolarityPoint(int id, int point);

    @Query("UPDATE grocery_items SET userpoint = :point WHERE id =:id")
    void updateUserPoints(int id, int point);

}
