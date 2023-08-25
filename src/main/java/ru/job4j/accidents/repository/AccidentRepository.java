package ru.job4j.accidents.repository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Accident;

import java.util.List;

@Profile({"jpa", "test"})
public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    List<Accident> findAllByOrderById();
}
