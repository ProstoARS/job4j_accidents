package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RulesMem;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RuleService {

    private final RulesMem rulesMem;

    public List<Rule> findAll() {
        return rulesMem.findAll();
    }

    public void setRuleById(String[] ids, Accident accident) {
       accident.setRules(Arrays.stream(ids)
                       .map(v -> rulesMem.findById(Integer.parseInt(v)).get())
               .collect(Collectors.toSet()));
    }
}
