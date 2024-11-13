package nl.geckotech.recipe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeRequest {
    private String name;
    private String desciption;
    private int timeToCook;

    public RecipeRequest(String name, String desciption, int timeToCook) {
        this.name = name;
        this.desciption = desciption;
        this.timeToCook = timeToCook;
    }

}
