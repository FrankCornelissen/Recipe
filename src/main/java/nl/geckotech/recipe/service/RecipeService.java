package nl.geckotech.recipe.service;

import nl.geckotech.recipe.dao.RecipeDao;
import nl.geckotech.recipe.dto.RecipeRequest;
import nl.geckotech.recipe.dto.RecipeResponse;
import nl.geckotech.recipe.entity.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;

import static nl.geckotech.recipe.converter.RecipeConverter.*;

@Service
public class RecipeService {

    private final RecipeDao recipeDao;

    public RecipeService(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    public List<RecipeResponse> getAllRecipes(){
        return convert(recipeDao.getAllRecipes());
    }

    public RecipeResponse createRecipe(RecipeRequest recipeRequest){
        Recipe recipe = convert(recipeRequest);
        return convert(recipeDao.createRecipe(recipe));
    }
}