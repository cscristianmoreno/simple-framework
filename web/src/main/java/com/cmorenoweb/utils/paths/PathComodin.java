package com.cmorenoweb.utils.paths;

import java.util.regex.Pattern;

public abstract class PathComodin {
    
    /** Convert {*} values in regex */
    public static Pattern convert(String uri) {
        String[] paths = uri.substring(1).split("/");
        String converted = uri;

        for (String path: paths) {
            if (!path.startsWith("{") && !path.endsWith("}")) {
                continue;
            }
            
            converted = converted.replace(path, "([^/]+)");
        }

        return Pattern.compile(converted); 
    }
}
