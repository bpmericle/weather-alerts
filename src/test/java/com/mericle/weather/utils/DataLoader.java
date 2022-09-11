package com.mericle.weather.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataLoader {
    private static Path workingDir = Path.of("", "src/test/resources");

    public static String loadFromFile(String fileName) throws IOException {
        return Files.readString(workingDir.resolve(fileName));
    }
}
