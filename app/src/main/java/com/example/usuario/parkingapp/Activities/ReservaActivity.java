package com.example.usuario.parkingapp.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import com.example.usuario.parkingapp.Api.RetrofitInstance;
import com.example.usuario.parkingapp.Libraries.DateTimeWheel.TimeWheel.TimePickerPopWin;
import com.example.usuario.parkingapp.Models.Cliente;
import com.example.usuario.parkingapp.Models.Reserva;
import com.example.usuario.parkingapp.Models.Servicio;
import com.example.usuario.parkingapp.Models.Vehiculo;
import com.example.usuario.parkingapp.R;
import com.example.usuario.parkingapp.Services.DataService;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

public class ReservaActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnEntrada;
    Button btnSalida;
    Button btnConfirmar;
    Button btnCancelar;
    TextView textView;
    TextView txtSalida;
    TextView txtIngreso;
    TextView txtCosto;
    String horaEntrada;
    String horaSalida;
    int vehiculoId;
    int servicioId;
    int cocheraId;
    String placa;

    Cliente cliente = new Cliente();
    Reserva reserva = new Reserva();
    Servicio servicio = new Servicio();

    List<String> vehiculosString = new ArrayList<>();
    List<Vehiculo> vehiculosList = new ArrayList<>();
    List<Servicio> serviciosList = new ArrayList<>();
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        textView = findViewById(R.id.text_cliente);
        btnEntrada = findViewById(R.id.btn_entrada);
        btnSalida = findViewById(R.id.btn_salida);
        btnConfirmar = findViewById(R.id.btn_confirmar);
        btnCancelar = findViewById(R.id.btn_cancelar);

        txtIngreso = findViewById(R.id.txt_ingreso);
        txtSalida = findViewById(R.id.txt_salida);
        txtCosto = findViewById(R.id.txt_costo);

        cocheraId = getIntent().getIntExtra("cochera_id", 0);
        serviciosList = (List<Servicio>) getIntent().getSerializableExtra("servicios");

        cliente = DataService.getInstance().getCliente(4);

        vehiculosList = cliente.getVehiculos();
        Log.d("Vehiculos", String.valueOf(cliente));

        servicioId = serviciosList.get(0).getId();

        Log.d("Vehiculos", String.valueOf(vehiculosList.get(0).getId()));

        for (int i = 0; i < cliente.getVehiculos().size(); i++) {
            vehiculosString.add(String.valueOf(cliente.getVehiculos().get(i).getPlaca()));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, vehiculosString);
        MaterialBetterSpinner materialBetterSpinner = findViewById(R.id.spinner);
        materialBetterSpinner.setAdapter(arrayAdapter);

        textView.setText("Bienvenido " + cliente.getNombre());
        txtCosto.setText("El costo del servicio por hora es: " + serviciosList.get(0).getCosto());

        btnEntrada.setOnClickListener(this);
        btnSalida.setOnClickListener(this);

        materialBetterSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vehiculoId = vehiculosList.get(position).getId();
                placa = vehiculosList.get(position).getPlaca();
            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserva = new Reserva("12:00:00", 0.0, 1, vehiculoId, servicioId, "", "13:00:00", "");
                saveData(reserva);
                Intent intent = new Intent(ReservaActivity.this, QRActivity.class);
                intent.putExtra("placa", placa);
                startActivity(intent);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservaActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void openTimeEntry(View view) {
        TimePickerPopWin pickerPopWin = new TimePickerPopWin.Builder(ReservaActivity.this, new TimePickerPopWin
            .OnTimePickedListener() {
            @Override
            public void onTimePickCompleted(int hour, int min, int sec, String meridium, String timeDesc) {
                horaEntrada = timeDesc;
                txtIngreso.setText("Hora ingreso: " + timeDesc);
                Toast.makeText(ReservaActivity.this, timeDesc, Toast.LENGTH_SHORT).show();
            }

        }).textConfirm("Guardar")
            .textCancel("Cancelar")
            .btnTextSize(16)
            .viewTextSize(25)
            .colorCancel(Color.parseColor("#999999"))
            .colorConfirm(Color.parseColor("#009900"))
            .build();

        pickerPopWin.showPopWin(this);
    }

    public void openTimeExit(View view) {
        TimePickerPopWin pickerPopWin = new TimePickerPopWin.Builder(ReservaActivity.this, new TimePickerPopWin
            .OnTimePickedListener() {
            @Override
            public void onTimePickCompleted(int hour, int min, int sec, String meridium, String timeDesc) {
                horaSalida = timeDesc;
                txtSalida.setText("Hora salida: " + timeDesc);
                Toast.makeText(ReservaActivity.this, timeDesc, Toast.LENGTH_SHORT).show();
            }

        }).textConfirm("Guardar")
            .textCancel("Cancelar")
            .btnTextSize(16)
            .viewTextSize(25)
            .colorCancel(Color.parseColor("#999999"))
            .colorConfirm(Color.parseColor("#009900"))
            .build();

        pickerPopWin.showPopWin(this);
    }

    private void saveData(Reserva reserva)  {
        DataService.getInstance().setReserva(reserva);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_entrada:
                openTimeEntry(v);
                break;
            case R.id.btn_salida:
                openTimeExit(v);
                break;
        }
    }

}
