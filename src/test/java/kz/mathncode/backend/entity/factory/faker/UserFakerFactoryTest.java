package kz.mathncode.backend.entity.factory.faker;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserFakerFactoryTest {
    private UserFakerFactory factory;

    @BeforeEach
    public void beforeEach() {
        var faker = new Faker();
        factory = new UserFakerFactory(faker);
    }

    @RepeatedTest(100)
    void produce() {
        var user = factory.produce();
        assertFalse(user.getFirstName().isBlank());
        assertFalse(user.getLastName().isBlank());
        assertFalse(user.getEmail().isBlank());
        assertFalse(user.getCreatedAt().isAfter(ZonedDateTime.now()));
    }
}