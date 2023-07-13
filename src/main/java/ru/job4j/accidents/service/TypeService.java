package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
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

    public List<AccidentType> findAll() {
        return typeMem.findAll();
    }

    public Optional<AccidentType> findTypeById(int typeId) {
        return typeMem.findTypeById(typeId);
    }

    public void setTypeById(Accident accident) {
        int typeId = accident.getType().getId();
        accident.setType(typeMem.findTypeById(typeId).get());
    }
}
