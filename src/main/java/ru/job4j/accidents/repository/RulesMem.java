package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RulesMem {

    private final AtomicInteger id = new AtomicInteger();
    private final Map<Integer, Rule> rulesMap = new ConcurrentHashMap<>();

    public RulesMem() {
        Rule rule = Rule.builder()
                .id(id.incrementAndGet())
                .name("Статья 1")
                .build();
        rulesMap.put(rule.getId(), rule);
        Rule rule1 = Rule.builder()
                .id(id.incrementAndGet())
                .name("Статья 2")
                .build();
        rulesMap.put(rule1.getId(), rule1);
        Rule rule2 = Rule.builder()
                .id(id.incrementAndGet())
                .name("Статья 3")
                .build();
        rulesMap.put(rule2.getId(), rule2);
    }

    public List<Rule> findAll() {
        return new ArrayList<>(rulesMap.values());
    }

    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(rulesMap.get(id));
    }
}
