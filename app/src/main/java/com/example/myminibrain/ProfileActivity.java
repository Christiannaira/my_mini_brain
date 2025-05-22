package com.example.myminibrain;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ActivityProfileBinding binding;

    String activeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        Intent getActiveUser = getIntent();
        activeUser = getActiveUser.getStringExtra("user_loggedIn");

//        List<String[]> userDataList = databaseHelper.getAllUserData();
//        StringBuilder displayString = new StringBuilder();
//
//        if (userDataList.isEmpty()) {
//            displayString.append("No User Found");
//        } else {
//            for (String[] user: userDataList) {
//
//                displayString.append("Username: ").append(user[1]).append("\n, Email: ").append(user[2]).append("\n");
//
//            }
//        }
//
//        binding.profile.setText(displayString.toString());

        ArrayList<String[]> activeUserData = databaseHelper.getActiveUser(activeUser);
        StringBuilder displayActiveUser = new StringBuilder();

        if (activeUserData.isEmpty()) {
            displayActiveUser.append("No User Found");
        } else {
            for (String[] user: activeUserData) {
                displayActiveUser.append("Username: ").append(user[0]).append("\nEmail: ").append(user[1]);
            }
        }

        binding.activeProfile.setText(displayActiveUser);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}