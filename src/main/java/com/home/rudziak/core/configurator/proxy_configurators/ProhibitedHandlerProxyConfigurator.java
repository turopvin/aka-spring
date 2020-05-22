package com.home.rudziak.core.configurator.proxy_configurators;

import com.home.rudziak.core.configurator.ProxyConfigurator;
import com.home.rudziak.core.configurator.annotations.Prohibited;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProhibitedHandlerProxyConfigurator implements ProxyConfigurator {

    @Override
    public Object replaceWithProxy(Object t, Class implClass) {
        if (implClass.isAnnotationPresent(Prohibited.class)) {
            if (implClass.getInterfaces().length == 0) {
                return Enhancer.create(implClass, (InvocationHandler) (proxy, method, args) -> getInvocationHandlerLogix(t, method, args));
            }

            return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(),
                    (proxy, method, args) -> getInvocationHandlerLogix(t, method, args));
        } else {
            return t;
        }
    }

    private Object getInvocationHandlerLogix(Object t, Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
        System.out.println("**************** что ж ты делаешь урод!*********");
        return method.invoke(t, args);
    }
}
