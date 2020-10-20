package com.crish.recipes.Network;

import com.crish.recipes.Data.ResponseRecipes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiClient {
    @GET("search.php/")
    Call<ResponseRecipes> getSearch(@Query("s") String search);
    @GET("lookup.php/")
    Call<ResponseRecipes> getRecipe(@Query("i") int recipeId);
    @GET("random.php/")
    Call<ResponseRecipes> getRandom();
}
