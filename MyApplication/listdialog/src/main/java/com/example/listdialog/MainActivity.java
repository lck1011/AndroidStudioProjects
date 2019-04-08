package com.example.listdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.selectdialog)
                .setItems(R.array.Radio_dialog_items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] items = getResources().getStringArray(R.array.Radio_dialog_items);
                        Toast.makeText(MainActivity.this,"你选择的位置是"+which+","+"你选择的洲是"+items[which],Toast.LENGTH_LONG).show();
                    }
                })
                .create();
        dialog.show();
    }
}
