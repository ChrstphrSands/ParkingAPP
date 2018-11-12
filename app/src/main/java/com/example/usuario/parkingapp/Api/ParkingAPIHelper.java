package com.example.usuario.parkingapp.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ParkingAPIHelper {
    public final ParkingApi api;

    public ParkingAPIHelper() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        api = retrofit.create(ParkingApi.class);
    }
}
