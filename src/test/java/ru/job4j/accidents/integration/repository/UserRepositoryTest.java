package ru.job4j.accidents.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.jdbc.Sql;
import ru.job4j.accidents.integration.annotation.IT;
import ru.job4j.accidents.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@Sql({
        "classpath:sql/data.sql"
})
@RequiredArgsConstructor
public class UserRepositoryTest {

    private final UserRepository userRepository;

    @Value("${profile.property.value}")
    private String propertyString;

    @Test
    void whenDevIsActiveThenValueShouldBeKeptFromApplicationYaml() {
        Assertions.assertEquals("This the the application-test.yaml file", propertyString);
    }

    @Test
    public void whenFindById() {
        var userRepositoryById = userRepository.findById(1);
        assertTrue(userRepositoryById.isPresent());
    }

}
