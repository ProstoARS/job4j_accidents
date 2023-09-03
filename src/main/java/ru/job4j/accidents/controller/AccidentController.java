package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.RuleService;
import ru.job4j.accidents.service.TypeService;

import java.util.Optional;
import java.util.Set;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidents;
    private final TypeService typeService;
    private final RuleService ruleService;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("accident", new Accident());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, @RequestParam("rIds") int[] rIds) {
        accidents.add(rIds, accident);
        return "redirect:/index";
    }

    @GetMapping("/editAccident")
    public String viewEditAccident(@RequestParam("id") int accidentId, Model model) {
        Optional<Accident> accidentsById = accidents.findAccidentById(accidentId);
        if (accidentsById.isEmpty()) {
            return "redirect:/error";
        }
        Accident accident = accidentsById.get();
        model.addAttribute("accident", accident);
        Set<Rule> rules = accident.getRules();
        model.addAttribute("accidentRules", rules);
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident, @RequestParam("rIds") int[] rIds) {
        accidents.update(rIds, accident);
        return "redirect:/index";
    }
}