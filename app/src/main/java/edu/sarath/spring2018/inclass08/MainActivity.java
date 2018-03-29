package edu.sarath.spring2018.inclass08;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    private String readJsonFile(InputStream inputStream) {
// TODO Auto-generated method stub
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte bufferByte[] = new byte[1024];
        int length;
        try {
            while ((length = inputStream.read(bufferByte)) != -1) {
                outputStream.write(bufferByte, 0, length);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney, Australia, and move the camera.


        Gson gson = new Gson();
        InputStream inputStream = this.getResources().openRawResource(R.raw.trip);
        String jsonString = readJsonFile(inputStream);
        TripClass tripClass = gson.fromJson(jsonString, TripClass.class);
        Log.d("print message---", "Valueeeee-->" + gson.toJson(tripClass));
        LatLngBounds.Builder builder = LatLngBounds.builder();

        List<points> locallist = tripClass.points;
        Log.d("print message---", "Valueeeee-->" + locallist.size());
        LatLng stpnt = null;
        LatLng endpnt = null;
        Polyline line = null;
        PolylineOptions polylineOptions = new PolylineOptions();
        for (int i = 0; i < locallist.size(); i++) {

            points localpoints = locallist.get(i);

            if (i == 0) {
                stpnt = new LatLng(localpoints.getLatitude(), localpoints.getLongitude());
                builder.include(stpnt);
                mMap.addMarker(new MarkerOptions().position(stpnt).title("Start Location"));
                polylineOptions.add(new LatLng(localpoints.getLatitude(), localpoints.getLongitude()));
            } else if (i == locallist.size() - 1) {

                endpnt = new LatLng(localpoints.getLatitude(), localpoints.getLongitude());
                builder.include(endpnt);
                mMap.addMarker(new MarkerOptions().position(endpnt).title("End Location"));
                polylineOptions.add(new LatLng(localpoints.getLatitude(), localpoints.getLongitude()));
//                Log.d("print message---", "lat-->" + localpoints.getLatitude());
//                Log.d("print message---", "lat-->" + localpoints.getLongitude());
            } else {
                builder.include(new LatLng(localpoints.getLatitude(), localpoints.getLongitude()));
                Log.d("print inside---", "lat-->" + localpoints.getLatitude());
                polylineOptions.add(new LatLng(localpoints.getLatitude(), localpoints.getLongitude()));
            }
        }
        mMap.addPolyline(polylineOptions.width(5).color(Color.BLUE));
        final LatLngBounds bounds = builder.build();
        Log.d("print message---", "lat-->" + bounds.toString());

//        int width = getResources().getDisplayMetrics().widthPixels;
//        int height = getResources().getDisplayMetrics().heightPixels;
//        int padding = (int) (width * 0.12); // offset from edges of the map 12% of screen

        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
            }
        });



        //builder.include(new LatLng(localpoints.getLatitude(), localpoints.getLongitude()));

    }
}
