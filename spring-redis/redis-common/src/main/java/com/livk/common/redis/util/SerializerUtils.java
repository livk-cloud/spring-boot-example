package com.livk.common.redis.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * <p>
 * SerializerUtils
 * </p>
 *
 * @author livk
 * @date 2022/4/1
 */
@UtilityClass
public class SerializerUtils {

    public <T> RedisSerializer<T> getSerializer(Class<T> tClass, ObjectMapper mapper) {
        var serializer = new Jackson2JsonRedisSerializer<>(tClass);
        serializer.setObjectMapper(mapper);
        return serializer;
    }

    public <T> RedisSerializer<T> getSerializer(Class<T> tClass) {
        var mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        return getSerializer(tClass, mapper);
    }

    public RedisSerializer<Object> getDefaultSerializer() {
        var mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        return getSerializer(Object.class, mapper);
    }

}
