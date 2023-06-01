package com.example.otterlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.otterlibrary.databinding.ActivityPlaceHoldBinding;

import java.util.List;

public class PlaceHoldActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityPlaceHoldBinding binding;
    private AccountDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaceHoldBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = AccountDatabase.getInstance(this);


        View computerScience = findViewById(R.id.computerScienceButton);
        computerScience.setOnClickListener(this);

        View fiction = findViewById(R.id.fictionButton);
        fiction.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.computerScienceButton) {
            String genre = "Computer Science";
            List<Book> books = db.bookDao().findByGenre(genre);
            boolean anyBookAvailable = false;
            for (Book book : books) {
                if (!book.isReserved()) {
                    anyBookAvailable = true;
                    break;
                }
            }
            if (!anyBookAvailable) {
                Toast.makeText(this, "No books available under the Computer Science genre", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(PlaceHoldActivity.this, MainActivity.class);
                startActivity(i);

            } else {
                Intent intent = new Intent(PlaceHoldActivity.this, UserHoldActivity.class);
                intent.putExtra("genre", genre);
                startActivity(intent);
            }
        } else if (view.getId() == R.id.fictionButton) {
            String genre = "Fiction";
            List<Book> books = db.bookDao().findByGenre(genre);
            boolean anyBookAvailable = false;
            for (Book book : books) {
                if (!book.isReserved()) {
                    anyBookAvailable = true;
                    break;
                }
            }
            if (!anyBookAvailable) {
                Toast.makeText(this, "No books available under the Fiction genre", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(PlaceHoldActivity.this, MainActivity.class);
                startActivity(i);
            } else {
                Intent intent = new Intent(PlaceHoldActivity.this, UserHoldActivity.class);
                intent.putExtra("genre", genre);
                startActivity(intent);
            }
        }
    }

}

