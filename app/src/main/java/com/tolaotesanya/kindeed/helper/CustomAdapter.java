package com.tolaotesanya.kindeed.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tolaotesanya.kindeed.R;
import com.tolaotesanya.kindeed.coordinator.IntentPresenter;
import com.tolaotesanya.kindeed.modellayer.enums.ActivityClassName;
import com.tolaotesanya.kindeed.modellayer.model.Product;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyView> {

    //private List<String> list;
    private int layoutid;
    private IntentPresenter intentPresenter;
    private Context context;
    private List<Product> mProduct;

    public CustomAdapter(int layoutid, IntentPresenter intentPresenter, Context context, List<String> horizontalList)
    {
        //this.list = horizontalList;
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
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        if(mProduct != null){
        //final String itemName = list.get(position);
            Product current = mProduct.get(position);
        holder.textView.setText(current.getItemName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(layoutid == R.layout.recycler_service) {
                    intentPresenter.presentIntent(context, ActivityClassName.service, current.getItemName());
                } else{
                    intentPresenter.presentIntent(context, ActivityClassName.item, current.getItemName());
                }
            }
        });} else {
            holder.textView.setText("No Products");
        }
    }

    public void setmProduct(List<Product> products){
        mProduct = products;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if(mProduct != null){
            return mProduct.size();
        } else return 0;
    }


    class MyView extends RecyclerView.ViewHolder{

        TextView textView;

        MyView(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.sample_text);
        }
    }


}
