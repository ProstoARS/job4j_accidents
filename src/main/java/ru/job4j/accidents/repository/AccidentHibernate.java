package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import java.util.List;
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

    private final SessionFactory sf;


    @Override
    public List<Accident> findAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery(SELECT_ACCIDENTS, Accident.class)
                    .list();
        }
    }

    @Override
    public void add(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.persist(accident);
            session.getTransaction().commit();
        }
    }

    @Override
    public Optional<Accident> findAccidentById(int accidentId) {
        Optional<Accident> optionalAccident;
        try (Session session = sf.openSession()) {
            optionalAccident = session.createQuery(SELECT_ACCIDENT, Accident.class)
                    .setParameter("fId", accidentId)
                    .uniqueResultOptional();
        }
        return optionalAccident;
    }

    @Override
    public void update(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.merge(accident);
            session.getTransaction().commit();
        }
    }
}
