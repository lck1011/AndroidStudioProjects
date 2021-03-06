package com.example.sqlitesample;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;


public class HealthAddActivity extends AppCompatActivity {
    private EditText txtInput;
    private EditText txtOutput;
    private EditText txtWeight;
    private EditText txtAmountExercise;
    private Button btnOk;
    private Button btnCancel;

    private DBHelper mDBHelper;

    public void onCreate(Bundle savedIntstanceState) {
        super.onCreate(savedIntstanceState);
        setContentView(R.layout.add_mod);

        mDBHelper = new DBHelper(this);
        txtInput = findViewById(R.id.txtinput);
        txtOutput = findViewById(R.id.txtoutput);
        txtWeight = findViewById(R.id.txtweight);
        txtAmountExercise = findViewById(R.id.txtamountExercise);

        btnOk = findViewById(R.id.btnok);
        btnCancel = findViewById(R.id.btncancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                SimpleDateFormat df = new SimpleDateFormat(SysConst.DATE_FORMATE);

                SQLiteDatabase db = mDBHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(SysConst.TABLE_FIELD_DATE, df.format(date));
                values.put(SysConst.TABLE_FIELD_INPUT, txtInput.getText().toString());
                values.put(SysConst.TABLE_FIELD_OUTPUT, txtOutput.getText().toString());
                values.put(SysConst.TABLE_FIELD_WEIGHT, txtWeight.getText().toString());
                values.put(SysConst.TABLE_FIELD_AMOUNTEXERCISE, txtAmountExercise.getText().toString());

                long rowId = db.insert(SysConst.TABLE_NAME, null, values);

                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}