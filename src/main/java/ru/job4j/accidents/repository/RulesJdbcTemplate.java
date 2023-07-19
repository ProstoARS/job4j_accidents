package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.*;

@Repository
@AllArgsConstructor
@Profile("jdbc")
public class RulesJdbcTemplate implements IRulesRepository {

    private static final String FIND_ALL = """
            SELECT * FROM rules
            """;

    private static final String FIND_RULE_BY_ID = """
            SELECT * FROM rules
            WHERE id = ?
            """;

    private final JdbcTemplate jdbc;

    @Override
    public List<Rule> findAll() {
        return jdbc.query(FIND_ALL, new BeanPropertyRowMapper<>(Rule.class));
    }

    @Override
    public Optional<Rule> findById(int id) {
        return jdbc.query(FIND_RULE_BY_ID,
                new BeanPropertyRowMapper<>(Rule.class),
                id).stream().findAny();
    }
}
