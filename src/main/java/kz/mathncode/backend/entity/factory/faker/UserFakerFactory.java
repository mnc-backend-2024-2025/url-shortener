package kz.mathncode.backend.entity.factory.faker;

import com.github.javafaker.Faker;
import kz.mathncode.backend.entity.User;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class UserFakerFactory extends FakerFactory<User> {
    public UserFakerFactory(Faker faker) {
        super(faker, Instant.now().minus(7, ChronoUnit.DAYS), Instant.now(), User.class);
    }

    public UserFakerFactory(Faker faker, Instant minCreatedAtSecond, Instant maxCreatedAtSecond) {
        super(faker, minCreatedAtSecond, maxCreatedAtSecond, User.class);
    }

    @Override
    public User produce() {
        var id = UUID.randomUUID();
        var firstName = getFaker().name().firstName();
        var lastName = getFaker().name().lastName();
        var email = String.format("%s.%s@gmail.com", firstName.toLowerCase(), lastName.toLowerCase());

        var createdAtSecond = getFaker().number().numberBetween(
                getMinCreatedAtSecond().getEpochSecond(),
                getMaxCreatedAtSecond().getEpochSecond()
        );

        var createdAt = ZonedDateTime.ofInstant(
                Instant.ofEpochSecond(createdAtSecond),
                ZoneId.of("Asia/Almaty")
        );

        return new User(id, firstName, lastName, email, createdAt);
    }
}
