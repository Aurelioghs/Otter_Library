package com.example.otterlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.otterlibrary.databinding.ActivityAddBookBinding;
import com.example.otterlibrary.databinding.ActivityMainBinding;

public class AddBookActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityAddBookBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        View Users = findViewById(R.id.NewUserButton);
        Users.setOnClickListener(this);

        View AddBook = findViewById(R.id.yesButton);
        AddBook.setOnClickListener(this);

        View backButton = findViewById(R.id.backHome);
        backButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.NewUserButton) {
            Intent i = new Intent(AddBookActivity.this, LogInActivity.class);
            startActivity(i);
        } else if (view.getId() == R.id.yesButton) {
            Intent i = new Intent(AddBookActivity.this, NewBookActivity.class);
            startActivity(i);
        } else if (view.getId() == R.id.backHome) {
            Intent i = new Intent(AddBookActivity.this, MainActivity.class);
            startActivity(i);
        }
    }
}

