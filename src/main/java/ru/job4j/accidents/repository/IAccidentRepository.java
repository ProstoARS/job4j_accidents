package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface IAccidentRepository {

    List<Accident> findAll();

    void add(Accident accident);

    Optional<Accident> findAccidentById(int accidentId);

    void update(Accident accident);

    Set<Rule> findAllRulesByAccidentId(int accidentId);
}
