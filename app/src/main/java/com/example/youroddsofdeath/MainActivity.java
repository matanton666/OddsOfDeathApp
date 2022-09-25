package com.example.youroddsofdeath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    HashMap<String, Double> lifeTimeRisksMap = new HashMap<>();
    HashMap<String, Double> statistics = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //lifeTimeRisksMap = InputData.worldLifeRisk();
        lifeTimeRisksMap = InputData.getMapFromJson("lifeTimeChance", getResources());
        lifeTimeRisksMap = lifeTimeToDaily(lifeTimeRisksMap);
        statistics = InputData.getMapFromJson("world life risk", getResources());

    }

    public void switch_to_search(View v){
        Intent i = new Intent(this, Search_a_statistic.class);
        i.putExtra("hashmap", lifeTimeRisksMap);
        i.putExtra("list name", "daily risk");
        i.putExtra("printTP", "percent");
        startActivity(i);
    }

    public void switch_to_statistics(View v){
        Intent i = new Intent(this, Search_a_statistic.class);
        i.putExtra("hashmap", statistics);
        i.putExtra("list name", "Life time risks");
        i.putExtra("printTP", "one in");
        startActivity(i);
    }



    public HashMap<String, Double> turnPercentToOneIn(HashMap<String, Double> map){
        int totalDeathsDaily = 164380;
        long totalPopulation = 8000000000L;

        for (Map.Entry<String, Double> entry : map.entrySet()){
            double calc = (entry.getValue() / 100) * totalDeathsDaily;
            calc = (calc / totalPopulation) * 100;
            calc = 1/calc;
            map.put(entry.getKey(), calc);
        }
        return map;
    }

    public HashMap<String, Double> lifeTimeToDaily(HashMap<String, Double> map){
        double dailyPercentDeath =  0.00205;
        for (Map.Entry<String, Double> entry : map.entrySet()){
            double calc = entry.getValue() * dailyPercentDeath;
            map.put(entry.getKey(), calc);
        }
        return map;
    }

}
