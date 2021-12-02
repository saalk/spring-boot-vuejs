package nl.saalks.springbootvuejs.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;


interface AdventOfCodeTest {

    default List<String> readFile(String resourceName) {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());
        System.out.println("File name :" + file.getName());
        System.out.println("Path: " + file.getPath());
        System.out.println("Absolute path: " + file.getAbsolutePath());
        System.out.println("Parent: " + file.getParent());
        System.out.println("Exists: " + file.exists());
        if (file.exists()) {
            System.out.println("Is writable: " + file.canWrite());
            System.out.println("Is readable: " + file.canRead());
            System.out.println("Is a directory: " + file.isDirectory());
            System.out.println("File Size in bytes: " + file.length());
        }

        List<String> lines = new ArrayList<>();
        try (Stream linesStream = Files.lines(file.toPath())) {
            linesStream.forEach(line -> {
                lines.add((String) line);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

}