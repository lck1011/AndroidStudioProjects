package com.example.progressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar progressHorizontal01 = findViewById(R.id.progress_horizontal01);
        LinearLayout buttonbar1 = findViewById(R.id.button_bar1);
        Button bar1IncreaseButton = buttonbar1.findViewById(R.id.increase);
        bar1IncreaseButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressHorizontal01.incrementProgressBy(1);
            }
        });
        Button bar1DecreaseButton = buttonbar1.findViewById(R.id.decrease);
        bar1DecreaseButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressHorizontal01.incrementProgressBy(-1);
            }
        });

        final ProgressBar progressHorizontal02 = findViewById(R.id.progress_horizontal02);
        LinearLayout buttonbar2 = findViewById(R.id.button_bar2);
        Button bar2IncreaseButton = buttonbar2.findViewById(R.id.increase);
        bar2IncreaseButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                progressHorizontal02.incrementProgressBy(1);
            }
        });
        Button bar2DecreaseButton = buttonbar2.findViewById(R.id.decrease);
        bar2DecreaseButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                progressHorizontal02.incrementProgressBy(-1);
            }
        });

        LinearLayout buttonBar3 = findViewById(R.id.button_bar3);
        Button bar3IncreaseButton = buttonBar3.findViewById(R.id.increase);
        bar3IncreaseButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                progressHorizontal02.incrementSecondaryProgressBy(1);
            }
        });
        Button bar3DecreaseButton = buttonBar3.findViewById(R.id.decrease);
        bar3DecreaseButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                progressHorizontal02.incrementSecondaryProgressBy(-1);
            }
        });
    }
}
