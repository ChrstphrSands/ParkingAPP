package com.example.usuario.parkingapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.usuario.parkingapp.Models.Cliente;
import com.example.usuario.parkingapp.Models.Servicio;
import com.example.usuario.parkingapp.R;
import com.example.usuario.parkingapp.Services.DataService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CocheraDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView nombre;
    private TextView descripcion;
    private TextView direccion;
    private ImageView foto;
    private Button btn_reserva;
    private List<Servicio> mServicios = new ArrayList<>();
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cochera_detail);

        String mFoto = getIntent().getStringExtra("cochera_foto");
        String mNombre = getIntent().getStringExtra("cochera_nombre");
        String mDireccion = getIntent().getStringExtra("cochera_direccion");
        String mDescripcion = getIntent().getStringExtra("cochera_descripcion");
        mServicios = (List<Servicio>) getIntent().getSerializableExtra("cochera_servicios");

        foto = findViewById(R.id.cochera_foto);
        nombre = findViewById(R.id.cochera_nombre);
        btn_reserva = findViewById(R.id.btn_reserva);
        direccion = findViewById(R.id.cochera_direccion);
        descripcion = findViewById(R.id.cochera_descripcion);

        String uri = "http://sistemaparqueo.azurewebsites.net/uploads/cocheras/" + mFoto;

        Glide.with(getApplicationContext())
            .load(uri)
            .into(foto);

        nombre.setText(mNombre);
        descripcion.setText(mDescripcion);
        direccion.setText(mDireccion);

        Log.d("Servicios", mServicios.toString());

        cliente = DataService.getInstance().getCliente(4);

        Log.d("CocheraDetailActivity", String.valueOf(cliente));

        btn_reserva.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(CocheraDetailActivity.this, ReservaActivity.class);
        intent.putExtra("servicios", (Serializable) mServicios);
        startActivity(intent);
    }
}
