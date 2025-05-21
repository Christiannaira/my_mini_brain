package com.example.myminibrain;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView courseTitleView, courseDesView;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        courseTitleView = itemView.findViewById(R.id.courseTitleView);
        courseDesView = itemView.findViewById(R.id.courseDesView);
    }
}
