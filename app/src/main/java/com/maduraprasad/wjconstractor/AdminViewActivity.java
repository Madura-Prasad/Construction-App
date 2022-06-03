package com.maduraprasad.wjconstractor;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import static maes.tech.intentanim.CustomIntent.customType;

public class AdminViewActivity extends AppCompatActivity {
    CardView masonry, supervisor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);
        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        masonry=findViewById(R.id.masonry);
        supervisor=findViewById(R.id.supervisor);

        supervisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminViewActivity.this, AdminView_SupervisorActivity.class);
                startActivity(intent);
                customType(AdminViewActivity.this, "fadein-to-fadeout");
            }
        });

        masonry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminViewActivity.this, AdminView_MasonryActivity.class);
                startActivity(intent);
                customType(AdminViewActivity.this, "fadein-to-fadeout");
            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        customType(AdminViewActivity.this, "fadein-to-fadeout");
    }
}