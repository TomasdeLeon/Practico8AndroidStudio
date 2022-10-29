package com.example.practico8;

import android.net.Uri;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LibroTask extends AsyncTask<String,Void,String> {

    private TextView txtResultado;

    public LibroTask(TextView txtResultado) {
        this.txtResultado = txtResultado;
    }

    @Override
    protected String doInBackground(String... strings) {

        if (strings.length == 0) {
            return null;
        }

        try {
            final String FORECAST_BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
            final String QUERY_PARAM = "q";
            final String MAX_RESULTS = "maxResults";
            final String PRINT_TYPE = "printType";

            InputStream inputStream;

            Uri builtURI = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, strings[0])
                    .appendQueryParameter(MAX_RESULTS, "1")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build();

            URL requestURL = new URL(builtURI.toString());
            HttpURLConnection connection = (HttpURLConnection) requestURL.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            inputStream = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder salida = new StringBuilder();
            String linea = reader.readLine();

            while (linea != null) {
                salida.append(linea+"\n");
                linea = reader.readLine();
            }

            reader.close();
            inputStream.close();

            return salida.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPostExecute(String resultado) {
        txtResultado.setText(resultado);
    }


}
