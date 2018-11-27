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

    @GET("clientes")
    Call<List<Cliente>> getClienteDetail(@Query("UID") String UID);

    @POST("clientes")
    Call<Cliente> setCliente(@Body Cliente cliente);

    @PUT("clientes/{id}")
    Call<Cliente> updateCliente(@Path("id") int id, @Body Cliente cliente);

    @GET("servicios")
    Call<List<Servicio>> getServicios(@Query("cochera_id") int cochera_id);

    @POST("reservas")
    Call<Reserva> setReserva(@Body Reserva reserva);


}
