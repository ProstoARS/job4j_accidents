package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITypeRepository {

    void addType(AccidentType accidentType);

    List<AccidentType> findAll();

    Optional<AccidentType> findTypeById(int typeId);
}
