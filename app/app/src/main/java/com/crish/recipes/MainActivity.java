package com.crish.recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.crish.recipes.Data.MealsRecipes;
import com.crish.recipes.Data.ResponseRecipes;
import com.crish.recipes.Network.ApiClient;
import com.crish.recipes.Network.Network;

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
                    if (response.code() == HttpURLConnection.HTTP_OK && response.body() !=null){
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
        mRandomImage = findViewById(R.id.randomResImage);
        mRandomText = findViewById(R.id.randomNameText);
        frameLayoutProgress = findViewById(R.id.progressFrame);
    }
}