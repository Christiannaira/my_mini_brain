package com.example.myminibrain;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myminibrain.databinding.ActivityCourseBinding;

public class CourseActivity extends AppCompatActivity {

    ActivityCourseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCourseBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());



        Intent intent = getIntent();
        String courseTitle = intent.getStringExtra("courseTitle");
        String courseDes = intent.getStringExtra("courseDes");
        int courseId = intent.getIntExtra("courseId", 0);

        binding.title.setText(courseTitle);
        binding.description.setText(courseDes);
        binding.courseId.setText(String.valueOf(courseId));



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}