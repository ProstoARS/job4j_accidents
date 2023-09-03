package ru.job4j.accidents.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import ru.job4j.accidents.integration.annotation.IT;
import ru.job4j.accidents.repository.AccidentRepository;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@Sql({
        "classpath:sql/data.sql"
})
@RequiredArgsConstructor
public class AccidentRepositoryTest {

    private final AccidentRepository accidentRepository;

    @Test
    public void whenFindAccidentById() {
        var accident = accidentRepository.findById(1);
        assertTrue(accident.isPresent());
    }
}
