package com.cmorenoweb.utils.values;

import java.util.ArrayList;
import java.util.List;

public abstract class PathValueUtil {
    
    public static List<String> getPathsValue(String pathInfo, String annotationValue) {
        String[] annotationPaths = annotationValue.substring(1).split("/");
        String[] paths = pathInfo.substring(1).split("/");

        int i = 0;
        List<String> wildCards = new ArrayList<String>();

        for (String annotationPath: annotationPaths) {
            if (!annotationPath.startsWith("{") && !annotationPath.endsWith("}")) {
                i++;
                continue;
            }

            wildCards.add(paths[i]);
            i++;
        }

        return wildCards;
    }
}
