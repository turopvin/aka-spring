package com.home.rudziak.core.config;

import java.util.Set;

public interface Config {

    <T> Class<? extends T> getImplClass(Class<T> ifc);

    <T> Set<Class<? extends T>> getAllImplClass(Class<T> ifc);
}
