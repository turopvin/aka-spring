package com.home.rudziak.core.configurator.annotation_configurators;

import com.home.rudziak.core.configurator.ObjectConfigurator;
import com.home.rudziak.usecase.impl.InjectProperty;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class InjectPropertyAnnotationConfigurator implements ObjectConfigurator {

    private Map<String, String> propertiesMap;

    @SneakyThrows
    public InjectPropertyAnnotationConfigurator() {
        final String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
        this.propertiesMap = new BufferedReader(new FileReader(path))
                .lines()
                .map(s -> s.split("="))
                .collect(toMap(s1 -> s1[0], s2 -> s2[1]));
    }

    @SneakyThrows
    @Override
    public void configure(Object t) {
        final Class<?> implClass = t.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            final InjectProperty annotation = field.getAnnotation(InjectProperty.class);
            if (annotation != null) {
                String value = annotation.value().isEmpty() ? propertiesMap.get(field.getName()) : propertiesMap.get(annotation.value());
                field.setAccessible(true);
                field.set(t, value);
            }
        }
    }
}
