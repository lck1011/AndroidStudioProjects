package com.example.xuanzhuan;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(this.getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.portrait);
        }
        else{
            setContentView(R.layout.landscapet);
        }
    }
}
