package com.androdevlinux.percy.androdevlinuxserver;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiEndPointInterface {
    @POST("login")
    Call<MessageWrapper> login(@Query(value = "username", encoded = true) String username, @Query(value = "password", encoded = true) String password);
}
