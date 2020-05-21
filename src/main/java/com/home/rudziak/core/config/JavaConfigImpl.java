package com.home.rudziak.core.config;

import org.reflections.Reflections;

import java.util.Set;

public class JavaConfigImpl implements Config {

    private Reflections scanner;

    public JavaConfigImpl(String packageToScan) {
        this.scanner = new Reflections(packageToScan);
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        final Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
        if (classes.size() != 1) {
            throw new RuntimeException(ifc + " has 0 or more than one impl");
        }
        return classes.iterator().next();
    }
}
