package com.home.rudziak.core.configurator;

import com.home.rudziak.core.ApplicationContext;

public interface ObjectConfigurator {

    void configure(Object t, ApplicationContext context);
}
