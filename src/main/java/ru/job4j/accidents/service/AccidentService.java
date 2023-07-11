package ru.job4j.accidents.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccidentService {

    private int id = 3;

    private final AccidentMem accidentMem;

    public List<Accident> findAll() {
        return accidentMem.findAll();
    }

    public void add(Accident accident) {
        accident.setId(id++);
        accidentMem.add(accident);
    }

    public Optional<Accident> findAccidentsById(int accidentId) {
        return accidentMem.findAccidentById(accidentId);
    }

    public void update(Accident accident) {
        accidentMem.update(accident);
    }
}
