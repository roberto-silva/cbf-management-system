package com.cbf.consumer.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {


    /**
     * RabbitMQ
     */

    public static final String EXCHANGE_NAME = "amq.direct";

    /**
     * Entities
     */
    public static final String PLAYER = "PLAYER";
    public static final String TEAM = "TEAM";
    public static final String TRANSFER = "TRANSFER";
    public static final String MATCH = "MATCH";
    public static final String TOURNAMENT = "TOURNAMENT";


    /**
     * Player Events Socket
     */

    public static final String SAVE_PLAYER = "SAVE_PLAYER";


    /**
     * Team Events Socket
     */

    public static final String SAVE_TEAM = "SAVE_TEAM";


    /**
     * Transfer Events Socket
     */

    public static final String SAVE_TRANSFER = "SAVE_TRANSFER";


    /**
     * Socket
     */
    public static final String SOCKET = "/socket";
    public static final String[] SOCKET_CHANEL = {SAVE_PLAYER, SAVE_TEAM, SAVE_TRANSFER};

}
