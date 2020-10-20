package com.crish.recipes.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.crish.recipes.Data.MealsRecipes;
import com.crish.recipes.Data.ResponseRecipes;
import com.crish.recipes.Network.ApiClient;
import com.crish.recipes.Network.Network;
import com.crish.recipes.R;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ImageView mRandomImage;
    private TextView mRandomText;
    private List<MealsRecipes> mealsRecipes;
    private FrameLayout frameLayoutProgress;
    private SearchView searchView;
    private Button biryani, pizza, noodles;
    private LinearLayout randomBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        fetchRandomData();
    }

    private void fetchRandomData() {
        ApiClient apiClient = Network.getRetrofitInstance(this).create(ApiClient.class);
        final Call<ResponseRecipes> recipesCall = apiClient.getRandom();
        recipesCall.enqueue(new Callback<ResponseRecipes>() {
            @Override
            public void onResponse(Call<ResponseRecipes> call, Response<ResponseRecipes> response) {
                if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
                    mealsRecipes = response.body().getMeals();
                    Glide.with(mRandomImage).load(mealsRecipes.get(0).getStrMealThumb()).into(mRandomImage);
                    mRandomText.setText(mealsRecipes.get(0).getStrMeal());
                    frameLayoutProgress.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseRecipes> call, Throwable t) {

            }
        });
    }

    private void initViews() {
        biryani = findViewById(R.id.biryaniBtn);
        pizza = findViewById(R.id.pizzaBtn);
        noodles = findViewById(R.id.noodlesBtn);
        randomBtn = findViewById(R.id.randomBtn);
        mRandomImage = findViewById(R.id.randomResImage);
        mRandomText = findViewById(R.id.randomNameText);
        frameLayoutProgress = findViewById(R.id.progressFrame);
        searchView = findViewById(R.id.searchBarMain);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(MainActivity.this, SearchResultsActivity.class);
                intent.putExtra("search", query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void openRecipe(View view) {
        switch (view.getId()) {
            case R.id.biryaniBtn:
                Intent intentBir = new Intent(MainActivity.this, RecipeActivity.class);
                intentBir.putExtra("data", "52805");
                startActivity(intentBir);
                break;
            case R.id.pizzaBtn:
                Intent intentPizza = new Intent(MainActivity.this, RecipeActivity.class);
                intentPizza.putExtra("data", "53014");
                startActivity(intentPizza);
                break;
            case R.id.noodlesBtn :
                Intent intentnood = new Intent(MainActivity.this, RecipeActivity.class);
                intentnood.putExtra("data", "52821");
                startActivity(intentnood);
                break;
            case R.id.randomBtn :
                Intent randomIntent = new Intent(MainActivity.this, RecipeActivity.class);
                randomIntent.putExtra("data",mealsRecipes.get(0).getIdMeal()+"");
                break;
        }
    }
}