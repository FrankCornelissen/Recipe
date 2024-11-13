package nl.geckotech.recipe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Enum typeOfMeal;

    @ManyToMany(mappedBy = "recipes")
    private Set<Ingredients> ingredients = new HashSet<>();

    public Recipe(String name) {
        this.name = name;
    }

    public Recipe(String name, String description, int timeToCook, Enum typeOfMeal, Set<Ingredients> ingredients) {
        this.name = name;
        this.description = description;
        this.timeToCook = timeToCook;
        this.typeOfMeal = typeOfMeal;
        this.ingredients = ingredients;
    }
}
