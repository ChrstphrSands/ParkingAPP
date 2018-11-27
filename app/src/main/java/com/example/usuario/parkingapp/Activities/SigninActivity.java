package com.example.usuario.parkingapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.usuario.parkingapp.ClienteDetailActivity;
import com.example.usuario.parkingapp.Models.Cliente;
import com.example.usuario.parkingapp.Models.Reserva;
import com.example.usuario.parkingapp.Models.Vehiculo;
import com.example.usuario.parkingapp.R;
import com.example.usuario.parkingapp.Services.DataService;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;
import java.util.List;

public class SigninActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private Button btnSignIn, btnSignUp, btnGmail;
    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "SIGN_IN_ACTIVITY";

    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInOptions gso;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Cliente cliente;
    private ArrayList<Vehiculo> vehiculos = new ArrayList<>();
//    private List<Cliente> clientes;

    private String mUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(SigninActivity.this, ClienteDetailActivity.class));
                    mUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    DataService.getInstance().getClienteDetail(mUID);
                }
            }
        };

        btnSignIn = findViewById(R.id.btn_ingresar);
        btnGmail = findViewById(R.id.gmail);

        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
            .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                @Override
                public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                    Toast.makeText(SigninActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            })
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Ingresa un correo", Toast.LENGTH_LONG).show();
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Ingresa la contraseña", Toast.LENGTH_LONG).show();
                }
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password muy corta, ingresa mínimo 6 caracteres", Toast
                        .LENGTH_LONG).show();
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SigninActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(SigninActivity.this, "CreateUserWithEmail:onComplete", Toast.LENGTH_LONG).show();

                            if (!task.isSuccessful()) {
                                Toast.makeText(SigninActivity.this, "Autenticacion fallida" + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(SigninActivity.this, ReservaActivity.class));
                                finish();
                            }
                        }
                    });
            }
        });

        btnGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            GoogleSignInAccount account = result.getSignInAccount();
            firebaseAuthWithGoogle(account);
        } else {

        }

    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.d(TAG, "signInWithCredential:onComplete" + task.isSuccessful());
                    Toast.makeText(getApplicationContext(), account.getId(), Toast.LENGTH_LONG).show();

                    String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    cliente = new Cliente(account.getFamilyName(), "", 0, 0, account.getDisplayName(), 0, account
                        .getEmail(), vehiculos, currentUser);
                    saveCliente(cliente);

                    if (!task.isSuccessful()) {
                        Log.w(TAG, "signInWithCredential", task.getException());
                        Toast.makeText(SigninActivity.this, "Autenticacion fallida", Toast.LENGTH_LONG).show();
                    }
                }
            });

    }

    private void saveCliente(Cliente cliente)  {
        DataService.getInstance().setCliente(cliente);
    }
}
