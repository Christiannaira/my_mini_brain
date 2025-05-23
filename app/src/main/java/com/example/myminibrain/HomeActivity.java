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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myminibrain.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    String activeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        // stored the email who's logged in
        Intent getActiveUser = getIntent();
        if (getActiveUser != null) {
            activeUser = getActiveUser.getStringExtra("user_loggedIn");
        } else {
            activeUser = "User";
        }


        binding.userProfileName.setText(activeUser);

        Toast.makeText(getApplicationContext(), activeUser, Toast.LENGTH_SHORT).show();

        List<Item> items = new ArrayList<>();

        items.add(new Item("React 18 for Beginners", "A step-by-step guide to building web apps with React 18+ and TypeScript"));
        items.add(new Item("The Ultimate Next.js Series", "Everything you need to build fast, full-stack web apps with Next.js 13+ (App Router) and TypeScript"));
        items.add(new Item("The Ultimate HTML5 & CSS3 Series", "Everything you need to build fast and stunning websites with HTML5 and CSS3 in one bundle"));

        items.add(new Item("The Ultimate Full-stack JavaScript Developer", "Master all the JavaScript skills you need to land a full-stack developer job"));
        items.add(new Item("The Ultimate JavaScript Series", "Master JavaScript: Go from Novice to Professional. Everything you need to code in JavaScript in one bundle"));
        items.add(new Item("React Testing Mastery: From Basics to Advanced Techniques", "A comprehensive guide to React testing packed with practical tips, real-world exercises, and best practices"));

        binding.homeView.setLayoutManager(new LinearLayoutManager(this));
        binding.homeView.setAdapter(new MyAdapter(HomeActivity.this, items));

        binding.userProfileName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                intent.putExtra("user_loggedIn", activeUser);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}