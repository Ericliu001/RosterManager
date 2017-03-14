package com.example.presentation.presenter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * Created by ericliu on 14/3/17.
 */

public final class DummyViewGenerator {
    private DummyViewGenerator() {
    }


    public static Object createDummyInstance(BasePresenter basePresenter) {
        ParameterizedType p = (ParameterizedType) basePresenter.getClass().getGenericSuperclass();
        Class<?> classType = (Class<?>) p.getActualTypeArguments()[0];

        return Proxy.newProxyInstance(
                classType.getClassLoader(),
                new Class[]{classType},
                new InvocationHandler() {
                    @Override
                    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
                        return null;
                    }
                });
    }
}
