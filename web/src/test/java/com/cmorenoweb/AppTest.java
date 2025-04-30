package com.cmorenoweb;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     * @throws URISyntaxException 
     * @throws IOException 
     */
    @Test
    public void shouldAnswerWithTrue() throws URISyntaxException, IOException
    {
        String env = System.getProperty("user.dir");
        File fileDirectory = new File(env);
        
        System.out.println(fileDirectory.getAbsolutePath());

        Path directory = Paths.get(getClass().getResource("/").toURI()).getParent();

        List<File> paths = Files.walk(Paths.get(directory.resolve("classes/com/cmorenoweb").toUri()))
        .filter(Files::isRegularFile)
        .map(Path::toFile)
        .collect(Collectors.toList());

        
    }
}
