package ru.job4j.accidents.integration.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.integration.annotation.IT;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IT
@RequiredArgsConstructor
@AutoConfigureMockMvc
class AccidentControllerTest {

    private final MockMvc mockMvc;

    @Test
    void whenCreateAccidentView() throws Exception {
        mockMvc.perform(get("/createAccident"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("createAccident"))
                .andExpect(model().attributeExists("rules"));
    }

    @Test
    void whenEditAccidentException() throws Exception {
        mockMvc.perform(get("/editAccident")
                        .param("id", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/error"));
    }
}