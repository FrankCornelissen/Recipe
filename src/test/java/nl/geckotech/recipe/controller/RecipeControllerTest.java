package nl.geckotech.recipe.controller;
import nl.geckotech.recipe.entity.Ingredient;
import nl.geckotech.recipe.entity.Recipe;
import nl.geckotech.recipe.entity.type.RecipeTypeOfMeal;
import nl.geckotech.recipe.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RecipeRepository recipeRepository;

    private Recipe recipe;

    @BeforeEach
    void setUp() {
        recipe = new Recipe("Toast",
                "Crispy toast",
                30,
                RecipeTypeOfMeal.Lunch,
                Set.of(new Ingredient("Dough", 1), new Ingredient("Breadcrumbs", 5))
        );
        recipeRepository.save(recipe);
    }

    @Test
    void shouldReturnAllRecipes() throws Exception {
        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Toast"));
    }

    @Test
    void shouldReturnRecipeById() throws Exception {
        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/withId?id=" + recipe.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Toast"));
    }

    @Test
    void shouldCreateNewRecipe() throws Exception {
        // Given a new recipe
        String newRecipeJson = "{ \"name\": \"Pasta\", \"description\": \"Delicious pasta\", \"cookTime\": 25, \"typeOfMeal\": \"Dinner\", \"ingredients\": [{\"name\": \"Pasta\", \"amount\": 1}, {\"name\": \"Tomato Sauce\", \"amount\": 2}]}";

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/recipe/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newRecipeJson))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Pasta"));
    }

    @Test
    void shouldDeleteRecipe() throws Exception {
        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/recipe/withId?id=" + recipe.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Toast"));
    }


    @Test
    void shouldReturnRecipeByTypeOfMeal() throws Exception {
        String typeOfMeal = RecipeTypeOfMeal.Lunch.name();

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/typeOfMeal")
                        .param("typeOfMeal", typeOfMeal))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Toast"));
    }
}