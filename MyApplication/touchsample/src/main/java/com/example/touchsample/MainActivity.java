package com.example.touchsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mAction;
    private TextView mPostion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAction = (TextView) findViewById(R.id.action);
        mPostion = (TextView) findViewById(R.id.postion);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                mAction.setText("手指抬起");
                break;
            case MotionEvent.ACTION_DOWN:
                mAction.setText(("手指按下"));
                break;
            case MotionEvent.ACTION_MOVE:
                mAction.setText("手指移动");
                break;
        }
        float X = event.getX();
        float Y = event.getY();
        mPostion.setText("位置 = (" + X + "," + Y + ")");
        return true;
    }
}
