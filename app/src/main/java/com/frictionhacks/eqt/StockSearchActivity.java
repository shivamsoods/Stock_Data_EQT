package com.frictionhacks.eqt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ramotion.fluidslider.FluidSlider;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class StockSearchActivity extends AppCompatActivity {

    private Button btnSearchSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_search);

        btnSearchSubmit=findViewById(R.id.btn_search_submit);
        final String min = "Short time";
        final String max = "Long time";


        final FluidSlider slider = findViewById(R.id.fs_search);
        slider.setBeginTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                //textView.setVisibility(View.INVISIBLE);
                return Unit.INSTANCE;
            }
        });

        slider.setEndTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                //textView.setVisibility(View.VISIBLE);
                return Unit.INSTANCE;
            }
        });


        slider.setPosition(0.3f);
        slider.setStartText(min);
        slider.setEndText(max);

        float pos=slider.getPosition();
        Log.d("TAAG", "value set is "+pos );

        btnSearchSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float pos=slider.getPosition();
                pos=pos*100;
                Log.d("TAAG", "value set is "+pos );
            }
        });
    }
}
