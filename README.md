# Приложение "Автонарушители"

+ [О проекте](#О-проекте)
+ [Технологии](#Технологии)
+ [Требования к окружению](#Требования-к-окружению)
+ [Запуск проекта](#Запуск-проекта)

## О проекте

Web-приложение "Автонарушители".
В системе существуют две роли. Обычные пользователи и автоинспекторы.
Пользователь добавляет описание автонарушение.
В заявлении указывает: адрес, номер машины, описание нарушения и фотографию нарушения.
У заявки есть статус. Принята. Отклонена. Завершена.

Для хранения данных используется Hibernate.
Для входа в систему пользователю необходимо зарегистрироваться

## Технологии

+ **Maven 3.8.1**
+ **Spring Boot 2.7.5**
+ **HTML 5**, **BOOTSTRAP 5**, **Thymeleaf 3.0.15**
+ **Hibernate 6.2.3.**, **PostgreSQL 14**
+ **Тестирование:** **Liquibase 3.6.2**, **H2 2.1.214**, **AssertJ 3.24.2**
+ **Java 17**
+ **Checkstyle 3.1.2**

## Требования к окружению
+ **Java 17**
+ **Maven 3.8**
+ **PostgreSQL 14**
+ **Hibernate 5.6.11**

## Запуск проекта
Перед запуском проекта необходимо настроить подключение к БД в соответствии с параметрами,
указанными в src/main/resources/db.properties, или заменить на свои параметры.

Варианты запуска приложения:
1. Упаковать проект в jar архив (job4j_accidents/target/job4j_accidents-1.0.jar):
``` 
mvn package
``` 
Запустить приложение:
```
java -jar job4j_accidents-1.0.jar 
```
2. Запустить приложение:
```
mvn spring-boot:run
```