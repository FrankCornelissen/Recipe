package nl.geckotech.recipe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.geckotech.recipe.entity.Ingredients;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeResponse {
    private String name;
    private String description;
    private Set<Ingredients> ingredients;
    private int timeToCook;

    public RecipeResponse(String name, String description, Set<Ingredients> ingredients, int timeToCook) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.timeToCook = timeToCook;
    }

}
