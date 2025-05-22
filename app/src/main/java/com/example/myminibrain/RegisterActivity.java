package com.example.myminibrain;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myminibrain.databinding.ActivityRegisterBinding;
import com.example.myminibrain.databinding.ActivityUserStartBinding;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    DatabaseHelper my_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());



//        Toast.makeText(this, userType, Toast.LENGTH_SHORT).show();

        my_database = new DatabaseHelper(this);

        binding.returnUserStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this, UserStartActivity.class);
                startActivity(intent);
                finish();
            }

        });

        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String registerName = binding.registerName.getText().toString();
                String registerEmail = binding.registerEmail.getText().toString();
                String registerPassword = binding.registerPassword.getText().toString();
                String registerConfirm = binding.registerConfirm.getText().toString();

                String userType = getIntent().getStringExtra("type");

                if (registerName.isEmpty() || registerEmail.isEmpty() || registerPassword.isEmpty() || registerConfirm.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Field must not be empty", Toast.LENGTH_SHORT).show();
                } else {

                    if (!registerPassword.equals(registerConfirm)) {
                        Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();

                        registerAccount(registerName, registerEmail, registerPassword, userType);

                    }

                }

            }
        });

        binding.signupToLogIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void registerAccount(String username, String email, String password, String type) {

        boolean registerAccount = my_database.insertUser(username, email, password, type);

        if (registerAccount) {
            Toast.makeText(getApplicationContext(), "Account Registered Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
        }


    }


}