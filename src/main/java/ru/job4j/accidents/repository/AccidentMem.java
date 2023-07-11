package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        Accident accident1 = Accident.builder()
                .id(1)
                .name("Иван Иванов")
                .text("Парковка в неположенном месте")
                .address("Ивановская 33")
                .build();
        Accident accident2 = Accident.builder()
                .id(2)
                .name("Незнайка")
                .text("Упал в канаву на автомобиле Винтика и Шпунтика")
                .address("Цветочная ул.")
                .build();
        accidents.put(1, accident1);
        accidents.put(2, accident2);
    }

    public List<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }

    public void add(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Optional<Accident> findAccidentById(int accidentId) {
        return Optional.ofNullable(accidents.get(accidentId));
    }

    public void update(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }
}
