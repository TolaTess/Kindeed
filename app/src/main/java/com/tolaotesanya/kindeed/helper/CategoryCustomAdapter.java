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
import com.tolaotesanya.kindeed.activities.ServiceActivity;
import com.tolaotesanya.kindeed.coordinator.IntentPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryCustomAdapter extends RecyclerView.Adapter<CategoryCustomAdapter.MyView> {

    private IntentPresenter intentPresenter;
    private Context context;
    private List<String> mCategory;
    private Activity activity;

    public CategoryCustomAdapter(IntentPresenter intentPresenter, Context context) {
        this.intentPresenter = intentPresenter;
        this.context = context;
        activity = (Activity) context;
        mCategory = new ArrayList<>();
        setmCategoryProduct();
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_service,
                        parent,
                        false);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        if (mCategory != null) {
            String current = mCategory.get(position);
            holder.itemNameView.setText(current);
            holder.itemImageView.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            //intentPresenter.presentIntent(context, ActivityClassName.service, category);
                                                            Intent intent = new Intent(context, ServiceActivity.class);
                                                            intent.putExtra("category", current);
                                                            context.startActivity(intent);
                                                            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                                        }
                                                    }
            );
        } else {
            holder.itemNameView.setText("No Products");
        }
    }

    public void setmCategoryProduct() {
        List<String> mFinalProductList = new ArrayList<>();
        mFinalProductList.add("Organic");
        mFinalProductList.add("Non-Organic");
        mFinalProductList.add("Donate");
        mCategory = mFinalProductList;
    }

    @Override
    public int getItemCount() {
        if (mCategory != null) {
            return mCategory.size();
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
