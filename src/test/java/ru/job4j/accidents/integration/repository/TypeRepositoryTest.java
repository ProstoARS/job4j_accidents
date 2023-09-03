package ru.job4j.accidents.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.job4j.accidents.integration.annotation.IT;
import ru.job4j.accidents.repository.TypeRepository;
import static org.assertj.core.api.Assertions.*;

@IT
@RequiredArgsConstructor
class TypeRepositoryTest {

    private final TypeRepository typeRepository;

    @Test
    public void whenFindAllType() {
        var all = typeRepository.findAllByOrderById();
        assertThat(all).hasSize(4);
    }

}