package com.example.usuario.parkingapp.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public final ParkingApi api;
    private final String BASE_URL_LOCAL_CELL = "http://192.168.0.3:3000/";
    private final String BASE_URL_LOCAL_EMULADOR = "http://10.0.2.2:3000/";
    private final String BASE_URL_AZURE = "http://sistemaparqueo.azurewebsites.net/api/";

    public RetrofitInstance() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL_AZURE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        api = retrofit.create(ParkingApi.class);
    }
}
