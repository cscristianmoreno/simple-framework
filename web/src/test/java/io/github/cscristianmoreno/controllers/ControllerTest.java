package io.github.cscristianmoreno.controllers;

import io.github.cscristianmoreno.annotations.servlet.methods.Header;
import io.github.cscristianmoreno.annotations.servlet.methods.Variable;

public class ControllerTest {
    
    public void method(@Variable int id, @Header int header) {
        System.out.println(id);
    }
}
