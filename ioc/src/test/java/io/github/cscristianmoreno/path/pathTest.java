package io.github.cscristianmoreno.path;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import io.github.cscristianmoreno.utils.PackageScanUtil;

public class pathTest {
    
    @Test
    public void testPath() throws URISyntaxException {
        Path path = Paths.get(getClass().getResource("/").toURI()).getParent();
        System.out.println(path.resolve("classes").resolve("io"));

        // int packagePosition = path.indexOf(PackageScanUtil.getScan());
        // String pack = path.replace(File.separator, ".").substring(packagePosition);
        // String packageName = pack.replace(".class", "");
    }
}
