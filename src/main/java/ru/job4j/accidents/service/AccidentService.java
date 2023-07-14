package ru.job4j.accidents.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;
import ru.job4j.accidents.repository.TypeMem;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccidentService {

    private final AccidentMem accidentMem;
    private final TypeMem typeMem;

    public List<Accident> findAll() {
        return accidentMem.findAll();
    }

    public void add(Accident accident) {
        accidentMem.add(accident);
    }

    public Optional<Accident> findAccidentsById(int accidentId) {
        return accidentMem.findAccidentById(accidentId);
    }

    public void update(Accident accident) {
        accidentMem.update(accident);
    }

    public void setTypeById(Accident accident) {
        int typeId = accident.getType().getId();
        accident.setType(typeMem.findTypeById(typeId).get());
    }
}
