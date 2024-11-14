package nl.geckotech.recipe.dao;

import nl.geckotech.recipe.entity.Ingredient;
import nl.geckotech.recipe.entity.Recipe;
import nl.geckotech.recipe.entity.type.RecipeTypeOfMeal;
import nl.geckotech.recipe.exception.RecipeNotFoundException;
import nl.geckotech.recipe.repository.IngredientRepository;
import nl.geckotech.recipe.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class RecipeDaoTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private RecipeDao recipeDao;

    private Recipe recipe;

    @BeforeEach
    void setUp() {
        // Mock data
        recipe = new Recipe("Toast",
                "Crispy toast",
                30,
                RecipeTypeOfMeal.Lunch,
                Set.of(new Ingredient("Dough", 1), new Ingredient("Breadcrumbs", 5))
        );
    }

    @Test
    void testGetAllRecipes() {
        // Given
        List<Recipe> recipes = List.of(recipe);
        when(recipeRepository.findAll()).thenReturn(recipes);

        // When
        List<Recipe> result = recipeDao.getAllRecipes();

        // Then
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.getFirst()).isEqualTo(recipe);
    }

    @Test
    void testGetRecipe_Success() throws RecipeNotFoundException {
        // Given
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        // When
        Recipe result = recipeDao.getRecipe(1L);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Toast");
    }

    @Test
    void testGetRecipe_NotFound() {
        // Given
        when(recipeRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        try {
            recipeDao.getRecipe(1L);
        } catch (RecipeNotFoundException e) {
            assertThat(e).hasMessage("Recipe not found");
        }
    }

    @Test
    void testFindByTypeOfMeal_Success() throws RecipeNotFoundException {
        // Given
        when(recipeRepository.findByTypeOfMeal(RecipeTypeOfMeal.Lunch)).thenReturn(Optional.of(recipe));

        // When
        Recipe result = recipeDao.findByTypeOfMeal(RecipeTypeOfMeal.Lunch);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTypeOfMeal()).isEqualTo(RecipeTypeOfMeal.Lunch);
    }

    @Test
    void testFindByTypeOfMeal_NotFound() {
        // Given
        when(recipeRepository.findByTypeOfMeal(RecipeTypeOfMeal.Dinner)).thenReturn(Optional.empty());

        // When & Then
        try {
            recipeDao.findByTypeOfMeal(RecipeTypeOfMeal.Dinner);
        } catch (RecipeNotFoundException e) {
            assertThat(e).hasMessage("There are no meals with this type: Dinner");
        }
    }

    @Test
    void testCreateRecipe_Success() {
        // Given
        when(recipeRepository.save(recipe)).thenReturn(recipe);

        // When
        Recipe result = recipeDao.createRecipe(recipe);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Toast");
    }

    @Test
    void testCreateRecipe_DataIntegrityViolation() {
        // Given
        when(recipeRepository.save(recipe)).thenThrow(DataIntegrityViolationException.class);

        // When & Then
        try {
            recipeDao.createRecipe(recipe);
        } catch (DataIntegrityViolationException e) {
            assertThat(e).isInstanceOf(DataIntegrityViolationException.class);
        }
    }

    @Test
    void testDeleteRecipe_Success() throws RecipeNotFoundException {
        // Given
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        // When
        Recipe result = recipeDao.deleteRecipe(1L);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Toast");
    }

    @Test
    void testDeleteRecipe_NotFound() {
        // Given
        when(recipeRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        try {
            recipeDao.deleteRecipe(1L);
        } catch (RecipeNotFoundException e) {
            assertThat(e).hasMessage("Recipe not found");
        }
    }
}