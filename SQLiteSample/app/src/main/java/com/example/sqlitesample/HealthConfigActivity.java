package com.example.sqlitesample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class HealthConfigActivity extends Activity {
    private RadioGroup rdgDateFormat;
    private RadioButton rdDateFormat1, rdDateFormat2;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        mSharedPreferences = getSharedPreferences(SysConst.PREFS_CONF, MODE_PRIVATE);

        rdgDateFormat = findViewById(R.id.rdgDateFormat);
        rdDateFormat1 = findViewById(R.id.rdDateFormat1);
        rdDateFormat2 = findViewById(R.id.rdDateFormat2);

        String dateConf = mSharedPreferences.getString(SysConst.DATE_KEY, "YYYY-MM-DD");
        setDateFormat(dateConf);

        rdgDateFormat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                if (checkedId == rdDateFormat1.getId()) {
                    editor.putString(SysConst.DATE_KEY, "YYYY-MM-DD");
                    setDateFormat("YYYY-MM-DD");
                } else {
                    editor.putString(SysConst.DATE_KEY, "YYYY/MM/DD");
                    setDateFormat("YYYY/MM/DD");
                }
                editor.commit();
            }
        });
    }

    private void setDateFormat(String dateFormat) {
        if ("YYYY-MM-DD".equals(dateFormat)) {
            rdDateFormat1.setChecked(true);
            rdDateFormat2.setChecked(false);
        } else {
            rdDateFormat1.setChecked(false);
            rdDateFormat2.setChecked(true);
        }
    }
}
