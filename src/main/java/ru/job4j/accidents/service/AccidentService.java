package ru.job4j.accidents.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccidentService {

    private final AccidentRepository accidentRepository;
    private final TypeRepository typeRepository;
    private final RulesRepository rulesRepository;

    public List<Accident> findAll() {
        return accidentRepository.findAllByOrderById();
    }

    public void add(int[] rIds, Accident accident) {
        int typeId = accident.getType().getId();
        accident.setType(typeRepository.findById(typeId).get());
        setRuleById(rIds, accident);
        accidentRepository.save(accident);
    }

    public Optional<Accident> findAccidentById(int accidentId) {
        return accidentRepository.findById(accidentId);
    }

    public void update(int[] rIds, Accident accident) {
        int typeId = accident.getType().getId();
        accident.setType(typeRepository.findById(typeId).get());
        setRuleById(rIds, accident);
        accidentRepository.save(accident);
    }

    public void setRuleById(int[] rIds, Accident accident) {
        accident.setRules(Arrays.stream(rIds)
                .mapToObj(v -> rulesRepository.findById(v).get())
                .collect(Collectors.toSet()));
    }
}
