package com.example.seekbarsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    TextView mProgressText;
    static String TAG="SeekBar";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressText=findViewById(R.id.progress);
        SeekBar seekBar1=findViewById(R.id.seekBar1);
        SeekBar seekBar2=findViewById(R.id.seekBar2);
        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mProgressText.setText("当前进度："+progress+"%");
        Log.i(TAG,"当前进度："+progress+"%");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        Log.i(TAG,"开始拖动");
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Log.i(TAG,"停止拖动");
    }
}
