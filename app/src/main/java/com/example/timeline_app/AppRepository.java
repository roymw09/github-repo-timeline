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

    private MutableLiveData<ArrayList<AppModel>> allRepos;
    private ArrayList<AppModel> repoList;

    public AppRepository(Application application) {
    }

    public MutableLiveData<ArrayList<AppModel>> callAPI(String user) {
        Call<ResponseBody> call = RetrofitClient.getInstance().getapi().repository(user);
        allRepos = new MutableLiveData<>();
        repoList = new ArrayList<>();
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
                }
            /*  if no data is retrieved allRepos should be null in order
                to set an EditText error in the MainActivity   */
                if (repoList.isEmpty()){
                    allRepos.setValue(null);
                } else {
                    allRepos.setValue(repoList);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                allRepos.setValue(null);
                System.out.println("t.getMessage() = " + t.getMessage());
            }
        });
        return allRepos;
    }
}
