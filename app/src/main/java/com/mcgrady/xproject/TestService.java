package com.mcgrady.xproject;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mcgrady on 2019-08-10.
 */
public interface TestService {

    @GET("/api/4/news/latest")
    Call<Object> getDailyList();
}
