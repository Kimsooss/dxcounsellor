package com.example.myapplication.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.repModel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Aws on 28/01/2018.
 */

public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.MyViewHolder> {

    private Context mContext ;
    private List<repModel> mData ;


    public ReplyAdapter(Context mContext, List<repModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.test_item,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        GradientDrawable drawable= (GradientDrawable) mContext.getDrawable(R.drawable.background_rounding);
        holder.Icon.setBackground(drawable);
        holder.Icon.setClipToOutline(true);

        holder.title.setText(mData.get(position).getTxt());
        holder.likeIcon.setImageResource(R.drawable.images);
        holder.Icon.setImageResource(R.drawable.unnamed);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView likeIcon;
        ImageView Icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title) ;
            likeIcon = (ImageView) itemView.findViewById(R.id.likeIcon);
            Icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }


}
