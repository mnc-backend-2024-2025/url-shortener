package kz.mathncode.backend.entity.factory.faker;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ListUtilities {
    protected static <T> T randomElementFromList(List<T> list) {
        return list.get(ThreadLocalRandom.current().nextInt(0, list.size()));
    }
}
