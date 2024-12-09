package kz.mathncode.backed.entity.factory.faker;

import com.github.javafaker.Faker;
import kz.mathncode.backed.entity.URLResource;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import static kz.mathncode.backed.entity.factory.faker.ListUtilities.randomElementFromList;

public class URLResourceFakerFactory extends FakerFactory<URLResource> {
    private List<UUID> userUUIDs;
    private List<String> fromAddresses;
    private List<String> toAddresses;

    public URLResourceFakerFactory(
            Faker faker,
            Instant minCreatedAtSecond,
            Instant maxCreatedAtSecond,
            List<UUID> userUUIDs,
            List<String> fromAddresses,
            List<String> toAddresses
    ) {
        super(faker, minCreatedAtSecond, maxCreatedAtSecond, URLResource.class);
        setUserUUIDs(userUUIDs);
        setFromAddresses(fromAddresses);
        setToAddresses(toAddresses);
    }

    public URLResourceFakerFactory(
            Faker faker,
            List<UUID> userUUIDs,
            List<String> fromAddresses,
            List<String> toAddresses
    ) {
        this(faker, Instant.now().minus(7, ChronoUnit.DAYS), Instant.now(), userUUIDs, fromAddresses, toAddresses);
    }

    @Override
    public URLResource produce() {
        var id = UUID.randomUUID().toString().substring(32 - 8);
        var fromAddress = randomElementFromList(fromAddresses);
        var toAddress = randomElementFromList(toAddresses);
        var createdBy = randomElementFromList(userUUIDs);
        var createdAt = ZonedDateTime.ofInstant(randomInstant(), ZoneId.of("Asia/Almaty"));

        return new URLResource(
                id,
                fromAddress,
                toAddress,
                createdBy,
                createdAt
        );
    }

    public List<UUID> getUserUUIDs() {
        return userUUIDs;
    }

    public void setUserUUIDs(List<UUID> userUUIDs) {
        if (userUUIDs.isEmpty()) {
            throw new RuntimeException("Cannot provide empty list of User UUIDs");
        }
        this.userUUIDs = userUUIDs;
    }

    public List<String> getFromAddresses() {
        return fromAddresses;
    }

    public void setFromAddresses(List<String> fromAddresses) {
        if (fromAddresses.isEmpty()) {
            throw new RuntimeException("Cannot provide empty list of from URL addresses");
        }
        this.fromAddresses = fromAddresses;
    }

    public List<String> getToAddresses() {
        return toAddresses;
    }

    public void setToAddresses(List<String> toAddresses) {
        if (fromAddresses.isEmpty()) {
            throw new RuntimeException("Cannot provide empty list of target URL addresses");
        }
        this.toAddresses = toAddresses;
    }
}
