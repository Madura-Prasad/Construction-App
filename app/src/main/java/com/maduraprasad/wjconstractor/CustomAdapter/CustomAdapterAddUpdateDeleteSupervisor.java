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
import com.maduraprasad.wjconstractor._Supervisor_Update_DeleteActivity;

import java.util.ArrayList;

import static maes.tech.intentanim.CustomIntent.customType;

public class CustomAdapterAddUpdateDeleteSupervisor extends RecyclerView.Adapter<CustomAdapterAddUpdateDeleteSupervisor.SuperviosrMYViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList supervisor_id, supervisor_name, supervisor_email, supervisor_password, supervisor_cpassword;

    Animation translate_anim;

    public CustomAdapterAddUpdateDeleteSupervisor(Activity activity, Context context, ArrayList supervisor_id, ArrayList supervisor_name, ArrayList supervisor_email, ArrayList supervisor_password, ArrayList supervisor_cpassword) {
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
    public SuperviosrMYViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_rowaddsuperviosr, parent, false);
        return new SuperviosrMYViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperviosrMYViewHolder holder, int position) {
        holder.supervisor_id_txt.setText(String.valueOf(supervisor_id.get(position)));
        holder.supervisor_name_txt.setText(String.valueOf(supervisor_name.get(position)));
        holder.supervisor_email_txt.setText(String.valueOf(supervisor_email.get(position)));
        holder.supervisor_password_txt.setText(String.valueOf(supervisor_password.get(position)));
        holder.supervisor_cpassword_txt.setText(String.valueOf(supervisor_cpassword.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, _Supervisor_Update_DeleteActivity.class);
                intent.putExtra("id", String.valueOf(supervisor_id.get(position)));
                intent.putExtra("name", String.valueOf(supervisor_name.get(position)));
                intent.putExtra("email", String.valueOf(supervisor_email.get(position)));
                intent.putExtra("password", String.valueOf(supervisor_password.get(position)));
                intent.putExtra("cpassword", String.valueOf(supervisor_cpassword.get(position)));
                activity.startActivityForResult(intent, 1);
                customType(context, "fadein-to-fadeout");
            }
        });
    }

    @Override
    public int getItemCount() {
        return supervisor_id.size();
    }

    public class SuperviosrMYViewHolder extends RecyclerView.ViewHolder {
        TextView supervisor_id_txt, supervisor_name_txt, supervisor_email_txt, supervisor_password_txt, supervisor_cpassword_txt;
        LinearLayout mainLayout;

        public SuperviosrMYViewHolder(@NonNull View itemView) {
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
