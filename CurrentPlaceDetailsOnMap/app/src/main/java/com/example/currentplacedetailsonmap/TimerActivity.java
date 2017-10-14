package com.example.currentplacedetailsonmap;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class TimerActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    TextView timerTextView;
    TextView Textview;
    public void updateTimer(int secondsLeft) {

        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String secondString = Integer.toString(seconds);

        if (seconds <= 9) {

            secondString = "0" + secondString;

        }


        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerTextView = (TextView)findViewById(R.id.timerTextView);
        Textview = (TextView)findViewById(R.id.textView);

        countDownTimer = new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                updateTimer((int) millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {
                finish();
                MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                mplayer.start();
                Textview.setText("Too bad you didn't reduce your speed. Now your family is notified about it. ");
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "8039534505"));
                startActivity(intent);

            }
        }.start();
    }
    public void finish(){
        timerTextView.setText("00:00");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.timer_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.speed_reduced:
                Toast.makeText(getApplicationContext(), "Thank you for speeding down",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MapsActivityCurrentPlace.class);
                startActivity(i);
                break;
            default:
                //Do nothing

        }
        return true;
    }
}
