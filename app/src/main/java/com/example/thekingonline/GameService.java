package com.example.thekingonline;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GameService {

    final String address = "http://912939-cf66069.tmweb.ru/";

    @GET("theking")
    Call<LoadingActivity.InitialData> getIDOfRoom();

    @GET("theking/room")
    Call<LoadingActivity.InitialData> getUserCode(@Query("id") String idOfRoom);

    @GET("theking/room")
    Call<LoadingActivity.InitialData> getTimeReminder(@Query("id") String idOfRoom, @Query("user-code") int userCode);

    @GET("theking/start")
    Call<LoadingActivity.InitialData> getCountryId(@Query("id") String idOfRoom, @Query("user-code") int userCode);
}