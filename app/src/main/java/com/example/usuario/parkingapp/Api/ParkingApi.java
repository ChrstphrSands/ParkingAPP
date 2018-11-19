package com.example.usuario.parkingapp.Api;

import com.example.usuario.parkingapp.Models.Cliente;
import com.example.usuario.parkingapp.Models.Cochera;
import com.example.usuario.parkingapp.Models.Reserva;
import com.example.usuario.parkingapp.Models.Servicio;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ParkingApi {
    @GET("cocheras")
    Call<List<Cochera>> getCocheras();

    @GET("clientes/{id}")
    Call<Cliente> getCliente(@Path("id") int id);

    @GET("servicios")
    Call<List<Servicio>> getServicios(@Query("cochera_id") int cochera_id);

    @POST("reservas")
    Call<Reserva> setReserva(@Body Reserva reserva);
}
