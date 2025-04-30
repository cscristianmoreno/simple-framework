package com.cmorenoweb.ioc;

import com.cmorenoweb.utils.ComponentUtil;

public abstract class IOCManager {
    
    /**
     * @return ComponentUtil
     */
    public static ComponentUtil component() {
        return new ComponentUtil();
    }
}
