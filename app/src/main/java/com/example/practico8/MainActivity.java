package com.example.practico8;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtResultado;

    ConnectivityManager conexion;
    NetworkInfo infoRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResultado = findViewById(R.id.txtResultado);

        conexion = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        infoRed = conexion.getActiveNetworkInfo();
    }

    public void buscar(View view) {
        LibroTask libro = new LibroTask(txtResultado);
        libro.execute("http://www.googleapis.com/books/v1/volumes?q=pride+prejudice&maxResults=5&printType=books");
    }
}