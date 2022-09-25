package com.example.youroddsofdeath;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

public class MyAddapter extends RecyclerView.Adapter<MyAddapter.MyViewHolders> {

    private static final DecimalFormat df = new DecimalFormat("0.00000000");
    String[] cause;
    Double[] odd;
    Context context;
    Double clicked = 0.0;
    String PT; // can be "percent" or "onein"
    int pos = -1;
    Integer[] colored;


    public MyAddapter(Context ct, HashMap<String, Double> hm, String printType) {
        cause = hm.keySet().toArray(new String[0]);
        odd = hm.values().toArray(new Double[0]);
        context = ct;
        PT = printType;
        colored = new Integer[cause.length];
        Arrays.fill(colored, Color.WHITE);


    }

    @NonNull
    @Override
    public MyViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAddapter.MyViewHolders holder, int position) {


        holder.mytext1.setText(cause[position]);
        holder.mytext2.setText(df.format(odd[position]) + "%");


        holder.mylayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (PT.equals("percent")) {

                    pos = position;
                    Resources res = holder.itemView.getContext().getResources();
                    //holder.mylayout.setBackgroundColor(res.getColor(R.color.black));
                    if (colored[position] == Color.WHITE) {
                        holder.mylayout.setBackgroundColor(res.getColor(R.color.green));
                        colored[position] = Color.GREEN;
                        clicked += odd[position];
                    } else {
                        holder.mylayout.setBackgroundColor(res.getColor(R.color.white));
                        colored[position] = Color.WHITE;
                        clicked -= odd[position];
                    }

                    Toast.makeText(context, cause[position] + " : " + df.format(odd[position]) + " %", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (PT.equals("percent")) {
            if (colored[position] == Color.GREEN) {
                holder.mylayout.setBackgroundColor(context.getResources().getColor(R.color.green));
            } else {
                holder.mylayout.setBackgroundColor(context.getResources().getColor(R.color.white));
            }
        }
    }

    @Override
    public int getItemCount() {
        return cause.length;
    }

    public class MyViewHolders extends RecyclerView.ViewHolder {

        TextView mytext1, mytext2;
        ConstraintLayout mylayout;

        public MyViewHolders(@NonNull View itemView) {
            super(itemView);
            mytext1 = itemView.findViewById(R.id.day);
            mytext2 = itemView.findViewById(R.id.desc);
            mylayout = itemView.findViewById(R.id.mainLayout2);
        }
    }
}
