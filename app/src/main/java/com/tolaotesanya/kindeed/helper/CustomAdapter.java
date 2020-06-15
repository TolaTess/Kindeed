package com.tolaotesanya.kindeed.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tolaotesanya.kindeed.R;
import com.tolaotesanya.kindeed.coordinator.IntentPresenter;
import com.tolaotesanya.kindeed.modellayer.enums.ActivityClassName;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyView> {

    private List<String> list;
    private int layoutid;
    private IntentPresenter intentPresenter;
    private Context context;

    public CustomAdapter(List<String> horizontalList, int layoutid, IntentPresenter intentPresenter, Context context)
    {
        this.list = horizontalList;
        this.layoutid = layoutid;
        this.intentPresenter = intentPresenter;
        this.context = context;
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
        final String itemName = list.get(position);
        holder.textView.setText(itemName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(layoutid == R.layout.recycler_service) {
                    intentPresenter.presentIntent(context, ActivityClassName.service, itemName);
                } else{
                    intentPresenter.presentIntent(context, ActivityClassName.item, itemName);
                }
            }
        });
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
