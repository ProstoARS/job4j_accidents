package ru.job4j.accidents.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.job4j.accidents.integration.annotation.IT;
import ru.job4j.accidents.repository.RulesRepository;

import static org.assertj.core.api.Assertions.*;

@IT
@RequiredArgsConstructor
class RulesRepositoryTest {

    private final RulesRepository rulesRepository;

    @Test
    public void whenFindAllRules() {
        var all = rulesRepository.findAllByOrderById();
        assertThat(all).hasSize(3);
    }

}