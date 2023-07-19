package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.ITypeRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TypeService {

    private final ITypeRepository typeRepository;

    public void addType(AccidentType accidentType) {
        typeRepository.addType(accidentType);
    }

    public List<AccidentType> findAll() {
        return typeRepository.findAll();
    }

    public Optional<AccidentType> findTypeById(int typeId) {
        return typeRepository.findTypeById(typeId);
    }
}
