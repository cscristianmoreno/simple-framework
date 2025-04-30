package com.cmorenoweb.controllers;

import com.cmorenoweb.annotations.servlet.methods.Variable;

public class ControllerTest {
    
    public void method(@Variable int id, @Variable Object object, @Variable String string, @Variable boolean b) {
        System.out.println(id);
        System.out.println(object);
        System.out.println(string);
        System.out.println(b);
    }
}
