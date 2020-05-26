package com.home.rudziak.core.configurator.object_configurators;

import com.home.rudziak.core.ApplicationContext;
import com.home.rudziak.core.configurator.ObjectConfigurator;
import com.home.rudziak.core.configurator.annotations.Repository;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class RepositoryAnnotationObjectConfigurator implements ObjectConfigurator {

    private static String DATABASE = "aka.database.";
    private static String URL = "url";
    private static String USERNAME = "username";
    private static String PASSWORD = "password";

    private Map<String, String> propertiesMap;

    @SneakyThrows
    public RepositoryAnnotationObjectConfigurator() {
        final String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
        this.propertiesMap = new BufferedReader(new FileReader(path))
                .lines()
                .map(s -> s.split("="))
                .collect(toMap(s1 -> s1[0], s2 -> s2[1]));
    }

    @SneakyThrows
    @Override
    public void configure(Object t, ApplicationContext context) {
        final Class<?> aClass = t.getClass();
        if (aClass.isAnnotationPresent(Repository.class)) {
            final Field connection = aClass.getDeclaredField(URL);
            connection.setAccessible(true);
            connection.set(t, propertiesMap.get(DATABASE.concat(URL)));

            final Field username = aClass.getDeclaredField(USERNAME);
            username.setAccessible(true);
            username.set(t, propertiesMap.get(DATABASE.concat(USERNAME)));

            final Field password = aClass.getDeclaredField(PASSWORD);
            password.setAccessible(true);
            password.set(t, propertiesMap.get(DATABASE.concat(PASSWORD)));
        }
    }
}
