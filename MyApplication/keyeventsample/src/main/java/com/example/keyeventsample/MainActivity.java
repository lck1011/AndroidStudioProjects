package com.example.keyeventsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView mImage;
    private TextView mAlphavalueText;
    private int mAlphavalue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImage = (ImageView) findViewById(R.id.image);
        mAlphavalueText = (TextView) findViewById(R.id.alphavalue);
        mAlphavalue = 100;
        mImage.setImageAlpha(mAlphavalue);
        mAlphavalueText.setText("Alpha = " + mAlphavalue * 100 / 255 + "%");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                mAlphavalue += 17;
                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                mAlphavalue -= 17;
                break;
        }
        if (mAlphavalue >= 255) {
            mAlphavalue = 255;
        }
        if (mAlphavalue <= 0) {
            mAlphavalue = 0;
        }
        mImage.setImageAlpha(mAlphavalue);
        mAlphavalueText.setText("Alpha = " + mAlphavalue * 100 / 255 + "%");
        return super.onKeyDown(keyCode, event);
    }
}
