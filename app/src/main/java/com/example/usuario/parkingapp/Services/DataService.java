package com.example.usuario.parkingapp.Services;

import android.util.Log;
import com.example.usuario.parkingapp.Api.ParkingApi;
import com.example.usuario.parkingapp.Models.Cochera;
import com.example.usuario.parkingapp.Models.Devslopes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class DataService {
    private List<Cochera> cocheraList = new ArrayList<>();
    private static DataService instance = new DataService();

    public static DataService getInstance() {
        return instance;
    }

    private DataService() {

    }

    public List<Cochera> getCocheras() {
        final ParkingApi api;

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        api = retrofit.create(ParkingApi.class);

        Call<List<Cochera>> cocherasArray = api.getCocheras();
        cocherasArray.enqueue(new Callback<List<Cochera>>() {
            @Override
            public void onResponse(Call<List<Cochera>> call, Response<List<Cochera>> response) {
                if (response.isSuccessful()) {
                    List<Cochera> cocheras = response.body();
                    cocheraList.addAll(cocheras);
                }
            }

            @Override
            public void onFailure(Call<List<Cochera>> call, Throwable t) {
                Log.e("Wiwi", t.getLocalizedMessage());
            }
        });

        Log.d("Wiwi", String.valueOf(cocheraList.size()));
        return cocheraList;
    }

    public ArrayList<Devslopes> getBootcampLocationsWithin10MilesOfZip(int zipcode) {

        getCocheras();
        ArrayList<Devslopes> list = new ArrayList<>();
        list.add(new Devslopes(-18.003081f, -70.24018f, "Parking 1", "Bonito", "slo"));
        list.add(new Devslopes(-18.003081f, -70.233072f, "Parking 2", "Masomenos", "slo"));
        list.add(new Devslopes(-18.002256f, -70.233554f, "Parking 3", "Horrible", "slo"));
        list.add(new Devslopes(-17.998759f, -70.238729f, "Parking 4", "Calamitoso", "slo"));
        return list;
    }
}
