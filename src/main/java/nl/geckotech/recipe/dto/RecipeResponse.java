package nl.geckotech.recipe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class RecipeResponse {
    private UUID id;
    private String name;

    public RecipeResponse(String name) {
        this.name = name;
    }
}
