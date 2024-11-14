package nl.geckotech.recipe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.geckotech.recipe.entity.Ingredient;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeRequest {
    private String name;
    private String description;
    private int timeToCook;
    private Set<Ingredient> ingredients;

    public RecipeRequest(String name, String description, int timeToCook) {
        this.name = name;
        this.description = description;
        this.timeToCook = timeToCook;
    }

    public RecipeRequest(String name, String description, int timeToCook, Set<Ingredient> ingredients) {
        this.name = name;
        this.description = description;
        this.timeToCook = timeToCook;
        this.ingredients = ingredients;
    }
}
