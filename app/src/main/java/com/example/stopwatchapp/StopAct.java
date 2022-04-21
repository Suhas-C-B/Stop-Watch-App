package com.example.stopwatchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class StopAct extends AppCompatActivity {

    Button btnstart, btnstop, btnreset;
    ImageView icanchor;
    Animation roundingalone;
    Chronometer timeHere;
    private boolean running;
    private long pauseOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);

        btnstart = findViewById(R.id.btnstart);
        btnstop = findViewById(R.id.btnstop);
        btnreset = findViewById(R.id.btnreset);
        icanchor= findViewById(R.id.icanchor);
        timeHere = findViewById(R.id.timerHere);

        btnstop.setVisibility(View.INVISIBLE);
        btnreset.setVisibility(View.INVISIBLE);

        roundingalone = AnimationUtils.loadAnimation(this, R.anim.roundingalone);

        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MMedium.ttf");

        btnstart.setTypeface(MMedium);
        btnstop.setTypeface(MMedium);
        btnreset.setTypeface(MMedium);



        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icanchor.startAnimation(roundingalone);
                btnstop.setVisibility(View.VISIBLE);
                btnstart.setVisibility(View.INVISIBLE);
                btnreset.setVisibility(View.INVISIBLE);

                timeHere.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                timeHere.start();

            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icanchor.clearAnimation();
                btnstop.setVisibility(View.INVISIBLE);
                btnstart.setVisibility(View.VISIBLE);
                btnreset.setVisibility(View.VISIBLE);

                timeHere.stop();
                pauseOffset = SystemClock.elapsedRealtime() - timeHere.getBase();



            }


        });

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeHere.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
                btnreset.setVisibility(View.INVISIBLE);
            }
        });




        }
    }
