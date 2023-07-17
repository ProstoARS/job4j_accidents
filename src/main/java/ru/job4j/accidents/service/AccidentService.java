package ru.job4j.accidents.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.IAccidentRepository;
import ru.job4j.accidents.repository.RulesMem;
import ru.job4j.accidents.repository.TypeMem;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccidentService {

    private final IAccidentRepository accidentRepository;
    private final TypeMem typeMem;
    private final RulesMem rulesMem;

    public List<Accident> findAll() {
        return accidentRepository.findAll();
    }

    public void add(int[] rIds, Accident accident) {
        int typeId = accident.getType().getId();
        accident.setType(typeMem.findTypeById(typeId).get());
        setRuleById(rIds, accident);
        accidentRepository.add(accident);
    }

    public Optional<Accident> findAccidentsById(int accidentId) {
        return accidentRepository.findAccidentById(accidentId);
    }

    public void update(int[] rIds, Accident accident) {
        int typeId = accident.getType().getId();
        accident.setType(typeMem.findTypeById(typeId).get());
        setRuleById(rIds, accident);
        accidentRepository.update(accident);
    }


    public void setRuleById(int[] rIds, Accident accident) {
        accident.setRules(Arrays.stream(rIds)
                .mapToObj(v -> rulesMem.findById(v).get())
                .collect(Collectors.toSet()));
    }
}
