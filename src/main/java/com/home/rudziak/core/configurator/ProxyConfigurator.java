package com.home.rudziak.core.configurator;

public interface ProxyConfigurator {

    Object replaceWithProxy(Object t, Class implClass);
}
