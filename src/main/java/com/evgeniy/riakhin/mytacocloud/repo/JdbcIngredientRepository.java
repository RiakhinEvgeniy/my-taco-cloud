package com.evgeniy.riakhin.mytacocloud.repo;

import com.evgeniy.riakhin.mytacocloud.model.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        String sql = "select id, name, type from Ingredient";
        return jdbcTemplate.query(sql, this::mapRowToIngredient);
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        String sql = "select id, name, type from Ingredient where id = ?";
        List<Ingredient> ingredients = jdbcTemplate.query(sql, this::mapRowToIngredient, id);
        return ingredients.isEmpty() ? Optional.empty() : Optional.of(ingredients.getFirst());
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        String sql = "insert into Ingredient (id, name, type) values (?, ?, ?)";
        jdbcTemplate.update(sql, ingredient.getId(), ingredient.getName(), ingredient.getType().toString());
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
        return new Ingredient(row.getString("id"),
                row.getString("name"),
                Ingredient.Type.valueOf(row.getString("type").toUpperCase()));
    }
}
