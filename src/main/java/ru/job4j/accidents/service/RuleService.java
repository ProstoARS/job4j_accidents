package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RulesMem;
import java.util.List;

@Service
@AllArgsConstructor
public class RuleService {

    private final RulesMem rulesMem;

    public List<Rule> findAll() {
        return rulesMem.findAll();
    }
}