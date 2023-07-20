package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;
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

    private final SessionFactory sf;
    @Override
    public void addType(AccidentType accidentType) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.persist(accidentType);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<AccidentType> findAll() {
        try (Session session = sf.openSession()) {
            return session.createQuery(FIND_ALL, AccidentType.class)
                    .getResultList();
        }
    }

    @Override
    public Optional<AccidentType> findTypeById(int typeId) {
        Optional<AccidentType> optionalType;
        try (Session session = sf.openSession()) {
            optionalType = session.createQuery(FIND_TYPE_BY_ID, AccidentType.class)
                    .setParameter("tId", typeId)
                    .uniqueResultOptional();
        }
        return optionalType;
    }
}
