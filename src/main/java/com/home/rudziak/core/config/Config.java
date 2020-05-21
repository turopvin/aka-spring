package com.home.rudziak.core.config;

public interface Config {

    <T> Class<? extends T> getImplClass(Class<T> ifc);
}
