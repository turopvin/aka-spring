package com.home.rudziak.core;

import com.home.rudziak.core.config.Config;
import com.home.rudziak.core.config.JavaConfigImpl;
import com.home.rudziak.usecase.Washer;
import com.home.rudziak.usecase.impl.AutomatedWasher;
import com.home.rudziak.usecase.impl.InjectProperty;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ObjectFactory {
    private static ObjectFactory ourInstance = new ObjectFactory();
    private Config config;

    private ObjectFactory() {
        config = new JavaConfigImpl("com.home", new HashMap<>(Map.of(Washer.class, AutomatedWasher.class)));
    }

    public static ObjectFactory getInstance() {
        return ourInstance;
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }
        final T t = implClass.getDeclaredConstructor().newInstance();

        for (Field field : implClass.getDeclaredFields()) {
            final InjectProperty annotation = field.getAnnotation(InjectProperty.class);
            final String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
            final Map<String, String> propertiesMap = new BufferedReader(new FileReader(path))
                    .lines()
                    .map(s -> s.split("="))
                    .collect(toMap(s1 -> s1[0], s2 -> s2[1]));

            if (annotation != null) {
                String value = annotation.value().isEmpty() ? propertiesMap.get(field.getName()) : propertiesMap.get(annotation.value());
                field.setAccessible(true);
                field.set(t, value);
            }
        }


        return t;
    }

}
