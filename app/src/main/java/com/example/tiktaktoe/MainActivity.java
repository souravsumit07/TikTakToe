package com.example.tiktaktoe;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button b[] = new Button[9];
    String bu[] = new String[9];
    int flag = 0, count = 0;

    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();

    private void initializeMediaPlayer() {
        mediaPlayer = MediaPlayer.create(this, R.raw.winner); // Replace with your sound file name
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                newgame();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getid();
        initializeMediaPlayer();
    }

    void getid() {
        b[0] = findViewById(R.id.button1);
        b[1] = findViewById(R.id.button2);
        b[2] = findViewById(R.id.button3);
        b[3] = findViewById(R.id.button4);
        b[4] = findViewById(R.id.button5);
        b[5] = findViewById(R.id.button6);
        b[6] = findViewById(R.id.button7);
        b[7] = findViewById(R.id.button8);
        b[8] = findViewById(R.id.button9);
        for (int i = 0; i < b.length; i++) {
            b[i].setBackgroundColor(getResources().getColor(R.color.buttonBackground));
            b[i].setTextColor(getResources().getColor(R.color.buttonText));
        }
    }

    public void click(View v) {
        Button currentButton = (Button) v;
        if (currentButton.getText().toString().equals("")) {
            count++;
            if (flag == 0) {
                currentButton.setText("X");
                flag = 1;
            } else {
                currentButton.setText("O");
                flag = 0;
            }

            if (count > 4) {
                bu[0] = b[0].getText().toString();
                bu[1] = b[1].getText().toString();
                bu[2] = b[2].getText().toString();
                bu[3] = b[3].getText().toString();
                bu[4] = b[4].getText().toString();
                bu[5] = b[5].getText().toString();
                bu[6] = b[6].getText().toString();
                bu[7] = b[7].getText().toString();
                bu[8] = b[8].getText().toString();

                if (bu[0].equals(bu[1]) && bu[1].equals(bu[2]) && !bu[0].equals("")) {
                    Toast.makeText(this, "The winner is " + bu[0], Toast.LENGTH_SHORT).show();
                    playWinningSound();
                } else if (bu[3].equals(bu[4]) && bu[4].equals(bu[5]) && !bu[3].equals("")) {
                    Toast.makeText(this, "The winner is " + bu[3], Toast.LENGTH_SHORT).show();
                    playWinningSound();
                } else if (bu[6].equals(bu[7]) && bu[7].equals(bu[8]) && !bu[6].equals("")) {
                    Toast.makeText(this, "The winner is " + bu[6], Toast.LENGTH_SHORT).show();
                    playWinningSound();
                } else if (bu[0].equals(bu[3]) && bu[3].equals(bu[6]) && !bu[0].equals("")) {
                    Toast.makeText(this, "The winner is " + bu[0], Toast.LENGTH_SHORT).show();
                    playWinningSound();
                } else if (bu[1].equals(bu[4]) && bu[4].equals(bu[7]) && !bu[1].equals("")) {
                    Toast.makeText(this, "The winner is " + bu[1], Toast.LENGTH_SHORT).show();
                    playWinningSound();
                } else if (bu[2].equals(bu[5]) && bu[5].equals(bu[8]) && !bu[2].equals("")) {
                    Toast.makeText(this, "The winner is " + bu[2], Toast.LENGTH_SHORT).show();
                    playWinningSound();
                } else if (bu[0].equals(bu[4]) && bu[4].equals(bu[8]) && !bu[0].equals("")) {
                    Toast.makeText(this, "The winner is " + bu[0], Toast.LENGTH_SHORT).show();
                    playWinningSound();
                } else if (bu[2].equals(bu[4]) && bu[4].equals(bu[6]) && !bu[2].equals("")) {
                    Toast.makeText(this, "The winner is " + bu[2], Toast.LENGTH_SHORT).show();
                    playWinningSound();
                } else if (count == 9) {
                    Toast.makeText(this, getString(R.string.it_s_a_tie), Toast.LENGTH_SHORT).show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            newgame();
                        }
                    }, 2000);
                }
            }
        }
    }

    private void playWinningSound() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.reset();
            }
            mediaPlayer.start();
        }
    }

    public void newgame() {
        for (int i = 0; i < b.length; i++) {
            b[i].setText("");
        }
        count = 0;
        flag = 0;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            initializeMediaPlayer(); // Reinitialize MediaPlayer after resetting
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
