package com.example.emotions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myOwnAdapter extends RecyclerView.Adapter<myOwnAdapter.myOwnHolder>{

    String data1[],data2[];
    int img[];
    Context ctx;

    public myOwnAdapter(Context ct, String s1[], String s2[], int i1[]){
        ctx = ct;
        data1 = s1;
        data2 = s2;
        img = i1;
    }
    @NonNull
    @Override
    public myOwnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflator = LayoutInflater.from(ctx);
        View myOwnVinew = myInflator.inflate(R.layout.my_row,parent,false);
        return new myOwnHolder(myOwnVinew);
    }

    @Override
    public void onBindViewHolder(@NonNull myOwnHolder holder, int position) {
        holder.t1.setText(data1[position]);
        holder.t2.setText(data2[position]);
        holder.myImage.setImageResource(img[position]);
    }

    @Override
    public int getItemCount() {

        return img.length;
    }

    public class myOwnHolder extends RecyclerView.ViewHolder{
        TextView t1,t2;
        ImageView myImage;
        public myOwnHolder(@NonNull View itemView) {
            super(itemView);
            t1 = (TextView)itemView.findViewById(R.id.t1);
            t2 = (TextView)itemView.findViewById(R.id.t2);
            myImage = (ImageView)itemView.findViewById(R.id.myImage);
        }
    }
}
