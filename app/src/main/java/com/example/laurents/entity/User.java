package com.example.laurents.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @ColumnInfo(name="id")
    @PrimaryKey(autoGenerate=true)
    private long id;

    @ColumnInfo(name="username")
    private String username;

    @ColumnInfo(name="phone")
    private String phone;

    @ColumnInfo(name="password")
    private String password;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(long id, String username, String phone, String password) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.password = password;
    }
}

