package io.github.cscristianmoreno.ioc;

import io.github.cscristianmoreno.utils.ComponentUtil;

public abstract class IOCManager {
    
    /**
     * @return ComponentUtil
     */
    public static ComponentUtil component() {
        return new ComponentUtil();
    }
}
