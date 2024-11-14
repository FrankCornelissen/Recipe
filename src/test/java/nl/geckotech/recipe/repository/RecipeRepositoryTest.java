package nl.geckotech.recipe.repository;

import nl.geckotech.recipe.entity.Ingredient;
import nl.geckotech.recipe.entity.Recipe;
import nl.geckotech.recipe.entity.type.RecipeTypeOfMeal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RecipeRepositoryTest {

    @Autowired
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp(){
        List<Recipe> recipes = List.of(
                new Recipe("Pancakes",
                        "Fluffy breakfast pancakes",
                        15,
                        RecipeTypeOfMeal.Breakfast,
                        Set.of(
                                new Ingredient("Flour", 100),
                                new Ingredient("Milk", 200),
                                new Ingredient("Eggs", 2),
                                new Ingredient("Butter", 20))
                ),
                new Recipe("Toast",
                        "Lovely crispy toast",
                        30,
                        RecipeTypeOfMeal.Lunch,
                        Set.of(
                                new Ingredient("Dough", 1),
                                new Ingredient("Breadcrumbs", 5))
                )
        );

        recipeRepository.saveAll(recipes);
    }

    @Test
    void shouldReturnRecipeById() {
        Optional<Recipe> recipe = recipeRepository.findById(1L);
        assertThat(recipe).isNotNull();
    }

    @Test
    void shouldReturnRecipeByTypeOfMeal() {
        // Given
        RecipeTypeOfMeal type = RecipeTypeOfMeal.Breakfast;

        // When
        Optional<Recipe> recipe = recipeRepository.findByTypeOfMeal(type);

        // Then
        assertThat(recipe).isPresent();
        assertThat(recipe.get().getName()).isEqualTo("Pancakes");
    }

    @Test
    void shouldReturnEmptyWhenRecipeTypeNotFound() {
        // Given
        RecipeTypeOfMeal type = RecipeTypeOfMeal.Dinner;

        // When
        Optional<Recipe> recipe = recipeRepository.findByTypeOfMeal(type);

        // Then
        assertThat(recipe).isEmpty();
    }

}