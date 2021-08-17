package com.example.timeline_app;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppRepository {

    private final MutableLiveData<ArrayList<AppModel>> allRepos;
    private final ArrayList<AppModel> repoList;

    public AppRepository(Application application) {
        allRepos = new MutableLiveData<>();
        repoList = new ArrayList<>();
    }

    public MutableLiveData<ArrayList<AppModel>> callAPI(String user) {
        Call<ResponseBody> call = RetrofitClient.getInstance().getapi().repository(user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        assert response.body() != null;
                        JSONArray dataArray = new JSONArray(response.body().string());
                        for (int i = 0; i < dataArray.length(); i++) {
                            AppModel modelRecycler = new AppModel();
                            JSONObject dataObj = dataArray.getJSONObject(i);
                            modelRecycler.setName(dataObj.getString("name"));
                            modelRecycler.setDescription(dataObj.getString("description"));
                            modelRecycler.setCreated_at(dataObj.getString("created_at"));
                            repoList.add(modelRecycler);
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                    allRepos.setValue(repoList);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                allRepos.postValue(null);
                System.out.println("t.getMessage() = " + t.getMessage());
            }
        });
        return allRepos;
    }
}
