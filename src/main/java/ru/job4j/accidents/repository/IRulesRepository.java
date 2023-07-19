package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRulesRepository {

    List<Rule> findAll();

    Optional<Rule> findById(int id);
}
