package com.crish.recipes.Data;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;


public class ResponseRecipes implements Serializable {

	@SerializedName("meals")
	private List<MealsRecipes> meals;

	public List<MealsRecipes> getMeals(){
		return meals;
	}

	@Override
 	public String toString(){
		return 
			"ResponseRecipes{" + 
			"meals = '" + meals + '\'' + 
			"}";
		}
}