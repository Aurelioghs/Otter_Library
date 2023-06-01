package com.example.otterlibrary;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.otterlibrary.AccountDao;
import com.example.otterlibrary.BookDao;
import com.example.otterlibrary.Book;

@Database(entities = {Account.class, Book.class}, version = 8, exportSchema = false)
public abstract class AccountDatabase extends RoomDatabase {
    private static AccountDatabase sInstance;

    public abstract AccountDao accountDao();
    public abstract BookDao bookDao();

    public static synchronized AccountDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AccountDatabase.class, "Account_Data_Base.bd")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return sInstance;
    }
}
