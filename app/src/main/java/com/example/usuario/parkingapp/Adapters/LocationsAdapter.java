package com.example.usuario.parkingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.usuario.parkingapp.Activities.CocheraDetailActivity;
import com.example.usuario.parkingapp.Holders.LocationsViewHolder;
import com.example.usuario.parkingapp.Models.Cochera;
import com.example.usuario.parkingapp.R;
import com.example.usuario.parkingapp.Services.DataService;

import java.io.Serializable;
import java.util.ArrayList;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsViewHolder>{
    private Context context;
    private ArrayList<Cochera> cocheras;

    public LocationsAdapter(Context context, ArrayList<Cochera> locations) {
        this.context = context;
        this.cocheras = locations;
    }

    @Override
    public void onBindViewHolder(LocationsViewHolder holder, final int position) {
        DataService.getInstance().getCocheras();
        final Cochera cochera = cocheras.get(position);
        holder.updateUI(cochera);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CocheraDetailActivity.class);
                intent.putExtra("cochera_id", cochera.getCocheraId());
                intent.putExtra("cochera_foto", cochera.getFoto());
                intent.putExtra("cochera_nombre", cochera.getNombre());
                intent.putExtra("cochera_direccion", cochera.getDireccion());
                intent.putExtra("cochera_descripcion", cochera.getDescripcion());
                intent.putExtra("cochera_servicios", (Serializable) cochera.getServicios());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cocheras.size();
    }

    @Override
    public LocationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_location, parent, false);
        return new LocationsViewHolder(card);
    }
}
