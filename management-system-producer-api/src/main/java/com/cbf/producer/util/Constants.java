package com.cbf.producer.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

    /**
     * RabbitMQ
     */

    public static final String EXCHANGE_NAME = "amq.direct";
    public static final String STATUS_QUEUE = "status";

    /**
     * Entities
     */
    public static final String PLAYER = "PLAYER";
    public static final String TEAM = "TEAM";
    public static final String TRANSFER = "TRANSFER";
    public static final String MATCH = "MATCH";
    public static final String TOURNAMENT = "TOURNAMENT";
}
