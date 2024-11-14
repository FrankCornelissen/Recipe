package nl.geckotech.recipe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.geckotech.recipe.entity.Ingredient;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeResponse {
    private String name;
    private String description;
    private Set<Ingredient> ingredients;
    private int timeToCook;

    public RecipeResponse(String name, String description, Set<Ingredient> ingredients, int timeToCook) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.timeToCook = timeToCook;
    }



}
