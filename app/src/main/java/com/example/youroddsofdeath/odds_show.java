package com.example.youroddsofdeath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class odds_show extends AppCompatActivity {
    private static final DecimalFormat df = new DecimalFormat("0.00000000");

    Double clicked = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Double normalRate = 0.00205;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odds_show);

        Intent i = getIntent();
        String s = i.getStringExtra("clicked");
        clicked = Double.parseDouble(s) + normalRate;
        ((TextView)findViewById(R.id.the_odds)).setText("YOUR ODDS OF DYING TODAY ARE:\n" + df.format(clicked) + " %");
        ((TextView)findViewById(R.id.the_odds3)).setText("YOU ADDED :\n" + df.format(clicked - normalRate) + " %\nTO YOUR NORMAL ODDS OF DYING TODAY");
    }
}