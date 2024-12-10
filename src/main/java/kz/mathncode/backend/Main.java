package kz.mathncode.backend;

import com.github.javafaker.Faker;
import kz.mathncode.backend.entity.factory.faker.UserFakerFactory;

public class Main {
    public static void main(String[] args) {
        var faker = new Faker();
        var factory = new UserFakerFactory(faker);

        var user = factory.produce();
        System.out.println(user);
    }
}