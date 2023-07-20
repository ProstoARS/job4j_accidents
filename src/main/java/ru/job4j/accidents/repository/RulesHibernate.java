package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.List;
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

    private final SessionFactory sf;


    @Override
    public List<Rule> findAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery(FIND_ALL, Rule.class)
                    .list();
        }
    }

    @Override
    public Optional<Rule> findById(int id) {
        Optional<Rule> optionalRule;
        try (Session session = sf.openSession()) {
            optionalRule = session
                    .createQuery(FIND_RULES_BY_ID, Rule.class)
                    .setParameter("tId", id)
                    .uniqueResultOptional();
        }
        return optionalRule;
    }
}
