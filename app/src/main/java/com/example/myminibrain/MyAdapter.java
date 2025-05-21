package com.example.myminibrain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Item> items;

    public MyAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.courseTitleView.setText(items.get(position).getCourseTitle());
        holder.courseDesView.setText(items.get(position).getCourseDes());

        String title = items.get(position).getCourseTitle();
        String des = items.get(position).getCourseDes();

        holder.bindData(title, des);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
