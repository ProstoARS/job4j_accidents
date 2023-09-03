package ru.job4j.accidents.integration.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.integration.annotation.IT;
import ru.job4j.accidents.repository.AccidentRepository;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IT
@RequiredArgsConstructor
@AutoConfigureMockMvc
class AccidentControllerTest {

    private final MockMvc mockMvc;

    private final AccidentRepository accidentRepository;


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

    @Test
    void whenCreateAccident() throws Exception {
        mockMvc.perform(post("/saveAccident")
                .param("name", "forTest")
                .param("text", "test")
                .param("address", "test")
                .param("type.id", "1")
                .param("rIds", "1")
        )
                .andDo(print())
                .andExpectAll(status().is3xxRedirection(),
                redirectedUrl("/index"));
        var accident = accidentRepository.findByName("forTest");
        assertTrue(accident.isPresent());
        accidentRepository.delete(accident.get());
        assertTrue(accidentRepository.findByName("forTest").isEmpty());
    }

    @Test
    void whenUpdateAccident() throws Exception {
        mockMvc.perform(post("/updateAccident")
                .param("name", "forTest")
                .param("text", "test")
                .param("address", "test")
                .param("type.id", "1")
                .param("rIds", "1")
        )
                .andDo(print())
                .andExpectAll(status().is3xxRedirection(),
                redirectedUrl("/index"));
        var accident = accidentRepository.findByName("forTest");
        assertTrue(accident.isPresent());
        accidentRepository.delete(accident.get());
        assertTrue(accidentRepository.findByName("forTest").isEmpty());
    }


}