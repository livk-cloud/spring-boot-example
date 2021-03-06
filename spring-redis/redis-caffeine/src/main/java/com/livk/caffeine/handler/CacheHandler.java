package com.livk.caffeine.handler;

/**
 * <p>
 * CacheHandler
 * </p>
 *
 * @author livk
 * @date 2022/3/30
 */
public interface CacheHandler<T> extends CacheReadHandler<T>, CacheWriteHandler<T> {

    default T readAndPut(String key, T defaultValue, long timeout) {
        T t = this.read(key);
        if (t == null) {
            t = defaultValue;
            this.put(key, t, timeout);
        }
        return t;
    }

}
