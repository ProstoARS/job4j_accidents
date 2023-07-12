package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.TypeService;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidents;
    private final TypeService typeService;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("accident", new Accident());
        List<AccidentType> types = typeService.findAllTypes();
        model.addAttribute("types", types);
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        int typeId = accident.getType().getId();
        accident.setType(typeService.findTypeById(typeId).get());
        accidents.add(accident);
        return "redirect:/index";
    }

    @GetMapping("/editAccident")
    public String viewEditAccident(@RequestParam("id") int accidentId, Model model) {
        Optional<Accident> accidentsById = accidents.findAccidentsById(accidentId);
        if (accidentsById.isEmpty()) {
            return "redirect:/error";
        }
        Accident accident = accidentsById.get();
        model.addAttribute("accident", accident);
        List<AccidentType> types = typeService.findAllTypes();
        model.addAttribute("types", types);
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident) {
        int typeId = accident.getType().getId();
        accident.setType(typeService.findTypeById(typeId).get());
        accidents.update(accident);
        return "redirect:/index";
    }
}