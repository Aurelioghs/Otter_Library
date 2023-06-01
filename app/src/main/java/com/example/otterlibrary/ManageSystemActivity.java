package com.example.otterlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.otterlibrary.databinding.ActivityMainBinding;
import com.example.otterlibrary.databinding.ActivityManageSystemBinding;

import java.util.List;

public class ManageSystemActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityManageSystemBinding binding;
    private String adminUsername;
    private String adminPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManageSystemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        View LogIn = findViewById(R.id.log_in_button);
        LogIn.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {

        adminUsername = binding.adminUsername.getText().toString().trim();
        adminPassword = binding.adminPassword.getText().toString().trim();

        if (adminUsername.isEmpty() || adminPassword.isEmpty()) {
            Toast.makeText(ManageSystemActivity.this, "Enter admin username and password", Toast.LENGTH_SHORT).show();
        } else if (adminUsername.equals("!admin2") && adminPassword.equals("!admin2")) {
            Intent intent = new Intent(ManageSystemActivity.this, AddBookActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(ManageSystemActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
        }
    }
}
