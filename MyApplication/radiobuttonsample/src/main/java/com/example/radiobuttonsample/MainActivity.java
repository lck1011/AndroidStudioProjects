package com.example.radiobuttonsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    RadioGroup mRadioGroup1;
    RadioGroup mRadioGroup2;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.TextView01);
        mRadioGroup1 = findViewById(R.id.RadioGroup1);
        mRadioGroup1.setOnCheckedChangeListener(this);

        mRadioGroup2 = findViewById(R.id.RadioGroup2);
        mRadioGroup2.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup rdp, int checkID) {
        switch (rdp.getId()) {
            case R.id.RadioGroup1:
                RadioButton rb1 = findViewById(checkID);
                mTextView.setText(rb1.getText());
                break;
            case R.id.RadioGroup2:
                RadioButton rb2 = findViewById(checkID);
                mTextView.setText(rb2.getText());
                break;
        }
    }
}
