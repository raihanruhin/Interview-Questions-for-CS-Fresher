package com.example.raihanruhin.interviewquestionsforcsfresher;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.graphics.Color.rgb;

/**
 * Created by raihanruhin on 14-Jul-16.
 */
public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder> {

    Context context;
    ArrayList<Information> data;
    LayoutInflater inflater;

    public MyCustomAdapter(Context context, ArrayList<Information> data) {
        this.context=context;
        this.data=data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = inflater.inflate(R.layout.list_item_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.textview.setText(data.get(position).title);
        myViewHolder.imageView.setImageResource(data.get(position).imageId);
        //myViewHolder.itemView.setBackgroundColor(Color.CYAN);
        //myViewHolder.itemView.setDrawingCacheBackgroundColor(Color.CYAN);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textview;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textview = (TextView) itemView.findViewById(R.id.txv_row);
            imageView = (ImageView) itemView.findViewById(R.id.img_row);
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(v.getContext(),textview.getText() , Toast.LENGTH_SHORT).show();
            Intent intentEditJob = new Intent(context,Questions.class);
            intentEditJob.putExtra("string", textview.getText());
            context.startActivity(intentEditJob);
        }
    }
}
