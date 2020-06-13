package com.tolaotesanya.kindeed.helper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tolaotesanya.kindeed.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyView> {

    private List<String> list;
    private int layoutid;

    public CustomAdapter(List<String> horizontalList, int layoutid)
    {
        this.list = horizontalList;
        this.layoutid = layoutid;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(layoutid,
                        parent,
                        false);

        // return itemView
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyView extends RecyclerView.ViewHolder{

        TextView textView;

        public MyView(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.sample_text);
        }
    }



}
