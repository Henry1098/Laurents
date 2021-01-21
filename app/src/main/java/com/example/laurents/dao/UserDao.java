package com.example.laurents.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.laurents.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    public long addUser(User user);

    @Update
    public void updateUser(User user);

    @Delete
    public void deleteUser(User user);

    @Query("SELECT * FROM users")
    public List<User> getAllUsers();

    @Query("SELECT * FROM users where phone == :phone AND password == :password")
    public User getUser(String phone,String password);
}
