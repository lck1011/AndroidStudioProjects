package com.example.myaudioplayer;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ImageButton play;
    private ImageButton stop;
    private MediaPlayer mediaPlayer;

    private static final int PLAYING = 0;
    private static final int PAUSE = 1;
    private static final int STOP = 2;
    private static final int IDLE = 3;
    private int state = IDLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = findViewById(R.id.play);
        play.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (state == PLAYING) {
                    pause();
                } else {
                    start();
                }
            }
        });
        stop = findViewById(R.id.stop);
        stop.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                stop();
            }
        });
    }

    private void pause() {
        mediaPlayer.pause();
        state = PAUSE;
        play.setImageResource(R.drawable.play);
    }

    private void start() {
        if (state == IDLE || state == STOP) {
            play();
        } else if (state == PAUSE) {
            mediaPlayer.start();
            state = PLAYING;
        }
        play.setImageResource(R.drawable.pause);
    }

    private void stop() {
        mediaPlayer.stop();
        state = STOP;
        play.setImageResource(R.drawable.play);
    }

    private void play() {
        File sdCardDir = Environment.getExternalStorageDirectory();
        String path = sdCardDir + "/qqmusic/song/杀破狼.mp3";
        Log.i("path", path);
        try {
            if (mediaPlayer == null || state == STOP) {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setOnPreparedListener(listener);
            } else {
                mediaPlayer.reset();
            }
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MediaPlayer.OnPreparedListener listener = new MediaPlayer.OnPreparedListener() {
        public void onPrepared(MediaPlayer mediaPlayer) {
            mediaPlayer.start();
            state = PLAYING;
        }
    };

}
