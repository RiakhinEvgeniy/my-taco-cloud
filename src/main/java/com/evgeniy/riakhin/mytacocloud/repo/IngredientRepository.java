package com.evgeniy.riakhin.mytacocloud.repo;

import com.evgeniy.riakhin.mytacocloud.model.Ingredient;

import java.util.Optional;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);
}
