package com.crish.recipes.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crish.recipes.Data.MealsRecipes;
import com.crish.recipes.Listeners.ItemClickListener;
import com.crish.recipes.R;
import com.crish.recipes.ViewHolder.SearchViewHolder;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {
    private List<MealsRecipes>  mealsRecipesList;
    private ItemClickListener itemClickListener;

    public SearchAdapter(List<MealsRecipes> mealsRecipesList, ItemClickListener itemClickListener) {
        this.mealsRecipesList = mealsRecipesList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout,parent,false);
        return new SearchViewHolder(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        MealsRecipes  mealsRecipes = mealsRecipesList.get(position);
        holder.setData(mealsRecipes);

    }


    @Override
    public int getItemCount() {
        return mealsRecipesList.size();
    }

    public void updateData(List<MealsRecipes> mealsRecipesList){
        this.mealsRecipesList = mealsRecipesList;
        notifyDataSetChanged();
    }
}
