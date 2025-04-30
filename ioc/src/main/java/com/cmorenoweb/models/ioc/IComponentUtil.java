package com.cmorenoweb.models.ioc;

public interface IComponentUtil {
    <T> void create(Class<?> clazz) throws Exception;
}
