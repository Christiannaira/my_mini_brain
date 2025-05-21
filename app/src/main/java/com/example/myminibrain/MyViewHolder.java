package com.example.myminibrain;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView courseTitleView, courseDesView;
    Button courseItemBtn;

    private String currentCourseTitle;
    private String currentCourseDes;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        courseTitleView = itemView.findViewById(R.id.courseTitleView);
        courseDesView = itemView.findViewById(R.id.courseDesView);
        courseItemBtn = itemView.findViewById(R.id.courseItemBtn);

        courseItemBtn.setOnClickListener(this);
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Context context = v.getContext();

        if (v.getId() == R.id.courseItemBtn) {

            String msg = currentCourseTitle;
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(context, "Item is clicked", Toast.LENGTH_SHORT).show();

        }

    }

    public void bindData(String courseTitle, String courseDes) {
        currentCourseTitle = courseTitle;
        currentCourseDes = courseDes;
    }
}
