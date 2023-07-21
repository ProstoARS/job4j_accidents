package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.TypeRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TypeService {

    private final TypeRepository typeRepository;

    public void addType(AccidentType accidentType) {
        typeRepository.save(accidentType);
    }

    public List<AccidentType> findAll() {
        return (List<AccidentType>) typeRepository.findAll();
    }

    public Optional<AccidentType> findTypeById(int typeId) {
        return typeRepository.findById(typeId);
    }
}
