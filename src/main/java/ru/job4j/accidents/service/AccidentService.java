package ru.job4j.accidents.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.IAccidentRepository;
import ru.job4j.accidents.repository.IRulesRepository;
import ru.job4j.accidents.repository.ITypeRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccidentService {

    private final IAccidentRepository accidentRepository;
    private final ITypeRepository typeRepository;
    private final IRulesRepository rulesRepository;

    public List<Accident> findAll() {
        List<Accident> all = accidentRepository.findAll();
        all.forEach(accident -> accident.setRules(
                accidentRepository.findAllRulesByAccidentId(accident.getId())));
        return all;
    }

    public void add(int[] rIds, Accident accident) {
        int typeId = accident.getType().getId();
        accident.setType(typeRepository.findTypeById(typeId).get());
        setRuleById(rIds, accident);
        accidentRepository.add(accident);
    }

    public Optional<Accident> findAccidentsById(int accidentId) {
        return accidentRepository.findAccidentById(accidentId);
    }

    public void update(int[] rIds, Accident accident) {
        int typeId = accident.getType().getId();
        accident.setType(typeRepository.findTypeById(typeId).get());
        setRuleById(rIds, accident);
        accidentRepository.update(accident);
    }

    public void setRuleById(int[] rIds, Accident accident) {
        accident.setRules(Arrays.stream(rIds)
                .mapToObj(v -> rulesRepository.findById(v).get())
                .collect(Collectors.toSet()));
    }
}
