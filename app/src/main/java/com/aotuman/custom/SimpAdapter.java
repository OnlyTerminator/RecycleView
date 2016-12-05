package com.aotuman.custom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aotuman on 2016/12/5.
 */
public class SimpAdapter extends RecyclerView.Adapter<SimpAdapter.MyViewHolder> {
    private LayoutInflater layoutInflater = null;
    private List<String> list = new ArrayList<String>();
    public SimpAdapter(Context context,List<String> data){
        layoutInflater = LayoutInflater.from(context);
        this.list = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.simp_adapter_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
