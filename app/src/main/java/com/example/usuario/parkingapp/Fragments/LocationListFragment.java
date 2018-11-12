package com.example.usuario.parkingapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.usuario.parkingapp.R;
import com.example.usuario.parkingapp.Adapters.LocationsAdapter;
import com.example.usuario.parkingapp.Services.DataService;


public class LocationListFragment extends Fragment {
    public LocationListFragment() {
        // Required empty public constructor
    }

    public static LocationListFragment newInstance() {
        LocationListFragment fragment = new LocationListFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_locations);
        recyclerView.setHasFixedSize(true);

        LocationsAdapter adapter = new LocationsAdapter(DataService.getInstance().getBootcampLocationsWithin10MilesOfZip(92284));
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        return view;
    }
}
