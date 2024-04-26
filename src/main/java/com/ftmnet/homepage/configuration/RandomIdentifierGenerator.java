package com.ftmnet.homepage.configuration;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;

public class RandomIdentifierGenerator implements IdentifierGenerator {

    /**
     * Represents 01/01/2024 00:00:00 GMT
     */
    public static final long APP_EPOCH = 1704067200L;

    private final Random rng = new SecureRandom();

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        //TODO add check to avoid collision
        long timestamp = System.currentTimeMillis() / 1000 - APP_EPOCH;
        long random = rng.nextLong(1 << 16);
        return (timestamp << 16) | random;
    }
}
