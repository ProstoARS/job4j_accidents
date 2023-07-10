package ru.job4j.accidents.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Accident {

    @EqualsAndHashCode.Include
    private int id;
    private String name;
    private String text;
    private String address;
}
