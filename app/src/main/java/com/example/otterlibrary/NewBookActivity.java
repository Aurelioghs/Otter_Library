package com.example.otterlibrary;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.otterlibrary.databinding.ActivityCreateAccountBinding;
import com.example.otterlibrary.databinding.ActivityNewBookBinding;

import java.util.List;

public class NewBookActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityNewBookBinding binding;
    private AccountDatabase db;

    private BookDao bookDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AccountDatabase.getInstance(NewBookActivity.this);

        Button createButton = findViewById(R.id.addButton);
        createButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String title = binding.inputTitle.getText().toString();
        String author = binding.inputAuthor.getText().toString();
        String genre = binding.inputGenre.getText().toString();

        if (title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            Book newBook = new Book(title, author, genre);
            db.bookDao().insert(newBook);
            Toast.makeText(this, "New book added successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(NewBookActivity.this, BookDataBaseActivity.class);
            startActivity(intent);
            }
        }
    }





