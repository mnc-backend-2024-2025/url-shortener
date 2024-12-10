package kz.mathncode.backend.entity.factory.faker;

import com.github.javafaker.Faker;
import kz.mathncode.backend.entity.factory.EntityFactory;

import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

public abstract class FakerFactory<T> implements EntityFactory<T> {
    private final Faker faker;
    private final Instant minCreatedAtSecond;
    private final Instant maxCreatedAtSecond;
    private final Class<T> entityClass;

    protected FakerFactory(Faker faker, Instant minCreatedAtSecond, Instant maxCreatedAtSecond, Class<T> entityClass) {
        this.faker = faker;
        this.minCreatedAtSecond = minCreatedAtSecond;
        this.maxCreatedAtSecond = maxCreatedAtSecond;
        this.entityClass = entityClass;
    }

    public Faker getFaker() {
        return faker;
    }

    public Instant getMinCreatedAtSecond() {
        return minCreatedAtSecond;
    }

    public Instant getMaxCreatedAtSecond() {
        return maxCreatedAtSecond;
    }

    public Instant randomInstant() {
        return Instant.ofEpochSecond(
                ThreadLocalRandom.current().nextLong(minCreatedAtSecond.getEpochSecond(), maxCreatedAtSecond.getEpochSecond())
        );
    }

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }
}
