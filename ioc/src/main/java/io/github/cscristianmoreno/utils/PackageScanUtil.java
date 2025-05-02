package io.github.cscristianmoreno.utils;

public abstract class PackageScanUtil {
    
    private static String scan = "com";

    public static String getScan() {
        return scan;
    }

    public static void setScan(String packageName) {
        scan = packageName;
    }
}
