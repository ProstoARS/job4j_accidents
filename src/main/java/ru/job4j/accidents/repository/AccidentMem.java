package ru.job4j.accidents.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@Profile("mem")
public class AccidentMem implements IAccidentRepository {

    private final AtomicInteger id = new AtomicInteger();
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        Accident accident1 = Accident.builder()
                .id(id.incrementAndGet())
                .name("Иван Иванов")
                .text("Парковка в неположенном месте")
                .address("Ивановская 33")
                .rules(new HashSet<>())
                .build();
        Accident accident2 = Accident.builder()
                .id(id.incrementAndGet())
                .name("Незнайка")
                .text("Упал в канаву на автомобиле Винтика и Шпунтика")
                .address("Цветочная ул.")
                .rules(new HashSet<>())
                .build();
        accident1.getRules().add(new Rule(4, "статья 4"));
        accident2.getRules().add(new Rule(5, "статья 5"));
        accidents.put(accident1.getId(), accident1);
        accidents.put(accident2.getId(), accident2);
    }

    @Override
    public List<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }

    @Override
    public void add(Accident accident) {
        accident.setId(id.incrementAndGet());
        accidents.put(accident.getId(), accident);
    }

    @Override
    public Optional<Accident> findAccidentById(int accidentId) {
        return Optional.ofNullable(accidents.get(accidentId));
    }

    @Override
    public void update(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }
}
