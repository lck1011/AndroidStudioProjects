package com.example.loginsample;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private final static String TAG = "LoginActivity";
    private EditText txtUserid;
    private EditText txtPwd;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.v(TAG, "进入LoginActivity");
        btnLogin = findViewById(R.id.button_login);
        txtUserid = findViewById(R.id.editText01);
        txtPwd = findViewById(R.id.editText02);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtUserid.getText().toString().equals("tony")&&txtPwd.getText().toString().equals("123")){
                    Intent it = new Intent(LoginActivity.this,SuccessActivity.class);
                    it.putExtra("userid",txtUserid.getText().toString());
                    startActivityForResult(it,1);
                }else{
                    Intent it = new Intent(LoginActivity.this,FailureActivity.class);
                    startActivityForResult(it,2);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (resultCode){
            case 1:
                Log.v(TAG,"从成功活动返回.resultCode="+resultCode);
                break;
            case 2:
                Log.v(TAG,"从失败活动返回.resultCode="+resultCode);
        }
        super.onActivityResult(requestCode,resultCode,data);
    }
}
