package com.home.rudziak.core.configurator.object_configurators;

import com.home.rudziak.core.ApplicationContext;
import com.home.rudziak.core.configurator.ObjectConfigurator;
import com.home.rudziak.core.configurator.annotations.InjectByType;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator {

    @SneakyThrows
    @Override
    public void configure(Object t, ApplicationContext context) {
        final Class<?> aClass = t.getClass();
        for (Field field : aClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectByType.class)) {
                final Object object = context.getObject(field.getType());
                field.setAccessible(true);
                field.set(t, object);
            }
        }
    }
}
