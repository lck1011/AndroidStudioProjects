package com.example.sqlitesample;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HealthModActivity extends AppCompatActivity {

    private EditText txtInput;
    private EditText txtOutput;
    private EditText txtWeight;
    private EditText txtAmountExercise;
    private Button btnOk;
    private Button btnCancel;
    private DBHelper mDBHelper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_mod);

        mDBHelper = new DBHelper(this);

        txtInput = (EditText) findViewById(R.id.txtinput);
        txtOutput = (EditText) findViewById(R.id.txtoutput);
        txtWeight = (EditText) findViewById(R.id.txtweight);
        txtAmountExercise = (EditText) findViewById(R.id.txtamountExercise);


        Bundle bundle = this.getIntent().getExtras();
        final String selectId = bundle.getString(SysConst.TABLE_FIELD_DATE);

        String intput = bundle.getString(SysConst.TABLE_FIELD_INPUT);
        txtInput.setText(intput);

        String weight = bundle.getString(SysConst.TABLE_FIELD_WEIGHT);
        txtWeight.setText(weight);

        String output = bundle.getString(SysConst.TABLE_FIELD_OUTPUT);
        txtOutput.setText(output);

        String amountExercise = bundle.getString(SysConst.TABLE_FIELD_AMOUNTEXERCISE);
        txtAmountExercise.setText(amountExercise);

        btnOk = (Button) findViewById(R.id.btnok);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = mDBHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(SysConst.TABLE_FIELD_INPUT, txtInput.getText().toString());
                values.put(SysConst.TABLE_FIELD_OUTPUT, txtOutput
                        .getText().toString());
                values.put(SysConst.TABLE_FIELD_WEIGHT, txtWeight.getText().toString());
                values.put(SysConst.TABLE_FIELD_AMOUNTEXERCISE, txtAmountExercise.getText().toString());

                String whereClause = SysConst.TABLE_FIELD_DATE + " = ?";
                long rowId = db.update(SysConst.TABLE_NAME, values, whereClause, new String[]{selectId});

                finish();
            }
        });

        btnCancel = (Button) findViewById(R.id.btncancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}