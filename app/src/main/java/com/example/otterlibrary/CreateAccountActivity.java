package com.example.otterlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.otterlibrary.databinding.ActivityCreateAccountBinding;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityCreateAccountBinding binding;
    private AccountDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AccountDatabase.getInstance(CreateAccountActivity.this);

        Button createButton = findViewById(R.id.create_button);
        createButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.create_button) {
            String username = binding.inputUsername.getText().toString();
            String password = binding.inputPassword.getText().toString();

//            use if statement to check if username is empty, if not display account already exist.
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                return;
            }
            Account existingAccount = db.accountDao().findByUsernameAndPassword(username, password);
            if (existingAccount != null) {
                Toast.makeText(this, "Account already exists", Toast.LENGTH_SHORT).show();
                displayMainMenu();
                return;
            }

//            If new user name, create new account object
            Account account = new Account(username, password);
            db.accountDao().insert(account);
            Toast.makeText(this, "New account created successfully", Toast.LENGTH_SHORT).show();

            displayMainMenu();
        }
    }
    private void displayMainMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
