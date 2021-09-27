package com.example.rerofit_;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @GET("posts")
    Call<List<Example>> getUser();

    @POST("posts")
    Call<Example> PostUser(@Body Example example);

    @FormUrlEncoded
    @POST("posts")
    Call<Example> PostUser(@Field("userId") int id,@Field("title") String title,@Field("body") String body);

    @FormUrlEncoded
    @POST("posts")
    Call<Example> PostUser(@FieldMap Map<String,String> map);
//
    @FormUrlEncoded
    @PATCH("posts/{id}")
    Call<Example> PatchUser(@Path ("id")int id,@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @PUT("posts/{id}")
    Call<Example> PUtUser(@Path ("id")int id,@FieldMap Map<String,String> map);

@DELETE("posts/{id}")
    Call<Void> DeleteUser(@Path("id")int id);
}
