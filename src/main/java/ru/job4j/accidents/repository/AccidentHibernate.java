package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Profile("hbm")
public class AccidentHibernate implements IAccidentRepository {

    private static final String SELECT_ACCIDENTS = """
            SELECT DISTINCT a FROM Accident a
            JOIN FETCH a.type
            JOIN FETCH a.rules
            """;

    private static final String SELECT_ACCIDENT = """
            SELECT a FROM Accident a
            JOIN FETCH a.rules
            WHERE a.id = :fId
            """;

    private final CrudRepository crudRepository;


    @Override
    public List<Accident> findAll() {
        return crudRepository.query(SELECT_ACCIDENTS, Accident.class);
    }

    @Override
    public void add(Accident accident) {
         crudRepository.run(session -> session.persist(accident));
    }

    @Override
    public Optional<Accident> findAccidentById(int accidentId) {
        return crudRepository.optional(SELECT_ACCIDENT, Accident.class, Map.of("fId", accidentId));
    }

    @Override
    public void update(Accident accident) {
        crudRepository.run(session -> session.merge(accident));
    }
}
