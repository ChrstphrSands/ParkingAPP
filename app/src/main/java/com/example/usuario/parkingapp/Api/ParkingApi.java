package com.example.usuario.parkingapp.Api;

import com.example.usuario.parkingapp.Models.Cochera;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ParkingApi {
    @GET("cocheras")
    Call<List<Cochera>> getCocheras();
}
