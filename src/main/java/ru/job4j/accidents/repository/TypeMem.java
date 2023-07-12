package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TypeMem {

    private final AtomicInteger id = new AtomicInteger();
    private final Map<Integer, AccidentType> typeMap = new ConcurrentHashMap<>();

    public TypeMem() {
        AccidentType accidentType = AccidentType.builder().id(id.incrementAndGet()).name("Машина").build();
        typeMap.put(accidentType.getId(), accidentType);
        AccidentType accidentType1 = AccidentType.builder().id(id.incrementAndGet()).name("Две машины").build();
        typeMap.put(accidentType1.getId(), accidentType1);
        AccidentType accidentType2 = AccidentType.builder().id(id.incrementAndGet()).name("Машина и человек").build();
        typeMap.put(accidentType2.getId(), accidentType2);
        AccidentType accidentType3 = AccidentType.builder().id(id.incrementAndGet()).name("Машина и велосипед").build();
        typeMap.put(accidentType3.getId(), accidentType3);
    }

    public void addType(AccidentType accidentType) {
        accidentType.setId(id.incrementAndGet());
        typeMap.put(accidentType.getId(), accidentType);
    }

    public List<AccidentType> findAllTypes() {
        return new ArrayList<>(typeMap.values());
    }

    public Optional<AccidentType> findTypeById(int typeId) {
        return Optional.ofNullable(typeMap.get(typeId));
    }
}
