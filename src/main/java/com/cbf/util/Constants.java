package com.cbf.util;

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

}
