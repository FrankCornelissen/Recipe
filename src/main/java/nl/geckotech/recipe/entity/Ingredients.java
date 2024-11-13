package nl.geckotech.recipe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "ingredients")
public class Ingredients {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private int amount;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_ingredients",
            joinColumns = { @JoinColumn(name = "ingredient_id") },
            inverseJoinColumns = { @JoinColumn(name = "recipe_id") }
    )
    Set<Recipe> recipes = new HashSet<>();
}
