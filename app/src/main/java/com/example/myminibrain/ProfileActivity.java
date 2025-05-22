package com.example.myminibrain;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myminibrain.databinding.ActivityProfileBinding;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        List<String[]> userDataList = databaseHelper.getAllUserData();
        StringBuilder displayString = new StringBuilder();

        if (userDataList.isEmpty()) {
            displayString.append("No User Found");
        } else {
            for (String[] user: userDataList) {

                displayString.append("Username: ").append(user[1]).append("\n, Email: ").append(user[2]);

            }
        }

        binding.profile.setText(displayString.toString());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}