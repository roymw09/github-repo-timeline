package com.example.timeline_app;

import android.app.Application;
import android.text.Editable;
import android.text.TextWatcher;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;

public class AppViewModel extends AndroidViewModel {

    private final AppRepository repository;

    private String username;

    public AppViewModel(@NonNull Application application) {
        super(application);
        repository = new AppRepository(application);
    }

    public MutableLiveData<ArrayList<AppModel>> loadData() {
        return repository.callAPI(username);
    }

    public TextWatcher usernameTextWater = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            username = s.toString();
        }

        @Override
        public void afterTextChanged(Editable s) {
            // Do nothing
        }
    };
}
