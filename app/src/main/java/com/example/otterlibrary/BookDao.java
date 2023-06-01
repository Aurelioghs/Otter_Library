package com.example.otterlibrary;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface BookDao {
    @Insert
    void insert(Book book);

    @Query("SELECT * FROM Book")
    List<Book> getAllBooks();

    @Query("SELECT * FROM Book WHERE id = :id")
    Book findById(int id);

//    @Query("SELECT * FROM Book WHERE title = :title")
//    List<Book> findByTitle(String title);

    @Update
    void update(Book book);

    @Query("SELECT COUNT(*) FROM Book WHERE title = :title")
    int countReservedBooks(String title);

    // Custom method to check if a book is reserved
    default boolean isBookReserved(String title) {
        int count = countReservedBooks(title);
        return count > 0;
    }

    @Query("SELECT * FROM Book WHERE genre = :genre")
    List<Book> findByGenre(String genre);

    @Query("SELECT * FROM Book WHERE title = :title LIMIT 1")
    Book findByTitle(String title);
}

