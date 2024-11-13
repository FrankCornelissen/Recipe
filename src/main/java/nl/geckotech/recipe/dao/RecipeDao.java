package nl.geckotech.recipe.dao;

import nl.geckotech.recipe.entity.Recipe;
import nl.geckotech.recipe.entity.type.RecipeTypeOfMeal;
import nl.geckotech.recipe.exception.RecipeNotFoundException;
import nl.geckotech.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeDao {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeDao(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe getRecipe(Long id) throws RecipeNotFoundException {
        Optional<Recipe> recipe = recipeRepository.findById(id);

        if (recipe.isEmpty())
            throw new RecipeNotFoundException("Recipe not found");

        return recipe.get();

    }

    public Recipe findByTypeOfMeal(RecipeTypeOfMeal typeOfMeal) throws RecipeNotFoundException {
        Optional<Recipe> recipe = recipeRepository.findByTypeOfMeal(typeOfMeal);

        if (recipe.isEmpty())
            throw new RecipeNotFoundException("There are no meals with this type: " + typeOfMeal.toString());

        return recipe.get();
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    };

//    TODO throws DataIntegrityViolationException
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe deleteRecipe(Long id) throws RecipeNotFoundException {
        Optional<Recipe> recipe = recipeRepository.findById(id);

        if (recipe.isEmpty())
            throw new RecipeNotFoundException("Recipe not found");

        return recipe.get();

    }

}
