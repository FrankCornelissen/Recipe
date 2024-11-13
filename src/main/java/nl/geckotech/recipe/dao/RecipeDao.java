package nl.geckotech.recipe.dao;

import nl.geckotech.recipe.entity.Recipe;
import nl.geckotech.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeDao {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeDao(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    };

//    TODO throws DataIntegrityViolationException
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
}
