package com.crish.recipes.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.crish.recipes.Data.MealsRecipes;
import com.crish.recipes.Listeners.ItemClickListener;
import com.crish.recipes.R;

public class SearchViewHolder extends RecyclerView.ViewHolder {
    private TextView mRecName;
    private ImageView mImageRes;
    private ItemClickListener itemClickListener;
    private CardView cardView;
    public SearchViewHolder(@NonNull View itemView,ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener=itemClickListener;
        initViews(itemView);

    }

    private void initViews(View itemView) {
        mRecName = itemView.findViewById(R.id.recyName);
        mImageRes = itemView.findViewById(R.id.recyImage);
        cardView = itemView.findViewById(R.id.cardResHolder);
    }
    public void setData(MealsRecipes recipes){
        mRecName.setText(recipes.getStrMeal());
        Glide.with(mRecName.getContext()).load(recipes.getStrMealThumb()).into(mImageRes);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            itemClickListener.onItemClicked(getAdapterPosition());
            }
        });
    }
}
