package com.example.checkboxsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    CheckBox mCheckbox01, mCheckbox02, mCheckbox03, mCheckbox04;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.TextView01);
        mCheckbox01 = findViewById(R.id.CheckBox01);
        mCheckbox01.setOnCheckedChangeListener(this);

        mCheckbox02 = findViewById(R.id.CheckBox02);
        mCheckbox02.setOnCheckedChangeListener(this);

        mCheckbox03 = findViewById(R.id.CheckBox03);
        mCheckbox03.setOnCheckedChangeListener(this);

        mCheckbox04 = findViewById(R.id.CheckBox04);
        mCheckbox04.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        CheckBox ckb = (CheckBox) buttonView;
        mTextView.setText(ckb.getText());
    }
}
