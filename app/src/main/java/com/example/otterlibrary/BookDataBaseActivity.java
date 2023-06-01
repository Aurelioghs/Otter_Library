package com.example.otterlibrary;

import androidx.appcompat.app.AppCompatActivity;

// I created this class to display all the books in data base

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.otterlibrary.databinding.ActivityBookDataBaseBinding;

import java.util.List;

public class BookDataBaseActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityBookDataBaseBinding binding;

    private AccountDatabase db;
    private BookDao bookDao;
    private ArrayAdapter<String> bookAdapter;

    private TextView bookNameTextView;
    private TextView bookAuthorTextView;
    private TextView bookGenreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookDataBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        View backButton = findViewById(R.id.backHomeButton);
        backButton.setOnClickListener(this);

        db = AccountDatabase.getInstance(this);
        bookDao = db.bookDao();

        bookNameTextView = findViewById(R.id.bookName);
        bookAuthorTextView = findViewById(R.id.bookAuthor);
        bookGenreTextView = findViewById(R.id.bookGenre);

        Intent intent = getIntent();
        if (intent != null) {
            // Retrieve all books from the database
            List<Book> bookList = bookDao.getAllBooks();

            // Create a StringBuilder to store book information
            StringBuilder bookInfo = new StringBuilder();

            // Iterate over each book and append its information to the StringBuilder
            for (int i = 0; i < bookList.size(); i++) {
                Book book = bookList.get(i);
                bookInfo.append("Book: ").append(book.getTitle()).append("\n");
                bookInfo.append("Author: ").append(book.getAuthor()).append("\n");
                bookInfo.append("Genre: ").append(book.getGenre()).append("\n\n");
            }
            bookNameTextView.setText(bookInfo.toString());
        }
    }

    @Override
    public void onClick(View v) {
        // Handle button click event
        if (v.getId() == R.id.backHomeButton) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
