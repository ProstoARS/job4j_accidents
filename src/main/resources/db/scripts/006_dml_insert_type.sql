--liquibase formatted sql

--changeset arsudakov:1
INSERT INTO type (name)
VALUES ('Машина');
INSERT INTO type (name)
VALUES ('Машина и машина');
INSERT INTO type (name)
VALUES ('Машина и велосипед');
INSERT INTO type (name)
VALUES ('Машина и пешиход');


