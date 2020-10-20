package com.crish.recipes.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.crish.recipes.Data.ResponseRecipes;
import com.crish.recipes.Network.ApiClient;
import com.crish.recipes.Network.Network;
import com.crish.recipes.R;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeActivity extends AppCompatActivity {
    private ImageButton imageButton;
    private TextView description;
    private TextView header;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        initViews();
        fetchData();
    }

    private void fetchData() {
        ApiClient apiClient = Network.getRetrofitInstance(this).create(ApiClient.class);
        int n = Integer.parseInt(getIntent().getStringExtra("data"));
        final Call<ResponseRecipes> recipesCall = apiClient.getRecipe(n);
        recipesCall.enqueue(new Callback<ResponseRecipes>() {
            @Override
            public void onResponse(Call<ResponseRecipes> call, final Response<ResponseRecipes> response) {
                if (response.code() == HttpURLConnection.HTTP_OK && response.body() !=null){
                    description.setText(response.body().getMeals().get(0).getStrInstructions());
                    header.setText(response.body().getMeals().get(0).getStrMeal());
                    Glide.with(imageButton).load(response.body().getMeals().get(0).getStrMealThumb()).into(imageButton);
                    frameLayout.setVisibility(View.GONE);
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getMeals().get(0).getStrYoutube()));
                            startActivity(appIntent);
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<ResponseRecipes> call, Throwable t) {

            }
        });
    }

    private void initViews() {
        imageButton = findViewById(R.id.imageRescipe);
        description = findViewById(R.id.rescipeDes);
        header = findViewById(R.id.headTextView);
        frameLayout = findViewById(R.id.recipeProgress);
    }
}