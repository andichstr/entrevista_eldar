package com.example.demo.utils;

import java.security.SecureRandom;
import java.util.Objects;

public class UUIDGenerator {

    private static SecureRandom generator = null;
    private static final long MSB = 0x8000000000000000L;

    private UUIDGenerator() {
        throw new IllegalStateException("Utility class");
    }

    public static String generate() {
        SecureRandom g = generator;
        if (Objects.isNull(g)) {
            generator = g = new SecureRandom();
        }

        return Long.toHexString(MSB | g.nextLong()) + Long.toHexString(MSB | g.nextLong());
    }
}
