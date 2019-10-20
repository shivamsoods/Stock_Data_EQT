package com.frictionhacks.eqt;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

public class MainActivity extends AppCompatActivity {

    private AHBottomNavigation nbBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nbBottom = findViewById(R.id.nv_main_bottom);
        loadFragment(new HomeFragment());

        nbBottom.setDefaultBackgroundColor(Color.parseColor("#FFFFFF"));

        nbBottom.setAccentColor(Color.parseColor("#13EDCA"));
        nbBottom.setInactiveColor(Color.parseColor("#807F7F"));
        nbBottom.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);

        AHBottomNavigationItem item1= new AHBottomNavigationItem("Home",R.drawable.ic_home);
        AHBottomNavigationItem item2= new AHBottomNavigationItem("Equity",R.drawable.ic_equity_finder);
        AHBottomNavigationItem item3= new AHBottomNavigationItem("Search",R.drawable.ic_search);
        AHBottomNavigationItem item4= new AHBottomNavigationItem("OCR",R.drawable.ic_camera);
        nbBottom.addItem(item1);
        nbBottom.addItem(item2);
        nbBottom.addItem(item3);
        nbBottom.addItem(item4);


        nbBottom.setCurrentItem(0);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.white)));


        nbBottom.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                Log.d("Tagg", "TABB "+position);
                switch (position) {
                    case 0:
                        //home
                        loadFragment(new HomeFragment());
                        break;
                    case 1:
                        //equity
                        loadFragment(new EquityFragment());
                        break;
                    case 2:
                        //search
                        loadFragment(new StockSearchFragment());
                        break;
                    case 3:
                        //ocr
                        loadFragment(new OcrFragment());
                        break;

                    default:
                        loadFragment(new HomeFragment());
                        break;
                }
                return true;
            }
        });




    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.slide_in_top,R.animator.slide_out_right);
        ft.replace(R.id.fl_main, fragment);
        ft.commit();
    }
}
