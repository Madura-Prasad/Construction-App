package com.maduraprasad.wjconstractor.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.maduraprasad.wjconstractor.AdminSignInActivity;
import com.maduraprasad.wjconstractor.R;
import com.maduraprasad.wjconstractor.SupervisorSignInActivity;

import static maes.tech.intentanim.CustomIntent.customType;


public class SigninFragment extends Fragment {

    CardView admin, supervisor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        admin = view.findViewById(R.id.admin);
        supervisor = view.findViewById(R.id.supervisor);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AdminSignInActivity.class);
                startActivity(intent);
                customType(getContext(), "fadein-to-fadeout");
            }
        });

        supervisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SupervisorSignInActivity.class);
                startActivity(intent);
                customType(getContext(), "fadein-to-fadeout");
            }
        });

        return view;
    }

}