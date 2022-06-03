package com.maduraprasad.wjconstractor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import static maes.tech.intentanim.CustomIntent.customType;

public class Supervisor_MainHomeActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView addUpdateSupervisor, addUpdateMasonry, attendence, salary;
    private TextView supervisor_user;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "my_pref_super";
    private static final String KEY_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor__main_home);
        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        //initialize variable
        addUpdateSupervisor = findViewById(R.id.supervisor);
        addUpdateMasonry = findViewById(R.id.masonry);
        attendence = findViewById(R.id.attendence);
        salary = findViewById(R.id.salary);
        supervisor_user = findViewById(R.id.supervisor_username);


        addUpdateSupervisor.setOnClickListener(this);
        addUpdateMasonry.setOnClickListener(this);
        attendence.setOnClickListener(this);
        salary.setOnClickListener(this);

        //share_preference
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        if (name != null) {
            supervisor_user.setText(name + "!");
        }

    }


    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.supervisor:
                i = new Intent(Supervisor_MainHomeActivity.this, AdminSupervisorActivity.class);
                startActivity(i);
                customType(Supervisor_MainHomeActivity.this, "fadein-to-fadeout");
                break;

            case R.id.masonry:
                i = new Intent(Supervisor_MainHomeActivity.this, AdminMasonryActivity.class);
                startActivity(i);
                customType(Supervisor_MainHomeActivity.this, "fadein-to-fadeout");
                break;
            case R.id.attendence:
                i = new Intent(Supervisor_MainHomeActivity.this, Attendence_Activity.class);
                startActivity(i);
                customType(Supervisor_MainHomeActivity.this, "fadein-to-fadeout");
                break;
            case R.id.salary:
                i = new Intent(Supervisor_MainHomeActivity.this, CalculateSalary.class);
                startActivity(i);
                customType(Supervisor_MainHomeActivity.this, "fadein-to-fadeout");
                break;
            default:
                break;
        }
    }


    public void logout(View view) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Supervisor_MainHomeActivity.this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.show();
    }
    //logout yes button
    public void logout_yes(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        finish();
    }

}