package nl.geckotech.recipe.service;

import nl.geckotech.recipe.dao.RecipeDao;
import nl.geckotech.recipe.dto.RecipeRequest;
import nl.geckotech.recipe.dto.RecipeResponse;
import nl.geckotech.recipe.entity.Recipe;
import nl.geckotech.recipe.entity.type.RecipeTypeOfMeal;
import nl.geckotech.recipe.exception.RecipeNotFoundException;
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

    public RecipeResponse getRecipe(Long id) throws RecipeNotFoundException {
        return convert(recipeDao.getRecipe(id));
    }

    public RecipeResponse findByTypeOfMeal(RecipeTypeOfMeal typeOfMeal) throws RecipeNotFoundException {
        return convert(recipeDao.findByTypeOfMeal(typeOfMeal));
    }

    public RecipeResponse createRecipe(RecipeRequest recipeRequest){
        Recipe recipe = convert(recipeRequest);
        return convert(recipeDao.createRecipe(recipe));
    }

    public RecipeResponse deleteRecipe(Long id) throws RecipeNotFoundException {
        return convert(recipeDao.deleteRecipe(id));
    }
}
