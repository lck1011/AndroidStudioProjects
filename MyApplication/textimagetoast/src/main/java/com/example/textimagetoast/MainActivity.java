package com.example.textimagetoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView view = new ImageView(MainActivity.this);
                view.setImageResource(R.mipmap.ic_launcher_round);

                TextView textview = new TextView(MainActivity.this);
                textview.setText(R.string.text);

                LinearLayout layout = new LinearLayout(MainActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(view);
                layout.addView(textview);

                Toast toast = new Toast(MainActivity.this);
                toast.setView(layout);
                toast.show();
            }
        });
    }
}
