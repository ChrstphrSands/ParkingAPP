package com.example.usuario.parkingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.usuario.parkingapp.Models.Cliente;
import com.example.usuario.parkingapp.Services.DataService;
import com.google.firebase.auth.FirebaseAuth;
import com.james602152002.floatinglabeledittext.FloatingLabelEditText;
import com.james602152002.floatinglabeledittext.validator.RegexValidator;
import com.libizo.CustomEditText;

import java.util.List;

public class ClienteDetailActivity extends AppCompatActivity {

    private FloatingLabelEditText nombre;
    private FloatingLabelEditText dni;
    private FloatingLabelEditText direccion;
    private FloatingLabelEditText celular;
    private FloatingLabelEditText telefono;
    private List<Cliente> cliente;
    private String mUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_detail);

        mUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        cliente = DataService.getInstance().getClienteDetail(mUID);

        Log.d("UID Cliente", mUID);
        Log.d("Datos Cliente", String.valueOf(cliente));

        nombre = findViewById(R.id.cliente_nombre);
        dni = findViewById(R.id.cliente_dni);
        direccion = findViewById(R.id.cliente_direccion);
        celular = findViewById(R.id.cliente_celular);
        telefono = findViewById(R.id.cliente_telefono);

        nombre.setText(cliente.get(0).getNombre());

        if (cliente.get(0).getDNI() == 0) {
            dni.setText("00000000");
        } else {
            dni.setText(cliente.get(0).getDNI());
        }

        if (cliente.get(0).getDireccion().length() == 0) {
            direccion.setText("Actualice su direccion");
        } else {
            direccion.setText(cliente.get(0).getDireccion());
        }

        if (cliente.get(0).getCelular() == 0) {
            celular.setText("Actualice su celular");
        } else {
            celular.setText(cliente.get(0).getCelular());
        }

        if (cliente.get(0).getTelefono() == 0) {
            telefono.setText("Actualice su telefono");
        } else {
            telefono.setText(cliente.get(0).getTelefono());
        }

        nombre.addValidator(new RegexValidator("Ingresar solo letras", "[A-Za-z]+$"));

    }
}
