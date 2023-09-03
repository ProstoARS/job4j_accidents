INSERT INTO accidents (id, name, text, address, type_id)
VALUES (1, 'Иван Иванов', 'Нарушение1', 'Ивановская 2', 1),
       (2, 'Света Светикова', 'Нарушение 2', 'Светлановская площадь', 2),
       (3, 'Пётр Петров', 'Нарушение 3', 'Петровская набережная', 3);

INSERT INTO accident_rule (id, accident_id, rule_id)
VALUES (1, (SELECT id FROM accidents WHERE name = 'Иван Иванов'), 1),
       (2, (SELECT id FROM accidents WHERE name = 'Света Светикова'), 2),
       (3, (SELECT id FROM accidents WHERE name = 'Пётр Петров'), 3);