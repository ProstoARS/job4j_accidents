package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Profile("hbm")
public class TypeHibernate implements ITypeRepository {

    private static final String FIND_ALL = """
            SELECT t FROM AccidentType t
            """;

    private static final String FIND_TYPE_BY_ID = """
            SELECT t FROM AccidentType t
            WHERE t.id = :tId
            """;

    private final CrudRepository crudRepository;

    @Override
    public void addType(AccidentType accidentType) {
        crudRepository.run(session -> session.persist(accidentType));
    }

    @Override
    public List<AccidentType> findAll() {
        return crudRepository.query(FIND_ALL, AccidentType.class);
    }

    @Override
    public Optional<AccidentType> findTypeById(int typeId) {
        return crudRepository.optional(FIND_TYPE_BY_ID, AccidentType.class, Map.of("tId", typeId));
    }
}
