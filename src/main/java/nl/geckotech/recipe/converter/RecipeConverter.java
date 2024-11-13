package nl.geckotech.recipe.converter;

import nl.geckotech.recipe.dto.RecipeRequest;
import nl.geckotech.recipe.dto.RecipeResponse;
import nl.geckotech.recipe.entity.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeConverter {

    public static Recipe convert(RecipeRequest recipeRequest) {
        return new Recipe(
                recipeRequest.getName()
        );
    }

    public static RecipeResponse convert(Recipe recipe) {
        return new RecipeResponse(
                recipe.getName()
        );
    }

    public static List<RecipeResponse> convert(List<Recipe> recipes) {
        List<RecipeResponse> recipeResponses = new ArrayList<>();
        recipes.forEach(recipe -> recipeResponses.add(convert(recipe)));
        return recipeResponses;
    }
}