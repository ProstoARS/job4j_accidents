package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final AtomicInteger id = new AtomicInteger();
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        Accident accident1 = Accident.builder()
                .id(id.getAndIncrement())
                .name("Иван Иванов")
                .text("Парковка в неположенном месте")
                .address("Ивановская 33")
                .build();
        Accident accident2 = Accident.builder()
                .id(id.getAndIncrement())
                .name("Незнайка")
                .text("Упал в канаву на автомобиле Винтика и Шпунтика")
                .address("Цветочная ул.")
                .build();
        accidents.put(accident1.getId(), accident1);
        accidents.put(accident2.getId(), accident2);
    }

    public List<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }

    public void add(Accident accident) {
        accident.setId(id.getAndIncrement());
        accidents.put(accident.getId(), accident);
    }

    public Optional<Accident> findAccidentById(int accidentId) {
        return Optional.ofNullable(accidents.get(accidentId));
    }

    public void update(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }
}
