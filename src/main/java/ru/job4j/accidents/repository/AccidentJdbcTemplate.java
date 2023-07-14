package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Profile("jdbc")
public class AccidentJdbcTemplate implements IAccidentRepository {

    private final JdbcTemplate jdbc;

    @Override
    public List<Accident> findAll() {
        return jdbc.query("select * from accidents",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    return accident;
                });
    }

    @Override
    public void add(Accident accident) {
        jdbc.update("insert into accidents (name, text, address) values (?, ?, ?)",
                accident.getName(),
                accident.getText(),
                accident.getAddress());
    }

    @Override
    public Optional<Accident> findAccidentById(int accidentId) {
        return Optional.ofNullable(jdbc.queryForObject("select * from accidents where id = ?",
                new Object[] {accidentId},
                (rs, rowNum) -> Accident.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .text(rs.getString("text"))
                        .address(rs.getString("address"))
                        .build()));
    }

    @Override
    public void update(Accident accident) {
        jdbc.update("update accidents set name = ?, text = ?, address = ? where id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getId());
    }
}
