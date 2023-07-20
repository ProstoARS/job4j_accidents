package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository
@AllArgsConstructor
@Profile("jdbc")
public class AccidentJdbcTemplate implements IAccidentRepository {



    private static final String INSERT_INTO_ACCIDENTS = """
            INSERT INTO accidents (name, text, address, type_id)
            VALUES (?, ?, ?, ?)
            """;

    private static final String INSERT_INTO_ACCIDENT_RULE = """
            INSERT INTO accident_rule (accident_id, rule_id)
            VALUES (?, ?)
            """;

    private static final String FIND_ALL = """
            SELECT a.id, a.name, a.text, a.address,
            a.type_id, t.name
            FROM accidents a
            JOIN type t ON a.type_id = t.id
            """;

    private static final String SELECT_RULES_BY_ACCIDENT_ID = """
            SELECT r.id, r.name
            FROM accident_rule ar INNER JOIN rules r ON ar.rule_id = r.id
            WHERE ar.accident_id = ?
            """;

    private static final String UPDATE_ACCIDENT = """
            UPDATE accidents SET name = ?, text = ?, address = ?, type_id = ?
            WHERE id = ?
            """;

    private static final String FIND_ACCIDENT_BY_ID = """
            SELECT a.name, a.text, a.address, a.type_id, t.name FROM accidents a
            JOIN type t on t.id = a.type_id
            WHERE a.id = ?
            """;

    private static final String DELETE_RULE_FROM_ACCIDENT_RULE = """
            DELETE FROM accident_rule
            WHERE accident_id = ? AND rule_id = ?
            """;


    private final JdbcTemplate jdbc;

    @Override
    public List<Accident> findAll() {
       List<Accident> all = jdbc.query(FIND_ALL,
                (rs, rowNum) -> Accident.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .text(rs.getString(3))
                        .address(rs.getString(4))
                        .type(AccidentType.builder().id(rs.getInt(5))
                                .name(rs.getString(6)).build())
                        .build());
        all.forEach(accident -> accident.setRules(
                findAllRulesByAccidentId(accident.getId())));
        return all;
    }

    @Override
    public void add(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(INSERT_INTO_ACCIDENTS, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        List<Integer> rulesId = accident.getRules().stream().map(Rule::getId).toList();
        rulesId.forEach(rId -> jdbc.update(INSERT_INTO_ACCIDENT_RULE,
                Objects.requireNonNull(keyHolder.getKeys()).get("id"), rId));
    }

    @Override
    public Optional<Accident> findAccidentById(int accidentId) {
        return Optional.ofNullable(jdbc.queryForObject(FIND_ACCIDENT_BY_ID,
                (rs, rowNum) -> Accident.builder()
                        .id(accidentId)
                        .name(rs.getString(1))
                        .text(rs.getString(2))
                        .address(rs.getString(3))
                        .type(AccidentType.builder()
                                .id(rs.getInt(4))
                                .name(rs.getString(5))
                                .build())
                        .rules(findAllRulesByAccidentId(accidentId))
                        .build(),
                accidentId));
    }

    @Override
    public void update(Accident accident) {
        jdbc.update(UPDATE_ACCIDENT,
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        Set<Rule> rulesForUpdate = accident.getRules();
        Set<Rule> rulesFromBase = findAllRulesByAccidentId(accident.getId());
        accident.setRules(updateRules(rulesFromBase, rulesForUpdate, accident.getId()));
    }

    private Set<Rule> updateRules(Set<Rule> rulesFromBase, Set<Rule> rulesForUpdate, int accidentId) {
        Set<Rule> rules = new HashSet<>();
        rules.addAll(rulesFromBase);
        rules.addAll(rulesForUpdate);
        rules.forEach(rule -> {
            if (!rulesForUpdate.contains(rule)) {
                jdbc.update(DELETE_RULE_FROM_ACCIDENT_RULE, accidentId, rule.getId());
            }
            if (!rulesFromBase.contains(rule)) {
                jdbc.update(INSERT_INTO_ACCIDENT_RULE, accidentId, rule.getId());
            }
        });
        rules.retainAll(rulesForUpdate);
        return rules;
    }

    public Set<Rule> findAllRulesByAccidentId(int accidentId) {
        Set<Rule> rules = new HashSet<>();
        jdbc.query(SELECT_RULES_BY_ACCIDENT_ID,
                (rs, rowNum) -> {
                    Rule rule = Rule.builder()
                            .id(rs.getInt(1))
                            .name(rs.getString(2))
                            .build();
                    rules.add(rule);
                    return rule;
                }, accidentId);
        return rules;
    }
}
