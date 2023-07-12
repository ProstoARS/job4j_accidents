package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.TypeMem;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TypeService {

    private final TypeMem typeMem;

    public void addType(AccidentType accidentType) {
        typeMem.addType(accidentType);
    }

    public List<AccidentType> findAllTypes() {
        return typeMem.findAllTypes();
    }

    public Optional<AccidentType> findTypeById(int typeId) {
        return typeMem.findTypeById(typeId);
    }
}
