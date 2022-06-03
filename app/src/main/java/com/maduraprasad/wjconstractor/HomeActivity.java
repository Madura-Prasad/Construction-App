package com.maduraprasad.wjconstractor;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.maduraprasad.wjconstractor.Fragment.AboutFragment;
import com.maduraprasad.wjconstractor.Fragment.HomeFragment;
import com.maduraprasad.wjconstractor.Fragment.SigninFragment;

import static maes.tech.intentanim.CustomIntent.customType;

public class HomeActivity extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;
    ChipNavigationBar chip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }


        chip = findViewById(R.id.bottom_nav_menu);
        chip.setItemSelected(R.id.fhome, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        bottomMenu();
    }

    private void bottomMenu() {
        chip.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.fhome:
                        fragment = new HomeFragment();
                        break;
                    case R.id.sign:
                        fragment = new SigninFragment();
                        break;
                    case R.id.about:
                        fragment = new AboutFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
    }


    //exit toast
    @Override
    public void onBackPressed() {


        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
            customType(HomeActivity.this, "fadein-to-fadeout");
        }

        backPressedTime = System.currentTimeMillis();

    }
}