package com.adsapp.adstool.MoreApps;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.adsapp.adstool.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<MoreAppModel> android;
    private Context context;

    public DataAdapter(ArrayList<MoreAppModel> android , Context context) {
        this.android = android;
        this.context = context;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(android.get(position).getLogo()).into(holder.icons);

        holder.tv_name.setText(android.get(position).getName());
        holder.appclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String DATA = "https://play.google.com/store/apps/details?id="+android.get(position).getPackage();
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(DATA)));

            }
        });
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView icons;
        private TextView tv_name;
        private LinearLayout appclick;

        public ViewHolder(View itemView) {
            super(itemView);

            icons = (ImageView)itemView.findViewById(R.id.icons);
            tv_name = (TextView)itemView.findViewById(R.id.tv_name);
            appclick = (LinearLayout)itemView.findViewById(R.id.appclick);

        }
    }
}