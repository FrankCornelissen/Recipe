package nl.geckotech.recipe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.geckotech.recipe.entity.type.RecipeTypeOfMeal;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    //    Time in minutes
    @Column(name = "time_to_cook")
    private int timeToCook;

    //   Enum value
    @Column(name = "type_of_meal")
    @Enumerated(EnumType.STRING)
    private RecipeTypeOfMeal typeOfMeal;

    @ManyToMany(mappedBy = "recipes")
    @JsonIgnore
    private Set<Ingredient> ingredients = new HashSet<>();

    public Recipe(String name) {
        this.name = name;
    }

    public Recipe(String name, String description, int timeToCook) {
        this.name = name;
        this.description = description;
        this.timeToCook = timeToCook;
    }

    public Recipe(String name, String description, int timeToCook, RecipeTypeOfMeal typeOfMeal, Set<Ingredient> ingredients) {
        this.name = name;
        this.description = description;
        this.timeToCook = timeToCook;
        this.typeOfMeal = typeOfMeal;
        this.ingredients = ingredients;
    }
}
