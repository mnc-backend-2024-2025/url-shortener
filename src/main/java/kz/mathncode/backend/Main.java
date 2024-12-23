package kz.mathncode.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import kz.mathncode.backend.entity.factory.faker.UserFakerFactory;


public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        var userFactory = new UserFakerFactory(new Faker());
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        var user = userFactory.produce();

        System.out.println(mapper.writeValueAsString(user));
    }
}