package com.cbf.producer.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

    /**
     * RabbitMQ
     */

    public static final String EXCHANGE_NAME = "amq.direct";
    public static final String START_QUEUE = "start";
    public static final String GOAL_QUEUE = "goal";
    public static final String BREAK_QUEUE = "break";
    public static final String ADDITION_QUEUE = "addition";
    public static final String REPLACEMENT_QUEUE = "replacement";
    public static final String WARNING_QUEUE = "warning";
    public static final String THE_END_QUEUE = "the_end";

    /**
     * Entities
     */
    public static final String PLAYER = "PLAYER";
    public static final String TEAM = "TEAM";
    public static final String TRANSFER = "TRANSFER";
    public static final String MATCH = "MATCH";
    public static final String TOURNAMENT = "TOURNAMENT";
}
