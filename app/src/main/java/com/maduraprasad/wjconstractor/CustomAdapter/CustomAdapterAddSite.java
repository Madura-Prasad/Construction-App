package com.maduraprasad.wjconstractor.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maduraprasad.wjconstractor.R;

import java.util.ArrayList;

public class CustomAdapterAddSite extends RecyclerView.Adapter<CustomAdapterAddSite.MyViewHolder> {
    private Context context;
    private ArrayList site_id, site_name,site_supervisor_name, site_location;

    Animation translate_anim;

    public CustomAdapterAddSite(Context context, ArrayList site_id, ArrayList site_name, ArrayList site_supervisor_name, ArrayList site_location) {
        this.context = context;
        this.site_id = site_id;
        this.site_name = site_name;
        this.site_supervisor_name = site_supervisor_name;
        this.site_location = site_location;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_rowaddsite, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.site_id_txt.setText(String.valueOf(site_id.get(position)));
        holder.site_name_txt.setText(String.valueOf(site_name.get(position)));
        holder.site_supervisor_name_txt.setText(String.valueOf(site_supervisor_name.get(position)));
        holder.site_location_txt.setText(String.valueOf(site_location.get(position)));
    }

    @Override
    public int getItemCount() {
        return site_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView site_id_txt, site_name_txt,site_supervisor_name_txt, site_location_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            site_id_txt = itemView.findViewById(R.id.site_id_txt);
            site_name_txt = itemView.findViewById(R.id.site_name_txt);
            site_supervisor_name_txt = itemView.findViewById(R.id.site_supervisor_name_txt);
            site_location_txt = itemView.findViewById(R.id.site_location_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
