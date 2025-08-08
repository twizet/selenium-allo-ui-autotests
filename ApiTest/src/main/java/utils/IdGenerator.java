package utils;

import java.util.concurrent.ThreadLocalRandom;

public class IdGenerator {
    public static long generateId() {
        return ThreadLocalRandom.current().nextLong(1000, 999999);
    }
}
