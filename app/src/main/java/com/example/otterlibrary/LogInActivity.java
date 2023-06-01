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

import com.example.otterlibrary.databinding.ActivityLogInBinding;

import java.util.List;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener  {

    private ActivityLogInBinding binding;
    private TextView userInfo;
    private AccountDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button logIn = findViewById(R.id.backHomeButton);
        logIn.setOnClickListener(this);

        userInfo = findViewById(R.id.customerAccount);

        db = AccountDatabase.getInstance(this);
        List<Account> accounts = db.accountDao().getAllAccounts();

        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            String username = account.getUsername();
            userInfo.append("Customer's username: " + username + "\n");
        }
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backHomeButton) {
            Log.d("click", "create Account");
            Intent i = new Intent(LogInActivity.this, AddBookActivity.class);
            startActivity(i);
        }
    }
}