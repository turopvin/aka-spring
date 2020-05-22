package com.home.rudziak.core;

import com.home.rudziak.core.config.Config;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    @Setter
    private ObjectFactory factory;
    private Map<Class, Object> container = new ConcurrentHashMap<>();
    @Getter
    private Config config;

    public ApplicationContext(Config config) {
        this.config = config;
    }

    public <T> T getObject(Class<T> type) {
        if (container.containsKey(type)) {
            return (T) container.get(type);
        }

        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }

        final T t = factory.createObject(implClass);
        container.put(type, t);

        return t;
    }
}
