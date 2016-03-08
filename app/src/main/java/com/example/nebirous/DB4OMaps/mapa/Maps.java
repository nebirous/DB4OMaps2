package com.example.nebirous.DB4OMaps.mapa;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nebirous.DB4OMaps.Db4O;
import com.example.nebirous.DB4OMaps.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class Maps extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Db4O bd;
    private PolylineOptions ruta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        bd=new Db4O(this);
        mMap = googleMap;
        PolylineOptions rectOptions = new PolylineOptions();
        List<Posicion> posicionList = bd.getConsulta();
        Posicion puntoInicial=posicionList.get(0);
        LatLng punto = new LatLng(puntoInicial.getLatitud(), puntoInicial.getLongitud());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(punto));
        for(Posicion p : posicionList){
            rectOptions.add(new LatLng(p.getLatitud(),p.getLongitud()));
        }
        rectOptions.color(Color.MAGENTA);
        rectOptions.width(4);
        rectOptions.visible(true);

        mMap.addPolyline(rectOptions);


        LatLng sydney = new LatLng(37.35, -122.0);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        Polyline polyline = mMap.addPolyline(rectOptions);
        bd.close();
    }
}


