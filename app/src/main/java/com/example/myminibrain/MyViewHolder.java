package com.example.myminibrain;

import android.content.Context;
import android.content.Intent;
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

    private int currentCourseId;

    DatabaseHelper databaseHelper;

    private Context context;

    public MyViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        databaseHelper = new DatabaseHelper(context);

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

            boolean addToCartCourse = databaseHelper.insertCourse(currentCourseTitle, "Technology", 0, currentCourseId);

            if (addToCartCourse) {
                Toast.makeText(context, "Course Added Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Course Was Not Added", Toast.LENGTH_SHORT).show();
            }


        } else {

            Toast.makeText(context, "Item is clicked " + currentCourseId, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, CourseActivity.class);
            intent.putExtra("courseTitle", currentCourseTitle);
            intent.putExtra("courseDes", currentCourseDes);
            intent.putExtra("courseId", currentCourseId);

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);


        }

    }

    public void bindData(String courseTitle, String courseDes, int courseId) {
        currentCourseTitle = courseTitle;
        currentCourseDes = courseDes;
        currentCourseId = courseId;
    }
}
