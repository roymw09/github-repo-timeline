package com.example.timeline_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.timeline_app.databinding.ActivityMainBinding;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    Adapter adapter;
    RecyclerView recyclerView;
    AppViewModel appViewModel;
    ArrayList<AppModel> modelRecyclerArrayList;
    Button generate;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        appViewModel = new ViewModelProvider(this).get(AppViewModel.class);
        binding.setAppViewModel(appViewModel);

        generate = findViewById(R.id.generateButton);
        recyclerView = findViewById(R.id.recycler_view);
        modelRecyclerArrayList = new ArrayList<>();

        initRecycler();

        generate.setOnClickListener( (View v) -> {
            refreshRecyclerArrayList();
            displayData(v);
        } );
    }

    private void initRecycler() {
        adapter = new Adapter(this, modelRecyclerArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    private void displayData(View v) {
        appViewModel.loadData().observe(this, appModels -> {
            if (appModels != null) {
                modelRecyclerArrayList = appModels;
                adapter.updateList(modelRecyclerArrayList);
            }
        });
    }

    private void refreshRecyclerArrayList() {
        modelRecyclerArrayList.clear();
    }
}
