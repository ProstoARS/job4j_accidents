package ru.job4j.accidents.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.AccidentType;

@Profile("jpa")
public interface TypeRepository extends CrudRepository<AccidentType, Integer> {

}
