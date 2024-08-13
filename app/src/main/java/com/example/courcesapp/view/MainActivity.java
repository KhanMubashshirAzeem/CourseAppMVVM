package com.example.courcesapp.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.courcesapp.R;
import com.example.courcesapp.adapter.CourseAdapter;
import com.example.courcesapp.databinding.ActivityMainBinding;
import com.example.courcesapp.model.CourseModal;
import com.example.courcesapp.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerViewMethod();

    }

    private void recyclerViewMethod() {
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        courseAdapter = new CourseAdapter(new ArrayList<>());
        binding.recyclerView.setAdapter(courseAdapter);

        // Initialize the ViewModel
        MainActivityViewModel mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // Observe the LiveData for changes
        mainActivityViewModel.getCourses().observe(this, new Observer<List<CourseModal>>() {
            @Override
            public void onChanged(List<CourseModal> courseModals) {
                if (courseModals != null && !courseModals.isEmpty()) {
                    // Update the RecyclerView adapter with the new data
                    courseAdapter.setCourseModals(courseModals);
                } else {
                    // Handle the case where no courses are available
//                    findViewById(R.id.noCoursesText).setVisibility(View.VISIBLE);
                }
            }
        });


    }
}








