package com.example.laurents.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.laurents.entity.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    public long addProduct(Product product);

    @Update
    public void updateProduct(Product product);

    @Delete
    public void deleteProduct(Product product);

    @Query("SELECT * FROM products")
    public List<Product> getAllProducts();

    @Query("SELECT * FROM products where id == :phone")
    public Product getProduct(String phone);
}
