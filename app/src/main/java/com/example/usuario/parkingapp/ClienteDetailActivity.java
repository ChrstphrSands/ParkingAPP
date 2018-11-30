package com.example.usuario.parkingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.usuario.parkingapp.Models.Cliente;
import com.example.usuario.parkingapp.Models.Vehiculo;
import com.example.usuario.parkingapp.Services.DataService;
import com.google.firebase.auth.FirebaseAuth;
import com.james602152002.floatinglabeledittext.FloatingLabelEditText;
import com.james602152002.floatinglabeledittext.validator.RegexValidator;

import java.util.List;

public class ClienteDetailActivity extends AppCompatActivity{

    private FloatingLabelEditText nombre;
    private FloatingLabelEditText dni;
    private FloatingLabelEditText direccion;
    private FloatingLabelEditText celular;
    private FloatingLabelEditText telefono;
    private List<Cliente> clientes;
    private Cliente cliente;
    private String mUID;
    private List<Vehiculo> vehiculos;

    private Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_detail);

        mUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        cliente = DataService.getInstance().getClienteDetail(mUID);

        Log.d("UID Cliente", mUID);
        Log.d("Datos Cliente", String.valueOf(clientes));

        nombre = findViewById(R.id.cliente_nombre);
        dni = findViewById(R.id.cliente_dni);
        direccion = findViewById(R.id.cliente_direccion);
        celular = findViewById(R.id.cliente_celular);
        telefono = findViewById(R.id.cliente_telefono);
        guardar = findViewById(R.id.btn_actualizar);

        nombre.setText(clientes.get(0).getNombre());

        if (clientes.get(0).getDNI() == 0) {
            dni.setText("00000000");
        } else {
            dni.setText(String.valueOf(clientes.get(0).getDNI()));
        }

        if (clientes.get(0).getDireccion().length() == 0) {
            direccion.setText("Actualice su direccion");
        } else {
            direccion.setText(clientes.get(0).getDireccion());
        }

        if (clientes.get(0).getCelular() == 0) {
            celular.setText("Actualice su celular");
        } else {
            celular.setText(String.valueOf(clientes.get(0).getCelular()));
        }

        if (clientes.get(0).getTelefono() == 0) {
            telefono.setText("Actualice su telefono");
        } else {
            telefono.setText(String.valueOf(clientes.get(0).getTelefono()));
        }

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cliente = new Cliente(
                    String.valueOf(nombre.getText()),
                    String.valueOf(direccion.getText()),
                    Integer.parseInt(String.valueOf(celular.getText())),
                    Integer.parseInt(String.valueOf(telefono.getText())),
                    String.valueOf(nombre.getText()),
                    Integer.parseInt(String.valueOf(dni.getText())),
                    "nuevo@gmail.com",
                    vehiculos,
                    mUID
                );

                DataService.getInstance().updateCliente(clientes.get(0).getCliente_id(), cliente);
                Log.d("Guardar", "Guardado");
            }
        });

    }
}
