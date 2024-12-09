package kz.mathncode.backed.entity.factory.faker;

import com.github.javafaker.Faker;
import kz.mathncode.backed.entity.Click;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class ClickFakerFactory extends FakerFactory<Click> {
    private List<UUID> resources;

    protected ClickFakerFactory(Faker faker, Instant minCreatedAtSecond, Instant maxCreatedAtSecond, List<UUID> resources) {
        super(faker, minCreatedAtSecond, maxCreatedAtSecond, Click.class);
        setResources(resources);
    }


    /**
     * @return random IPv4 address WITHOUT VALIDATION
     * */
    private String randomIp() {
        return String.format("%d.%d.%d.%d",
                ThreadLocalRandom.current().nextInt(0, 256),
                ThreadLocalRandom.current().nextInt(0, 256),
                ThreadLocalRandom.current().nextInt(0, 256),
                ThreadLocalRandom.current().nextInt(0, 256)
            );

    }

    @Override
    public Click produce() {
        var id = UUID.randomUUID();
        var resourceId = ListUtilities.randomElementFromList(resources);
        var ipAddress = randomIp();
        var createdAt = ZonedDateTime.ofInstant(randomInstant(), ZoneId.of("Asia/Almaty"));

        return new Click(
                id,
                resourceId,
                ipAddress,
                createdAt
        );
    }

    public List<UUID> getResources() {
        return resources;
    }

    public void setResources(List<UUID> resources) {
        if (resources.isEmpty()) {
            throw new RuntimeException("Cannot provide empty resources");
        }
        this.resources = resources;
    }
}
