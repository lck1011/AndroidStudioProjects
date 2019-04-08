package com.example.loginsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SuccessActivity extends AppCompatActivity {

    private final static String TAG = "SuccessActivity";
    private Button btnBack;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        Log.v(TAG, "进入SuccessActivity");
        Intent it = this.getIntent();
        Bundle bundle = it.getExtras();
        final String userid = bundle.getString("userid");

        textView = findViewById(R.id.textView);
        textView.setText("登陆成功,Userid:" + userid);
        btnBack = findViewById(R.id.button_Back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1,(new Intent()).putExtra("userid",userid));
                finish();
            }
        });
    }
}
