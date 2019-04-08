package com.example.customdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button01);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.layoutdialog, null);
        AlertDialog dlg = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher_round)
                .setTitle("请登录")
                .setView(textEntryView)
                .setPositiveButton(
                        R.string.login,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                EditText user = textEntryView.findViewById(R.id.username);
                                EditText pass = textEntryView.findViewById(R.id.password);
                                String username = user.getText().toString();
                                String password = pass.getText().toString();
                                Toast.makeText(MainActivity.this, "用户名：" + username + "密码" + password, Toast.LENGTH_SHORT).show();
                            }
                        }
                )
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                .create();
        dlg.show();
    }
}
