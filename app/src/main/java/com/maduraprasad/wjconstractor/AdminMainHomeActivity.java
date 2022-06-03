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

public class AdminMainHomeActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView addCard, supervisorCard, masonryCard, deleteCard, viewCard;
    private TextView admin_user;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "my_pref";
    private static final String KEY_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_home);
        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //initialize variable
        addCard = findViewById(R.id.addsite);
        supervisorCard = findViewById(R.id.supervisor);
        masonryCard = findViewById(R.id.masonry);
        deleteCard = findViewById(R.id.delete);
        viewCard = findViewById(R.id.view);
        admin_user = findViewById(R.id.admin_username);


        addCard.setOnClickListener(this);
        supervisorCard.setOnClickListener(this);
        masonryCard.setOnClickListener(this);
        deleteCard.setOnClickListener(this);
        viewCard.setOnClickListener(this);


        //share_preference
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        if (name != null) {
            admin_user.setText(name + "!");
        }
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.addsite:
                i = new Intent(AdminMainHomeActivity.this, AdminAddSiteActivity.class);
                startActivity(i);
                customType(AdminMainHomeActivity.this, "fadein-to-fadeout");
                break;

            case R.id.supervisor:
                i = new Intent(AdminMainHomeActivity.this, AdminSupervisorActivity.class);
                startActivity(i);
                customType(AdminMainHomeActivity.this, "fadein-to-fadeout");
                break;
            case R.id.masonry:
                i = new Intent(AdminMainHomeActivity.this, AdminMasonryActivity.class);
                startActivity(i);
                customType(AdminMainHomeActivity.this, "fadein-to-fadeout");
                break;
            case R.id.delete:
                i = new Intent(AdminMainHomeActivity.this, AdminDeleteActivity.class);
                startActivity(i);
                customType(AdminMainHomeActivity.this, "fadein-to-fadeout");
                break;
            case R.id.view:
                i = new Intent(AdminMainHomeActivity.this, AdminViewActivity.class);
                startActivity(i);
                customType(AdminMainHomeActivity.this, "fadein-to-fadeout");
                break;
            default:
                break;
        }

    }


    //logout button
    public void logout(View view) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(AdminMainHomeActivity.this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.show();
    }

    public void logout_yes(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        finish();
    }

}