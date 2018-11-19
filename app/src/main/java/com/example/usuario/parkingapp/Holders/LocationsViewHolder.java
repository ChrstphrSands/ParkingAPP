package com.example.usuario.parkingapp.Holders;

import android.content.Context;
import android.media.tv.TvContract;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.usuario.parkingapp.Models.Cochera;
import com.example.usuario.parkingapp.R;
import com.example.usuario.parkingapp.Models.Devslopes;

public class LocationsViewHolder extends RecyclerView.ViewHolder {
    private ImageView locationImage;
    private TextView locationTitle;
    private TextView locationAddress;
    public Context context;

    public LocationsViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        locationImage = itemView.findViewById(R.id.location_img);
        locationTitle = itemView.findViewById(R.id.location_title);
        locationAddress = itemView.findViewById(R.id.location_address);
    }

    public void updateUI(Cochera cochera) {
        String foto = "http://sistemaparqueo.azurewebsites.net/uploads/cocheras/" + cochera.getFoto();
        int resource = locationImage.getResources().getIdentifier(foto, null, locationImage.getContext().getPackageName
            ());
        locationImage.setImageResource(resource);

        Glide.with(context.getApplicationContext())
            .load(foto)
            .into(locationImage);

        locationTitle.setText(cochera.getNombre());
        locationAddress.setText(cochera.getDescripcion());
    }
}
