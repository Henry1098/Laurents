package com.example.laurents.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.laurents.dao.UserDao;
import com.example.laurents.entity.User;

@Database(entities = {User.class},version =1)
public abstract class UserAppDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
}
