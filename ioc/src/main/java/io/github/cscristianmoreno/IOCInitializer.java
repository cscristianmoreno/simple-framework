package io.github.cscristianmoreno;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.codehaus.jackson.map.ObjectMapper;

import io.github.cscristianmoreno.annotations.Server;
import io.github.cscristianmoreno.factory.SingletonFactory;
import io.github.cscristianmoreno.utils.ClassScanUtil;
import io.github.cscristianmoreno.utils.PackageScanUtil;

@Server
public class IOCInitializer 
{
    public static void main(String[] args) throws Exception {
        SingletonFactory.register(ObjectMapper.class);
        
        /** Get a IOCInitializer insance to scan files */
        IOCInitializer app = new IOCInitializer();
        List<File> files = app.getFiles();

        /** Scan all clases */
        ClassScanUtil classScanUtil = new ClassScanUtil();
        classScanUtil.scan(files);
    }

    public List<File> getFiles() throws URISyntaxException, IOException {
        Path paths = Paths.get(getClass().getResource("/").toURI()).getParent(); 
        
        Path resolve = paths.resolve("classes").resolve(PackageScanUtil.getScan());

        return Files.walk(resolve) 
        .filter(Files::isRegularFile)
        .map(Path::toFile)
        .collect(Collectors.toList());
    }
}
