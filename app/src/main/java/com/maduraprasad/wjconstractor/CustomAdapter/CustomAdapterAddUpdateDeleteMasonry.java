package com.maduraprasad.wjconstractor.CustomAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.maduraprasad.wjconstractor._Masonry_Update_DeleteActivity;

import java.util.ArrayList;

import static maes.tech.intentanim.CustomIntent.customType;

public class CustomAdapterAddUpdateDeleteMasonry extends RecyclerView.Adapter<CustomAdapterAddUpdateDeleteMasonry.MasonryMYViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList masonry_id, masonry_name, masonry_address, masonry_nic, masonry_age,masonry_sname;
    Animation translate_anim;


    public CustomAdapterAddUpdateDeleteMasonry(Activity activity, Context context, ArrayList masonry_id, ArrayList masonry_name, ArrayList masonry_address, ArrayList masonry_nic, ArrayList masonry_age, ArrayList masonry_sname) {
        this.activity = activity;
        this.context = context;
        this.masonry_id = masonry_id;
        this.masonry_name = masonry_name;
        this.masonry_address = masonry_address;
        this.masonry_nic = masonry_nic;
        this.masonry_age = masonry_age;
        this.masonry_sname = masonry_sname;
    }
        @NonNull
        @Override
        public MasonryMYViewHolder onCreateViewHolder (@NonNull ViewGroup parent,int viewType){
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.my_rowaddmasonry, parent, false);
            return new MasonryMYViewHolder(view);
        }

        @Override
        public void onBindViewHolder (@NonNull MasonryMYViewHolder holder,int position){
            holder.masonry_id_txt.setText(String.valueOf(masonry_id.get(position)));
            holder.masonry_name_txt.setText(String.valueOf(masonry_name.get(position)));
            holder.masonry_address_txt.setText(String.valueOf(masonry_address.get(position)));
            holder.masonry_nic_txt.setText(String.valueOf(masonry_nic.get(position)));
            holder.masonry_age_txt.setText(String.valueOf(masonry_age.get(position)));
            holder.masonry_sname_txt.setText(String.valueOf(masonry_sname.get(position)));
            //Recyclerview onClickListener
            holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, _Masonry_Update_DeleteActivity.class);
                    intent.putExtra("id", String.valueOf(masonry_id.get(position)));
                    intent.putExtra("name", String.valueOf(masonry_name.get(position)));
                    intent.putExtra("address", String.valueOf(masonry_address.get(position)));
                    intent.putExtra("nic", String.valueOf(masonry_nic.get(position)));
                    intent.putExtra("age", String.valueOf(masonry_age.get(position)));
                    intent.putExtra("sname", String.valueOf(masonry_sname.get(position)));
                    activity.startActivityForResult(intent,1);
                    customType(context, "fadein-to-fadeout");
                }
            });
        }

        @Override
        public int getItemCount () {
            return masonry_id.size();
        }

        public class MasonryMYViewHolder extends RecyclerView.ViewHolder {

            TextView masonry_id_txt, masonry_name_txt, masonry_address_txt, masonry_nic_txt, masonry_age_txt,masonry_sname_txt;
            LinearLayout mainLayout;
            public MasonryMYViewHolder(@NonNull View itemView) {
                super(itemView);
                masonry_id_txt = itemView.findViewById(R.id.masonry_id_txt);
                masonry_name_txt = itemView.findViewById(R.id.masonry_name_txt);
                masonry_address_txt = itemView.findViewById(R.id.masonry_address_txt);
                masonry_nic_txt = itemView.findViewById(R.id.masonry_nic_txt);
                masonry_age_txt = itemView.findViewById(R.id.masonry_age_txt);
                masonry_sname_txt = itemView.findViewById(R.id.supervisor_name_txt);
                mainLayout = itemView.findViewById(R.id.mainLayoutMasonry);
                //Animate Recyclerview
                translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
                mainLayout.setAnimation(translate_anim);
            }
        }
    }
