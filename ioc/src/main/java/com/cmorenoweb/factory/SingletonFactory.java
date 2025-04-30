package com.cmorenoweb.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public abstract class SingletonFactory {
    private static final Map<String, Object> instances = new ConcurrentHashMap<>();
    private static final Map<String, Supplier<?>> suppliers = new ConcurrentHashMap<>(); 

    /** Register singleton factory */
    public static void register(Class<?> clazz) throws Exception {
        /** Instance a class without costructor parameter */
        Object instance = clazz.getConstructor().newInstance();

        /** Register clazz in supplier */
        suppliers.put(clazz.getSimpleName(), () -> instance);
    }

    public static <T> T get(Class<T> clazz) {
        /** Get a class name */
        String className = clazz.getSimpleName();

        /** Cast and create instance if don't exist */
        return clazz.cast(instances.computeIfAbsent(className, (k) -> {
            Supplier<?> supplier = suppliers.get(k);

            if (supplier == null) {
                throw new IllegalArgumentException("No supplier registered for key " + k);
            }

            return supplier.get();
        }));
    }
}
