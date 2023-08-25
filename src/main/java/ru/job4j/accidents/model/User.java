package ru.job4j.accidents.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String password;

    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authority_id")
    private Authority authority;

    private boolean enabled;
}
