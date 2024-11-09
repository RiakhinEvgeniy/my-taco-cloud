package com.evgeniy.riakhin.mytacocloud.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class Taco {
    @NotNull
    @Size(min = 4, message = "Name must be at last 4 characters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "You must choose at last 1 ingredient")
    private List<Ingredient> ingredients;
}
