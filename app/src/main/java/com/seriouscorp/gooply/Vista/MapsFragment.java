package com.seriouscorp.gooply.Vista;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.seriouscorp.gooply.R;
import com.seriouscorp.gooply.Modelo.DocMapa;

import java.util.ArrayList;

import static com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom;
import static com.seriouscorp.gooply.Presentador.UtilidadesVarias.irInicio;
import static com.seriouscorp.gooply.Presentador.UtilidadesVarias.marcar;

public class MapsFragment extends Fragment{

    private FusedLocationProviderClient locationProviderClient;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    ArrayList<DocMapa> mapaArrayList = new ArrayList<>();
    Boolean alfa = false;
    ConstraintLayout layout;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            boolean success = googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.stylemapretro));
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            dontoy(googleMap);
            setMarcadoresdoc(googleMap);
        }
    };


    private void setMarcadoresdoc(final GoogleMap googleMap) {

        reference.child("Locaciones").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Marker marker;
                int i = 0;
                if(dataSnapshot.exists()){
                    mapaArrayList.removeAll(mapaArrayList);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        DocMapa mapa = snapshot.getValue(DocMapa.class);
                        mapaArrayList.add(mapa);
                        String latitud = mapa.getLatitud();
                        String longitud = mapa.getLongitud();

                        marker = googleMap.addMarker(new MarkerOptions()
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                                .position(new LatLng(Double.parseDouble(latitud), Double.parseDouble(longitud))
                                ));
                        marker.setTag(i);
                        accionMark(googleMap);
                        i++;
                    }
                    layout.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void accionMark(GoogleMap googleMap) {
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {;
            @Override
            public boolean onMarkerClick(final Marker marker) {

                final FlatDialog flatDialog = new FlatDialog(getContext());
                flatDialog.setTitle(
                        "Doctor: " + mapaArrayList.get(Integer.parseInt(marker.getTag().toString())).getNombre() +
                        "\nEspecialidad: " + mapaArrayList.get(Integer.parseInt(marker.getTag().toString())).getTipodoc() +
                        "\nCelular: " +mapaArrayList.get(Integer.parseInt(marker.getTag().toString())).getCelular())
                        .setFirstButtonText("Contactar")
                        .setFirstButtonColor(R.color.verduzco)
                        .setSecondButtonText("Cancelar")
                        .setSecondButtonColor(R.color.rojo)
                        .withFirstButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                marcar(getContext(), mapaArrayList.get(Integer.parseInt(marker.getTag().toString())).getCelular());
                                alfa = true;
                            }
                        })
                        .withSecondButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flatDialog.dismiss();
                            }
                        })
                        .show();

                return false;
            }
        });
    }

    private void dontoy(final GoogleMap googleMap) {
        locationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);
        locationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    googleMap.moveCamera(newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 14f));
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        layout = view.findViewById(R.id.layoutcargandom);
        return view ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    @Override
    public void onResume() {
        if (alfa){
            irInicio(getContext());
            alfa = false;
        }
        super.onResume();
    }

}