package com.home.rudziak.core.configurator;

import com.home.rudziak.core.ObjectFactory;
import com.home.rudziak.core.configurator.annotations.InjectByType;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator {

    @SneakyThrows
    @Override
    public void configure(Object t) {
        final Class<?> aClass = t.getClass();
        for (Field field : aClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectByType.class)) {
                final Object object = ObjectFactory.getInstance().createObject(field.getType());
                field.setAccessible(true);
                field.set(t, object);
            }
        }
    }
}
