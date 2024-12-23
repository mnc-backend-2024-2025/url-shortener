# Структура проекта

```shell
tree -A -L 2 -I 'target  .
```
```
.
├── Taskfile.yml        <-- Файл, хранящий SHELL-команды для удобства, аналог Makefile
├── docker-compose.yml  <-- Docker-compose для быстрого деплоя базы данных
├── pom.xml             <-- Главный файл, хранящий зависимости для сборки продуктов
├── readme.md           <-- Описание репозитория
└── src
    ├── main            <-- Главная директория для .java файлов
    └── test            <-- Директория для тестов

```

Если вы пользетесь IntelliJ IDEA или подобной IDE, и развернули PostgreSQL локально, без использования Docker -- можно
сосредоточиться только на следующих файлах:

- `pom.xml`
- `src/main` и файлы внутри
- `src/test` и файлы внутри 

# Lesson 1

## Задача

Создать базу данных с тестовыми данными, которые будут сгенерированы при помощи программы на Java. Конкретно, создать 
150 объектов класса `User` и сохранить их в базу данных.

Для вас уже написаны следующие сущности:

- `User`: Класс описывающий пользователя нашей системы, способного создавать короткие ссылки
- `URLResource`: Класс, описывающий укороченную ссылку внутри нашей системы
- `Click`: Класс описывающий событие перехода по данной ссылке.

Кроме этого, для вашего удобства, были написанны следующие классы для того, чтобы не генерировать вручную данные для объектов:

- `ClickFakerFactory`
- `URLResourceFakerFactory`
- `UserFakerFactory`

Каждый из этих классов способен генерировать случайные данные для ваших объектов, чтобы вам было легче тестировать программу.
Все из этих классов являются реализацией интерфейса `EntityFactory` и содержат в себе метод `produce()` который может создать
для вас случайный объект. 

```java
public interface EntityFactory<T> {
    Class<T> getEntityClass();
    T produce();
}
```

Пример использования:

```java
public class Main{
    public static void main(String[] args) {
        UserFakerFactory factory = new UserFakerFactory(new Faker());
        User user = factory.produce(); // Создает для вас случайного пользователя
    }
}
```

Также, для вашего удобства, был написан `UserDAO` класс, который отвечает за взаимодействие с базой данных

```java
public class UserDAO extends AbstractDAO<User, UUID>{
    public UserDAO(Connection connection) {
        super(connection, User.class, UUID.class);
    }

    @Override
    public void create(User entity) throws SQLException {
        var queryFormat = """
                INSERT INTO "user" ("id", "firstName", "lastName", "email", "createdAt")
                VALUES ('%s', '%s', '%s', '%s', %d);
                """;
        var query = String.format(queryFormat,
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getCreatedAt().toInstant().getEpochSecond()
        );

        var statement = getConnection().createStatement();
        statement.executeUpdate(query);
    }

    @Override
    public User readById(UUID uuid) throws SQLException {
        var queryFormat = """
                SELECT "id", "firstName", "lastName", "email", "createdAt"
                FROM "user"
                WHERE id = '%s';
                """;
        var query = String.format(queryFormat, uuid);
        var statement = getConnection().createStatement();
        var resultSet = statement.executeQuery(query);

        resultSet.next();
        var id = resultSet.getString("id");
        var firstName = resultSet.getString("firstName");
        var lastName = resultSet.getString("lastName");
        var email = resultSet.getString("email");
        var createdAtTS = resultSet.getInt("createdAt");
        var createdAt = ZonedDateTime.ofInstant(Instant.ofEpochSecond(createdAtTS), ZoneId.of("Asia/Almaty"));

        return new User(UUID.fromString(id), firstName, lastName, email, createdAt);

    }

    @Override
    public void update(User entity, UUID uuid) throws SQLException {
        var queryFormat = """
                UPDATE "user"
                SET "firstName" = '%s', "lastName" = '%s', "email" = '%s', "createdAt" = '%s'
                WHERE "id" = '%s';
                """;
        var query = String.format(queryFormat,
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getCreatedAt().toInstant().getEpochSecond(),
                entity.getId()
        );

        var statement = getConnection().createStatement();
        statement.executeUpdate(query);
    }

    @Override
    public void deleteById(UUID uuid) throws SQLException {
        var queryFormat = """
                DELETE FROM "user"
                WHERE "id" = '%s';
                """;
        var query = String.format(queryFormat, uuid);
        var statement = getConnection().createStatement();
        statement.executeUpdate(query);
    }

    @Override
    public void createTableIfNotExists() throws SQLException {
        var query = """
                CREATE TABLE IF NOT EXISTS "user" (
                    "id" varchar(38) PRIMARY KEY,
                    "firstName" varchar(100) NOT NULL,
                    "lastName" varchar(100) NOT NULL,
                    "email" varchar(250) NOT NULL,
                    "createdAt" INTEGER NOT NULL
                );
                """;
        var statement = getConnection().createStatement();
        statement.executeUpdate(query);
    }
}
```

> Данная реализация использует JDBC API и кое-где страдает от нехватки экранирования. На этапе обучения
> было принято решение игнорировать эти ограничения.

## Решение

Обратите внимание, что в `pom.xml` у вас следующая зависимость:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.7.4</version>
</dependency>
```
Это -- реализация драйвера для postgresql. Если бы вы использовали другую базу, то нужна была бы другая зависимость,
найти которую можно на сайте СУБД.

```java
public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/url-shortener?sslmode=disable"; // Исходим из того, что база поднята локально
        Connection connection = DriverManager.getConnection(url, "postgres", "admin"); // Здесь должны быть актуальные креды
        /* Код выше упадет с ошибкой, если у вас нет драйвера и/или база находится на другом адресе или с другими кредами */
        
        /* 
        Какую бы базу данных вы не использовали, код ниже будет работать без изменений для всех, пока 
        она реляционная (поддерживает SQL) и вы смогли установить соединение к базе без ошибок
         */
        UserDAO dao = new UserDAO(connection);
        UserFakerFactory factory = new UserFakerFactory(new Faker());

        dao.createTableIfNotExists();
        
        for (int i = 0; i < 150; i++) {
            dao.create(factory.produce());
        }
    }
}
```

# Lesson 2

Создать свою реализацию DAO для оставшихся классов.

