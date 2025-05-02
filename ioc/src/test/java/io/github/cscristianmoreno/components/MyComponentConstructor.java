package io.github.cscristianmoreno.components;

public class MyComponentConstructor {
    
    private final MyComponentInjectable myComponentInjectable;

    public MyComponentConstructor(final MyComponentInjectable myComponentInjectable) {
        this.myComponentInjectable = myComponentInjectable;
    }

    public MyComponentInjectable getMyComponentInjectable() {
        return myComponentInjectable;
    }
}
