package softuni.exam.instagraphlite.utils.impl;

import org.springframework.stereotype.Component;
import softuni.exam.instagraphlite.utils.FileUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class FileUtilImpl implements FileUtil {
    @Override
    public String readFile(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath))
                .stream()
                .filter(x -> !x.isEmpty())
                .collect(Collectors.joining(""));
    }

    @Override
    public String readFileAddedNewLines(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath))
                .stream()
                .filter(x -> !x.isEmpty())
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public void writeFile(String content, String filePath) throws IOException {
        Files.write(
                Paths.get(filePath),
                Collections.singleton(content),
                StandardCharsets.UTF_8
        );
    }
}
