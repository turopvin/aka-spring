package com.home.rudziak.core;

import com.home.rudziak.core.config.JavaConfigImpl;

import java.util.Map;

public class ApplicationRunner {

    public static ApplicationContext run(String packageToScan, Map<Class, Class> ifc2implClass) {
        final JavaConfigImpl config = new JavaConfigImpl(packageToScan, ifc2implClass);
        final ApplicationContext context = new ApplicationContext(config);
        final ObjectFactory factory = new ObjectFactory(context);
        context.setFactory(factory);
        return context;
    }
}
