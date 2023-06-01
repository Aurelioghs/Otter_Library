package com.example.otterlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.example.otterlibrary.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;

    private AccountDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AccountDatabase.getInstance(MainActivity.this);


        View createAccount = findViewById(R.id.createAccountButton);
        createAccount.setOnClickListener(this);

        View manageSystem = findViewById(R.id.manageSystemButton);
        manageSystem.setOnClickListener(this);

        View placeHold = findViewById(R.id.placeHoldButton);
        placeHold.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createAccountButton:
                Intent createAccountIntent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(createAccountIntent);
                break;
            case R.id.manageSystemButton:
                Intent manageSystemIntent = new Intent(MainActivity.this, ManageSystemActivity.class);
                startActivity(manageSystemIntent);
                break;
            case R.id.placeHoldButton:
                Intent placeHoldIntent = new Intent(MainActivity.this, PlaceHoldActivity.class);
                startActivity(placeHoldIntent);
                break;
        }
    }

}