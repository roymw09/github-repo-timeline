package com.example.timeline_app;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.timeline_app.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity{

    ActivityMainBinding binding;
    AppViewModel appViewModel;
    RecyclerView recyclerView;
    ArrayList<AppModel> modelRecyclerArrayList;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        appViewModel = new ViewModelProvider(this).get(AppViewModel.class);
        recyclerView = findViewById(R.id.recycler_view);
        modelRecyclerArrayList = new ArrayList<>();

        binding.setAppViewModel(appViewModel);
        binding.generateButton.setOnClickListener(this::displayData);

        initRecycler();
    }

    private void initRecycler() {
        adapter = new Adapter(this, modelRecyclerArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    private void displayData(View v) {
        appViewModel.loadData().observe(this, appModels -> {
            if (appModels == null) {
                binding.usernameEditText.setError("User not found! Check your spelling and confirm that you " +
                        "have a valid internet connection");
            } else {
                modelRecyclerArrayList = appModels;
                Collections.sort(modelRecyclerArrayList); // sort by date
                adapter.updateList(modelRecyclerArrayList);
            }
        });
        modelRecyclerArrayList.clear();
    }
}