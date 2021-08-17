package com.example.timeline_app;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface api {
    @GET("{user}/repos")
    Call<ResponseBody> repository (@Path("user") String user);
}
