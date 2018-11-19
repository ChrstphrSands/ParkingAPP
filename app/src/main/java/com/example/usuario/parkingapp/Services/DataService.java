package com.example.usuario.parkingapp.Services;

import android.util.Log;
import com.example.usuario.parkingapp.Api.RetrofitInstance;
import com.example.usuario.parkingapp.Models.Cliente;
import com.example.usuario.parkingapp.Models.Cochera;
import com.example.usuario.parkingapp.Models.Reserva;
import com.example.usuario.parkingapp.Models.Servicio;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class DataService {
    private List<Cochera> cocheraList = new ArrayList<>();
    private List<Servicio> servicioList = new ArrayList<>();
    private static DataService instance = new DataService();

    private Cliente cliente = new Cliente();
    RetrofitInstance retrofitInstance = new RetrofitInstance();

    public static DataService getInstance() {
        return instance;
    }

    private DataService() {

    }

    public List<Cochera> getCocheras() {
        RetrofitInstance apiHelper = new RetrofitInstance();

        Call<List<Cochera>> cocherasArray = apiHelper.api.getCocheras();
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

        return cocheraList;
    }


    public Cliente getCliente(int ClienteId) {

        retrofitInstance = new RetrofitInstance();

        Call<Cliente> callCliente = retrofitInstance.api.getCliente(ClienteId);

        callCliente.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    Cliente clientes = response.body();
                    cliente = clientes;
                    Log.d("Cliente DataService", String.valueOf(cliente));
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {

            }

        });

        return cliente;
    }

    public List<Servicio> getServicios(int cochera_id) {

        retrofitInstance = new RetrofitInstance();

        Call<List<Servicio>> callServicio = retrofitInstance.api.getServicios(cochera_id);
        callServicio.enqueue(new Callback<List<Servicio>>() {
            @Override
            public void onResponse(Call<List<Servicio>> call, Response<List<Servicio>> response) {
                if (response.isSuccessful()) {
                    List<Servicio> servicios = response.body();
                    servicioList.addAll(servicios);
                }
            }

            @Override
            public void onFailure(Call<List<Servicio>> call, Throwable t) {
                Log.e("Wiwi", t.getLocalizedMessage());
            }
        });

        return servicioList;
    }

    public void setReserva(Reserva reserva) {
//        RetrofitInstance retrofitInstance = new RetrofitInstance();

        retrofitInstance = new RetrofitInstance();
        Call<Reserva> callReserva = retrofitInstance.api.setReserva(reserva);
        callReserva.enqueue(new Callback<Reserva>() {
            @Override
            public void onResponse(Call<Reserva> call, Response<Reserva> response) {
            }

            @Override
            public void onFailure(Call<Reserva> call, Throwable t) {

            }
        });
    }
}
