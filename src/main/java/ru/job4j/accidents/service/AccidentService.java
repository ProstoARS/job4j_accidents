package ru.job4j.accidents.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccidentService {

    private int id = 0;

    private final AccidentMem accidentMem;

    public List<Accident> findAll() {
        return accidentMem.findAll();
    }

    public void add(Accident accident) {
        accident.setId(id++);
        accidentMem.add(accident);
    }
}
