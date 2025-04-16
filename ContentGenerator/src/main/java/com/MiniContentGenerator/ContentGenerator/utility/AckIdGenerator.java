package com.MiniContentGenerator.ContentGenerator.utility;

import java.util.UUID;

public class AckIdGenerator {
    public static String generateAckId() {
        return "ACK-" + UUID.randomUUID().toString() + "-" + System.currentTimeMillis();
    }
}
