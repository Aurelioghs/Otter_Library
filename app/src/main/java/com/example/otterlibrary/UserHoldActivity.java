package com.example.otterlibrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.otterlibrary.databinding.ActivityReserveBookBinding;

public class UserHoldActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityReserveBookBinding binding;
    private AccountDatabase db;

    private String verify_Username;
    private String verify_Password;
    private int reservationNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReserveBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.holdButton.setOnClickListener(this);

        db = AccountDatabase.getInstance(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        String inputBook = binding.bookToHold.getText().toString().trim();
        verify_Username = binding.verifyUsername.getText().toString().trim();
        verify_Password = binding.verifyPassword.getText().toString().trim();

        if (inputBook.isEmpty() || verify_Username.isEmpty() || verify_Password.isEmpty()) {
            Toast.makeText(this, "Input all fields", Toast.LENGTH_SHORT).show();
        } else {
            // If the username is found in the database, allow the user to access and hold the book
            Account account = db.accountDao().findByUsernameAndPassword(verify_Username, verify_Password);
            if (account != null) {
                Book foundBook = db.bookDao().findByTitle(inputBook);
                if (foundBook != null) {
                    // If the book is already on hold, increment the reservation number
                    if (foundBook.isReserved()) {
                        foundBook.setReservationNumber(foundBook.getReservationNumber() + 1);
                        db.bookDao().update(foundBook);
                    } else {
                        // If the book is not on hold, reserve it
                        foundBook.setReserved(true);
                        foundBook.setReservationNumber(1); // Set the initial reservation number to 1
                        db.bookDao().update(foundBook);
                    }

                    // Count the reservations for the book
                    int bookReservations = db.bookDao().countReservedBooks(foundBook.getTitle());
                    reservationNumber = bookReservations;

                    String genre = getIntent().getStringExtra("genre");
                    Intent intent = new Intent(UserHoldActivity.this, DisplayInformationActivity.class);
                    intent.putExtra("username", account.getUsername());
                    intent.putExtra("bookTitle", foundBook.getTitle());
                    intent.putExtra("reservationNumber", String.valueOf(reservationNumber));
                    startActivity(intent);
                } else {
                    // Book not found in the database, display the information without adding to the database
                    String username = account.getUsername();
                    Intent intent = new Intent(UserHoldActivity.this, DisplayInformationActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("bookTitle", inputBook);
                    intent.putExtra("reservationNumber", String.valueOf(reservationNumber));
                    startActivity(intent);
                }
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
