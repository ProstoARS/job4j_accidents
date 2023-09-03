package ru.job4j.accidents.integration.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.integration.annotation.IT;
import ru.job4j.accidents.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IT
@RequiredArgsConstructor
@AutoConfigureMockMvc
class RegControlTest {

    private final MockMvc mockMvc;

    private final UserRepository userRepository;

    @Test
    public void whenRegistrationView() throws Exception {
        mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("reg"));
    }

    @Test
    @WithAnonymousUser
    void whenRegistrationUser() throws Exception {
        mockMvc.perform(post("/reg")
                .param("username", "test")
                .param("password", "test")
        )
                .andDo(print())
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrl("/login"));
        var user = userRepository.findByUsername("test");
        assertTrue(user.isPresent());
    }
}