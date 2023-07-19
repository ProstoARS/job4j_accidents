package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.IRulesRepository;
import java.util.List;

@Service
@AllArgsConstructor
public class RuleService {

    private final IRulesRepository rulesRepository;

    public List<Rule> findAll() {
        return rulesRepository.findAll();
    }
}
