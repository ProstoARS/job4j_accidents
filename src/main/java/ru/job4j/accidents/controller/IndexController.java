package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

@Controller
@AllArgsConstructor
public class IndexController {

    private final AccidentService accidentService;

    @GetMapping("/index")
    public String index(Model model) {
        Accident accident1 = Accident.builder()
                .id(1)
                .name("Иван Иванов")
                .text("Парковка в неположенном месте")
                .address("Ивановская 33")
                .build();
        Accident accident2 = Accident.builder()
                .id(2)
                .name("Незнайка")
                .text("Упал в канаву на автомобиле Винтика и Шпунтика")
                .address("Цветочная ул.")
                .build();
        accidentService.add(accident1);
        accidentService.add(accident2);
        model.addAttribute("user", "Arseny Sudakov");
        model.addAttribute("accidents", accidentService.findAll());
        return "index";
    }
}
