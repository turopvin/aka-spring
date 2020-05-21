package com.home.rudziak.core;

import com.home.rudziak.core.config.Config;
import com.home.rudziak.core.config.JavaConfigImpl;
import com.home.rudziak.usecase.Washer;
import com.home.rudziak.usecase.impl.HandWasher;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

public class ObjectFactory {
    private static ObjectFactory ourInstance = new ObjectFactory();
    private Config config;

    public static ObjectFactory getInstance() {
        return ourInstance;
    }

    private ObjectFactory() {
        config = new JavaConfigImpl("com.home", new HashMap<>(Map.of(Washer.class, HandWasher.class)));
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }
        final T t = implClass.getDeclaredConstructor().newInstance();



        return t;
    }

}
