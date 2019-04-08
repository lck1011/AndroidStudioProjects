package com.example.menusample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    public static final int RED_MENU_ID = Menu.FIRST;
    public static final int GREEN_MENU_ID = Menu.FIRST + 1;
    public static final int BLUE_MENU_ID = Menu.FIRST + 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.text01);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(0, RED_MENU_ID, 0, R.string.menu1);
//        menu.add(0, GREEN_MENU_ID, 0, R.string.menu2);
//        menu.add(0, BLUE_MENU_ID, 0, R.string.menu3);
//        return super.onCreateOptionsMenu(menu);
//    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, RED_MENU_ID, 0, R.string.menu1)
                .setIcon(R.mipmap.ic_launcher)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        menu.add(0, GREEN_MENU_ID, 0, R.string.menu2)
                .setIcon(R.mipmap.ic_launcher)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        menu.add(0, BLUE_MENU_ID, 0, R.string.menu3)
                .setIcon(R.mipmap.ic_launcher)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case RED_MENU_ID:
                mTextView.setBackgroundColor(Color.RED);
                mTextView.setText(R.string.menu1);
                return true;

            case GREEN_MENU_ID:
                mTextView.setBackgroundColor(Color.GREEN);
                mTextView.setText(R.string.menu2);
                return true;

            case BLUE_MENU_ID:
                mTextView.setBackgroundColor(Color.BLUE);
                mTextView.setText(R.string.menu3);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
