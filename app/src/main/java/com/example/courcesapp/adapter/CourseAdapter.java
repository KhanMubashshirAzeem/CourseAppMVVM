package com.example.courcesapp.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.courcesapp.model.CourseModal;
import com.example.courcesapp.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<CourseModal> courseModals;

    public CourseAdapter(List<CourseModal> courseList) {
        this.courseModals = courseList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from((parent.getContext())).inflate(R.layout.course_card_view, parent, false); //
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        CourseModal courseModal = courseModals.get(position);
        holder.title.setText(courseModal.getCourse());
        // Use Glide to load the image from the URL
        // Use Glide to load the image from the URL with a placeholder
        Glide.with(holder.itemView.getContext())
                .load(courseModal.getImageLocation()) // Replace with your placeholder image
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return courseModals.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleCardView);
            image = itemView.findViewById(R.id.imageCardView);
        }
    }

    // Update the course list and notify the adapter
    @SuppressLint("NotifyDataSetChanged")
    public void setCourseModals(List<CourseModal> courseModals) {
        this.courseModals = courseModals;
        Log.d("CourseAdapter", "Course list updated, size: " + courseModals.size());  // Add this log
        notifyDataSetChanged();
    }


}
