package com.example.loginandfindcity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;


public class Activity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner spinner;
    String selected;
    Button btnSearch;
    TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        spinner = findViewById(R.id.spinner);
        btnSearch = findViewById(R.id.btnSearch);
        tvResults = findViewById(R.id.tvResults);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            int cityId=3453248;

            @Override
            public void onClick(View view) {
                if (!selected.isEmpty()) {
                    if (selected.equals("Budapest"))
                        cityId = 51643;
                    else if (selected.equals("Eger"))
                        cityId = 3453248;
                    else if (selected.equals("Debrecen"))
                        cityId = 3453089;
                    else if (selected.equals("Miskolc"))
                        cityId = 3453227;
                    else if (selected.equals("Sopron"))
                        cityId = 3453069;

                    //TODO http request to the API, endpoint: https://wft-geo-db.p.rapidapi.com/v1
                    //TODO get the GPS coordinates from the json file
                    String POSTS_API_URL = "http://geodb-free-service.wirefreethought.com/v1/geo/cities/51643";
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .GET()
                            .header("accept", "application/json")
                            .uri(URI.create(POSTS_API_URL))
                            .build();
                    try{
                        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                    }
                    catch (Exception e){
                        System.out.println("Error occured");
                    }

                    //  try {
                    //    URL url = new URL("http://geodb-free-service.wirefreethought.com/v1/geo/cities/" + cityId);
                    //    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    //    urlConnection.connect();
                    //    InputStream stream = urlConnection.getInputStream();
                    //    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                      //  StringBuffer buffer = new StringBuffer();

                        //String line = "";
                       // while((line = reader.readLine()) != null) {
                     //       buffer.append(line);
                      //  }
                      //  urlConnection.disconnect();
                      //  reader.close();
                      //  tvResults.setText(buffer.toString());
                  //  } catch (MalformedURLException e) {
                    //    e.printStackTrace();}
                    //catch (IOException e) {
                      //  e.printStackTrace();}

                    //TODO parse json for latitude and longitude
                    //Eger:
                     String latitude = "47.898888888";
                     String longitude = "20.374722222";
                     String uri = "https://maps.google.com/maps/api/staticmap?center=" + latitude + "," + longitude;
                     Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                     intent.setPackage("com.google.android.apps.maps");
                     startActivity(intent);
                }


            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        selected = parent.getItemAtPosition(pos).toString().trim();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // String selected = "";
    }


}