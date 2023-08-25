package ru.job4j.accidents.repository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Rule;

import java.util.List;

@Profile({"jpa", "test"})
public interface RulesRepository extends CrudRepository<Rule, Integer> {

    List<Rule> findAllByOrderById();
}
