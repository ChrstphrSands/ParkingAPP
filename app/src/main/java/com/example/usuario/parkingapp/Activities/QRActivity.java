package com.example.usuario.parkingapp.Activities;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPoolAdapter;
import com.example.usuario.parkingapp.R;
import com.google.zxing.WriterException;

public class QRActivity extends AppCompatActivity {

    String qrValue;
    ImageView imgQR;
    TextView txtCodigo;

    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        txtCodigo = findViewById(R.id.txtCodigo);
        imgQR = findViewById(R.id.imgQR);

        txtCodigo.setText(getIntent().getStringExtra("placa"));

        qrValue = getIntent().getStringExtra("placa").trim();

        generarQR(qrValue);

    }

    private void generarQR(String arValue) {
        qrgEncoder = new QRGEncoder(qrValue, null, QRGContents.Type.TEXT, 200);

        try {
            bitmap = qrgEncoder.encodeAsBitmap();
            imgQR.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
