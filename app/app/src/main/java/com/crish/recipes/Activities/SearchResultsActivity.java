package com.crish.recipes.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crish.recipes.Adapter.SearchAdapter;
import com.crish.recipes.Data.MealsRecipes;
import com.crish.recipes.Data.ResponseRecipes;
import com.crish.recipes.Listeners.ItemClickListener;
import com.crish.recipes.Network.ApiClient;
import com.crish.recipes.Network.Network;
import com.crish.recipes.R;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultsActivity extends AppCompatActivity implements ItemClickListener {
    private TextView mTitle;
    private RecyclerView recyclerView;
    private List<MealsRecipes> mealsRecipesList = new ArrayList<>();
    private SearchAdapter searchAdapter;
    private String str;
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        initViews();
        fetchList();
        setRecyclerAdapter();
    }

    private void setRecyclerAdapter() {
        searchAdapter = new SearchAdapter(mealsRecipesList,this);
        LinearLayoutManager  linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(searchAdapter);
    }

    private void fetchList() {
        ApiClient apiClient = Network.getRetrofitInstance(this).create(ApiClient.class);
        Call<ResponseRecipes> call = apiClient.getSearch(str);
        call.enqueue(new Callback<ResponseRecipes>() {
            @Override
            public void onResponse(Call<ResponseRecipes> call, Response<ResponseRecipes> response) {
                if (response.code()== HttpURLConnection.HTTP_OK && response.body()!=null){
                    mealsRecipesList = response.body().getMeals();
                        searchAdapter.updateData(mealsRecipesList);
                        frameLayout.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<ResponseRecipes> call, Throwable t) {

            }
        });
    }

    private void initViews() {
        str = getIntent().getStringExtra("search");
        mTitle = findViewById(R.id.searchText);
        mTitle.setText(str);
        recyclerView = findViewById(R.id.recyclerContainer);
        frameLayout = findViewById(R.id.frameProgress);
    }

    @Override
    public void onItemClicked(int position) {
        Intent rescipeIntent = new Intent(this,RecipeActivity.class);
        rescipeIntent.putExtra("data",mealsRecipesList.get(position).getIdMeal()+"");
        startActivity(rescipeIntent);

    }
}