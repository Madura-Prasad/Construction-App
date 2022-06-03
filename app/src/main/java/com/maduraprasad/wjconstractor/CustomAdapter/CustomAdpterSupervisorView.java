package com.maduraprasad.wjconstractor.CustomAdapter;

import android.app.Activity;
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

public class CustomAdpterSupervisorView extends RecyclerView.Adapter<CustomAdpterSupervisorView.SuperviosrViewMYViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList supervisor_id, supervisor_name, supervisor_email, supervisor_password, supervisor_cpassword;

    Animation translate_anim;

    public CustomAdpterSupervisorView(Activity activity, Context context, ArrayList supervisor_id, ArrayList supervisor_name, ArrayList supervisor_email, ArrayList supervisor_password, ArrayList supervisor_cpassword) {
        this.activity = activity;
        this.context = context;
        this.supervisor_id = supervisor_id;
        this.supervisor_name = supervisor_name;
        this.supervisor_email = supervisor_email;
        this.supervisor_password = supervisor_password;
        this.supervisor_cpassword = supervisor_cpassword;
    }

    @NonNull
    @Override
    public SuperviosrViewMYViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_rowaddsuperviosr, parent, false);
        return new SuperviosrViewMYViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperviosrViewMYViewHolder holder, int position) {
        holder.supervisor_id_txt.setText(String.valueOf(supervisor_id.get(position)));
        holder.supervisor_name_txt.setText(String.valueOf(supervisor_name.get(position)));
        holder.supervisor_email_txt.setText(String.valueOf(supervisor_email.get(position)));
        holder.supervisor_password_txt.setText(String.valueOf(supervisor_password.get(position)));
        holder.supervisor_cpassword_txt.setText(String.valueOf(supervisor_cpassword.get(position)));
    }

    @Override
    public int getItemCount() {
        return supervisor_id.size();
    }

    public class SuperviosrViewMYViewHolder extends RecyclerView.ViewHolder {
        TextView supervisor_id_txt, supervisor_name_txt, supervisor_email_txt, supervisor_password_txt, supervisor_cpassword_txt;
        LinearLayout mainLayout;
        public SuperviosrViewMYViewHolder(@NonNull View itemView) {
            super(itemView);
            supervisor_id_txt = itemView.findViewById(R.id.supervisor_id_txt);
            supervisor_name_txt = itemView.findViewById(R.id.supervisor_name_txt);
            supervisor_email_txt = itemView.findViewById(R.id.supervisor_email_txt);
            supervisor_password_txt = itemView.findViewById(R.id.supervisor_password_txt);
            supervisor_cpassword_txt = itemView.findViewById(R.id.supervisor_cpassword_txt);
            mainLayout = itemView.findViewById(R.id.mainLayoutSupervisor);
            //Animate Recyclerview
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
