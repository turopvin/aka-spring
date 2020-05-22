package com.home.rudziak.core;

import com.home.rudziak.core.config.Config;
import com.home.rudziak.core.config.JavaConfigImpl;
import com.home.rudziak.core.configurator.ObjectConfigurator;
import com.home.rudziak.usecase.interfaces.Washer;
import com.home.rudziak.usecase.interfaces.impl.AutomatedWasher;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectFactory {
    private static ObjectFactory ourInstance = new ObjectFactory();
    private Config config;
    private List<ObjectConfigurator> configurators = new ArrayList<>();

    @SneakyThrows
    private ObjectFactory() {
        this.config = new JavaConfigImpl("com.home", new HashMap<>(Map.of(Washer.class, AutomatedWasher.class)));
        for (Class<? extends ObjectConfigurator> aClass : this.config.getAllImplClass(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    public static ObjectFactory getInstance() {
        return ourInstance;
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {

        final T t = implClass.getDeclaredConstructor().newInstance();

        configurators.forEach(c -> c.configure(t));

        return t;
    }

}
