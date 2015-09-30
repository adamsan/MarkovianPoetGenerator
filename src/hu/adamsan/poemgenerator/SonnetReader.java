package hu.adamsan.poemgenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SonnetReader {
    private Path path;

    public SonnetReader(String filePath) {
        path = Paths.get(filePath);
    }

    public List<String> readAllLines() {
        List<String> sonnetLines = null;
        try {
            sonnetLines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sonnetLines;
    }
}
