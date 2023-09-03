package ru.job4j.accidents.repository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

@Profile({"jpa", "test"})
public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Query("FROM Accident a join fetch a.type join fetch a.rules ORDER BY a.id")
    List<Accident> findAllByOrderById();

    @Query("FROM Accident a join fetch a.type join fetch a.rules where a.id = :id")
    Optional<Accident> findById(int id);

    Optional<Accident> findByName(String name);
}
