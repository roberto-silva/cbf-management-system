package com.cbf.producer.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class RequestUtil {

    public static URI toURI(Long entityId) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entityId).toUri();
    }
}
