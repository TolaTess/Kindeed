package com.tolaotesanya.kindeed.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tolaotesanya.kindeed.R;
import com.tolaotesanya.kindeed.activities.ItemActivity;
import com.tolaotesanya.kindeed.coordinator.IntentPresenter;
import com.tolaotesanya.kindeed.modellayer.model.Product;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ServiceCustomAdapter extends RecyclerView.Adapter<ServiceCustomAdapter.MyView> {

    private IntentPresenter intentPresenter;
    private Context context;
    private List<Product> mProduct;
    private Activity activity;
    private String category;

    public ServiceCustomAdapter(String category, IntentPresenter intentPresenter, Context context) {
        this.category = category;
        this.intentPresenter = intentPresenter;
        this.context = context;
        activity = (Activity) context;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_items,
                        parent,
                        false);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        if (mProduct != null) {
            Product current = mProduct.get(position);
            String itemName = current.getItemName();
            double price = current.getPrice();
            String newPrice = String.valueOf(price);
            String description = current.getDescription();
            holder.itemNameView.setText(itemName);
            holder.itemPriceView.setText(newPrice + " EUR");
            holder.itemDescView.setText(description);

            holder.itemImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, ItemActivity.class);
                    intent.putExtra("itemName", itemName);
                    intent.putExtra("desc", description);
                    intent.putExtra("price", newPrice);
                    context.startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });
        } else {
            holder.itemNameView.setText("No product");
        }
    }

    public void setmProduct(List<Product> products) {
        mProduct = products;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (mProduct != null) {
            return mProduct.size();
        } else return 0;
    }


    class MyView extends RecyclerView.ViewHolder {

        TextView itemNameView;
        TextView itemDescView;
        TextView itemPriceView;
        ImageView itemImageView;

        MyView(@NonNull View itemView) {
            super(itemView);
            itemNameView = itemView.findViewById(R.id.sample_text);
            itemDescView = itemView.findViewById(R.id.sample_desc);
            itemPriceView = itemView.findViewById(R.id.sample_price);
            itemImageView = itemView.findViewById(R.id.image_item);
        }
    }


}
