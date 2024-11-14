package nl.geckotech.recipe.controller;


import nl.geckotech.recipe.dto.RecipeRequest;
import nl.geckotech.recipe.dto.RecipeResponse;
import nl.geckotech.recipe.entity.type.RecipeTypeOfMeal;
import nl.geckotech.recipe.exception.RecipeNotFoundException;
import nl.geckotech.recipe.service.RecipeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = RecipeController.RECIPE_PATH)
public class RecipeController {
    private final RecipeService recipeService;
    static final String RECIPE_PATH = "/recipe";

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    private HttpHeaders createHeader(String name, String value){
        HttpHeaders headers = new HttpHeaders();
        headers.add(name, value);
        return headers;
    }

    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<List<RecipeResponse>> getAllRecipes() {
        return new ResponseEntity<> (
                recipeService.getAllRecipes(),
                createHeader("Recipes", "ALL"),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/withId", produces = "application/json")
    public ResponseEntity<RecipeResponse> getRecipe(@RequestParam(name = "id") Long id ) throws RecipeNotFoundException {
        return new ResponseEntity<> (
                recipeService.getRecipe(id),
                createHeader("Recipe", id.toString()),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/typeOfMeal", produces = "application/json")
    public ResponseEntity<RecipeResponse> findByTypeOfMeal(@RequestParam(name = "typeOfMeal") String typeOfMeal ) throws RecipeNotFoundException {
        return new ResponseEntity<> (
                recipeService.findByTypeOfMeal(RecipeTypeOfMeal.valueOf(typeOfMeal)),
                createHeader("Recipe type of meal", typeOfMeal),
                HttpStatus.OK
        );
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<RecipeResponse> createRecipe(@RequestBody RecipeRequest recipeRequest) {
        RecipeResponse newRecipe = recipeService.createRecipe(recipeRequest);
        return new ResponseEntity<>(
                newRecipe,
                createHeader("Recipe created:", newRecipe.getName()),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping(path = "/withId", produces = "application/json")
    public ResponseEntity<RecipeResponse> deleteRecipe(@RequestParam(name = "id") Long id) throws RecipeNotFoundException {
        return new ResponseEntity<>(
                recipeService.deleteRecipe(id),
                createHeader("Recipe deleted: ", id.toString()),
                HttpStatus.OK
        );
    }
}
