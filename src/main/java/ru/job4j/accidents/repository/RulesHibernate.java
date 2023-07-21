package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Profile("hbm")
public class RulesHibernate implements IRulesRepository {

    private static final String FIND_ALL = """
            SELECT r FROM Rule r
            """;

    private static final String FIND_RULES_BY_ID = """
            SELECT r FROM Rule r
            WHERE r.id = :tId
            """;

    private final CrudRepository crudRepository;


    @Override
    public List<Rule> findAll() {
            return crudRepository
                    .query(FIND_ALL, Rule.class);
    }

    @Override
    public Optional<Rule> findById(int id) {
        return crudRepository.optional(FIND_RULES_BY_ID, Rule.class, Map.of("tId", id));
    }
}
