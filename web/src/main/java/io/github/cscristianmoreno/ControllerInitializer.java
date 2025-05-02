package io.github.cscristianmoreno;

import io.github.cscristianmoreno.annotations.Server;
import io.github.cscristianmoreno.utils.PackageScanUtil;

@Server
public class ControllerInitializer {
    public static void main(String[] args) throws Exception {
        PackageScanUtil.setScan("io");
        IOCInitializer.main(args);
    }
}
