package ru.job4j.accidents.integration.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.integration.annotation.IT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@IT
@RequiredArgsConstructor
@AutoConfigureMockMvc
class RegControlTest {

    private final MockMvc mockMvc;

    @Test
    public void whenRegistrationView() throws Exception {
        mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("reg"));
    }
}