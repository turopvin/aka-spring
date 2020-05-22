package com.home.rudziak.core;

import com.home.rudziak.core.configurator.ObjectConfigurator;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class ObjectFactory {
    private final ApplicationContext context;
    private List<ObjectConfigurator> configurators = new ArrayList<>();

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getAllImplClass(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {

        final T t = implClass.getDeclaredConstructor().newInstance();

        configurators.forEach(c -> c.configure(t, context));

        return t;
    }

}
