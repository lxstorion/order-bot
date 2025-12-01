package com.tensei.order_bot.utils;

import com.tensei.order_bot.exception.ResourceException;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class ResourceUtil {

    public static <T extends Resource> String getResourceContentAsString(T resource) throws ResourceException {

        try {
            return resource.getContentAsString(StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ResourceException("Error occurred due loading resource", e);
        }

    }

    private ResourceUtil() {}

}
