package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Profile("jdbc")
public class TypeJdbcTemplate implements ITypeRepository {

    private static final String FIND_ALL = """
            SELECT * FROM type
            """;

    private static final String INSERT_TYPE = """
            INSERT INTO type (name)
            VALUES (?)
            """;

    private static final String FIND_TYPE_BY_ID = """
            SELECT * FROM type
            WHERE id = ?
            """;

    private final JdbcTemplate jdbc;

    @Override
    public void addType(AccidentType accidentType) {
        jdbc.update(INSERT_TYPE, accidentType.getName());
    }

    @Override
    public List<AccidentType> findAll() {
        return jdbc.query(FIND_ALL, new BeanPropertyRowMapper<>(AccidentType.class));
    }

    @Override
    public Optional<AccidentType> findTypeById(int typeId) {
        return jdbc.query(FIND_TYPE_BY_ID,
                new BeanPropertyRowMapper<>(AccidentType.class),
                        typeId)
                .stream().findAny();
    }
}
