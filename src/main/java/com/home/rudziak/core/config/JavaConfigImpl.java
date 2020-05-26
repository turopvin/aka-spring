package com.home.rudziak.core.config;

import org.reflections.Reflections;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Set;

public class JavaConfigImpl implements Config {

    private Reflections scanner;
    private Map<Class, Class> ifc2implClass;
    private DataSource dataSource;

    public JavaConfigImpl(String packageToScan, Map<Class, Class> ifc2implClass) {
        this.scanner = new Reflections(packageToScan);
        this.ifc2implClass = ifc2implClass;
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {

        if (ifc2implClass.containsKey(ifc)) {
            return ifc2implClass.get(ifc);
        }

        final Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
        if (classes.size() != 1) {
            throw new RuntimeException(ifc + " has 0 or more than one impl");
        }
        final Class<? extends T> next = classes.iterator().next();
        ifc2implClass.put(ifc, next);
        return next;

    }

    @Override
    public <T> Set<Class<? extends T>> getAllImplClass(Class<T> ifc) {
        return scanner.getSubTypesOf(ifc);
    }
}
