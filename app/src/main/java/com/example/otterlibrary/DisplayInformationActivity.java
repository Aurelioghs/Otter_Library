package com.example.otterlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.otterlibrary.databinding.ActivityDisplayInformationBinding;

public class DisplayInformationActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityDisplayInformationBinding binding;
    private TextView user;
    private TextView book;
    private TextView reservationNumberTextView;

    private AccountDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDisplayInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        View backButton = findViewById(R.id.backHome);
        backButton.setOnClickListener(this);

        db = AccountDatabase.getInstance(this);

        user = findViewById(R.id.userName);
        book = findViewById(R.id.bookTitle);
        reservationNumberTextView = findViewById(R.id.reservationNumber);

        Intent intent = getIntent();
        if (intent != null) {
            String username = intent.getStringExtra("username");
            String bookTitle = intent.getStringExtra("bookTitle");
            String reservationNumber = intent.getStringExtra("reservationNumber");

            if (username != null && bookTitle != null && reservationNumber != null) {
                user.setText("Customer Username: " + username);
                book.setText("Book to Hold: " + bookTitle);
                reservationNumberTextView.setText("Reservation Number: " + reservationNumber);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backHome) {
//            Log.d("click", "Display Book info");
            Intent i = new Intent(DisplayInformationActivity.this, MainActivity.class);
            startActivity(i);
        }
    }
}