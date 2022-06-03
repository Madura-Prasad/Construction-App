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

import com.maduraprasad.wjconstractor.AdminDelete_MasonryActivity;
import com.maduraprasad.wjconstractor.Mark_AttendenceActivity;
import com.maduraprasad.wjconstractor.R;

import java.util.ArrayList;

import static maes.tech.intentanim.CustomIntent.customType;

public class CustomAdpterAttendence extends RecyclerView.Adapter<CustomAdpterAttendence.AttendenceViewMYViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList masonry_id, masonry_name;
    Animation translate_anim;

    public CustomAdpterAttendence(Activity activity, Context context, ArrayList masonry_id, ArrayList masonry_name) {
        this.activity = activity;
        this.context = context;
        this.masonry_id = masonry_id;
        this.masonry_name = masonry_name;
    }

    @NonNull
    @Override
    public AttendenceViewMYViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_rowattendence, parent, false);
        return new AttendenceViewMYViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendenceViewMYViewHolder holder, int position) {
        holder.masonry_id_txt.setText(String.valueOf(masonry_id.get(position)));
        holder.masonry_name_txt.setText(String.valueOf(masonry_name.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Mark_AttendenceActivity.class);
                intent.putExtra("id", String.valueOf(masonry_id.get(position)));
                intent.putExtra("name", String.valueOf(masonry_name.get(position)));
                activity.startActivityForResult(intent,1);
                customType(context, "fadein-to-fadeout");
            }
        });

    }

    @Override
    public int getItemCount() {
        return masonry_id.size();
    }

    public class AttendenceViewMYViewHolder extends RecyclerView.ViewHolder {
        TextView masonry_id_txt, masonry_name_txt;
        LinearLayout mainLayout;

        public AttendenceViewMYViewHolder(@NonNull View itemView) {
            super(itemView);
            masonry_id_txt = itemView.findViewById(R.id.masonry_id_txt);
            masonry_name_txt = itemView.findViewById(R.id.masonry_name_txt);
            mainLayout = itemView.findViewById(R.id.mainLayoutMasonry);
            //Animate Recyclerview
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
