package com.example.usuario.parkingapp.Fragments;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.usuario.parkingapp.Models.Cochera;
import com.example.usuario.parkingapp.Models.Devslopes;
import com.example.usuario.parkingapp.R;
import com.example.usuario.parkingapp.Services.DataService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private MarkerOptions userMarker;
    private LocationListFragment mListFragment;

    public MainFragment() {
        // Required empty public constructor
    }


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
            .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mListFragment = (LocationListFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id
            .container_locations_list);

        if (mListFragment == null) {
            mListFragment = LocationListFragment.newInstance();
            getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_locations_list, mListFragment)
                .commit();
        }

//        final EditText zipText = view.findViewById(R.id.zip_text);
//        zipText.setOnKeyListener(new View.OnKeyListener() {
//
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if ((event.getAction() == KeyEvent.ACTION_DOWN) && keyCode == KeyEvent.KEYCODE_ENTER) {
//                    String text = zipText.getText().toString();
//                    int zip = Integer.parseInt(text);
//
//                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//
//                    imm.hideSoftInputFromWindow(zipText.getWindowToken(), 0);
//
//                    showList();
//                    updateMapForZip(zip);
//                    return true;
//                }
//                return false;
//            }
//        });
        hideList();
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    public void setUserMarker(LatLng latLng) {
        if (userMarker == null) {
            userMarker = new MarkerOptions().position(latLng).title("Ubicacion actual");
            mMap.addMarker(userMarker);
            Log.v("Wiwi", "Latitud actual: " + latLng.latitude + " Long: " + latLng.longitude);
        }

        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude,latLng.longitude, 1);
            // int zip = Integer.parseInt(addresses.get(0).getPostalCode());
            updateMap(23000);
        } catch (IOException exception){

        }

        updateMap(23000);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
    }

    private void updateMapForZip(int zipcode) {

        ArrayList<Devslopes> locations = DataService.getInstance().getBootcampLocationsWithin10MilesOfZip(zipcode);

        for (int x = 0; x < locations.size(); x++) {
            Devslopes loc = locations.get(x);
            MarkerOptions marker = new MarkerOptions().position(new LatLng(loc.getLatitude(),loc.getLongitude()));
            marker.title(loc.getLocationTitle());
            marker.snippet(loc.getLocationAddress());
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin));
            mMap.addMarker(marker);
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker mark) {
                    if (mark.equals(mark)) {
                        Marker marker1;
                        showList();
                    }
                    return false;
                }
            });
        }
    }

    private void updateMap(int zipcode) {

        hideList();
        List<Cochera> cocheras = DataService.getInstance().getCocheras();

        for (int x = 0; x < cocheras.size(); x++) {
            Cochera coch = cocheras.get(x);
            MarkerOptions marker = new MarkerOptions().position(new LatLng(coch.getLatitud(),coch.getLongitud()));
            marker.title(coch.getNombre());
            marker.snippet(coch.getDescripcion());
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin));
            mMap.addMarker(marker);
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker mark) {
                    if (mark.equals(mark)) {
                        Marker marker1;
                        showList();
                    }
                    return false;
                }
            });
        }
    }

    private void hideList() {
        getActivity().getSupportFragmentManager().beginTransaction().hide(mListFragment).commit();
    }

    private void showList() {
        getActivity().getSupportFragmentManager().beginTransaction().show(mListFragment).commit();
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        return true;
    }
}
