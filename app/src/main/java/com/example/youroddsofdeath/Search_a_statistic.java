package com.example.youroddsofdeath;

import static com.example.youroddsofdeath.MainActivity.sortByValue;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class Search_a_statistic extends AppCompatActivity {

    RecyclerView reclVew;
    HashMap<String, Double> statisticsList = new HashMap<>();
    String listName;
    MyAddapter adapter;
    String printTP = "";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_astatistic);
        // get data from intent
        Intent i = getIntent();
        statisticsList = (HashMap<String, Double>) i.getSerializableExtra("hashmap");
        statisticsList = sortByValue(statisticsList);

        listName = i.getStringExtra("list name");
        printTP = i.getStringExtra("printTP");

        if (!printTP.equals("percent")){
            findViewById(R.id.goToShow).setVisibility(View.GONE);
        }

        ((TextView)findViewById(R.id.list_desc)).setText(listName); // set list name


        reclVew = findViewById(R.id.activites_menu);
        // set addapter for layout
        adapter = new MyAddapter(this, statisticsList, printTP);
        reclVew.setAdapter(adapter);
        reclVew.setLayoutManager(new LinearLayoutManager(this));
        initSearchWidgit();
    }


    private void initSearchWidgit(){
        SearchView searchView = (SearchView) findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) { // change text on search
                HashMap<String, Double> filterdList = new HashMap<>();

                for(Map.Entry<String, Double> entry : statisticsList.entrySet()){// find matching items
                    if(entry.getKey().toLowerCase().contains(newText.toLowerCase())){
                        filterdList.put(entry.getKey(), entry.getValue());
                    }
                }

                filterdList = sortByValue(filterdList);
                MyAddapter addapter = new MyAddapter(getApplicationContext(), filterdList, printTP); // update view
                reclVew.setAdapter(addapter);
                reclVew.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                return false;
            }
        });

    }

    public void switchToShow(View v){
        Intent i = new Intent(this, odds_show.class);
        i.putExtra("clicked", adapter.clicked.toString());
        startActivity(i);
    }
}