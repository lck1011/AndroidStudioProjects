package com.example.lck.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//活动作为事件监听器
/*
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnOK = (Button) findViewById(R.id.button);
        btnOK.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        TextView text = (TextView) findViewById(R.id.textView);
        text.setText("HelloWorld");
    }
}*/

//内部类事件监听器
/*public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnOK = (Button) findViewById(R.id.button);
        btnOK.setOnClickListener(new ButtonOKOnClickListener());
    }

    class ButtonOKOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TextView text = (TextView) findViewById(R.id.textView);
            text.setText("HelloWorld");
        }
    }
}
*/
//匿名内部类事件监听器
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnOK = (Button) findViewById(R.id.button);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = (TextView) findViewById(R.id.textView);
                text.setText("HelloWorld");
            }
        });
    }
}