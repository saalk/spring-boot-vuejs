package nl.saalks.springbootvuejs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.springframework.test.util.AssertionErrors.assertTrue;

class Puzzles2021Test {

    Puzzles2021 fixture;    File file;
    List<String> lines = new ArrayList<>();

    @BeforeEach
    void setUp() {
        fixture = new Puzzles2021();
    }

    void readFile(String resourceName) {
        ClassLoader classLoader = getClass().getClassLoader();
        file = new File(classLoader.getResource(resourceName).getFile());
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
    }

    void processLines() {
        try (Stream linesStream = Files.lines(file.toPath())) {
            linesStream.forEach(line -> {
                lines.add((String) line);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("1.1 - 1226 times increased for file")
    void sonarSweepWithInputFile() {

        String resourceName = "input-sonar-sweep.txt";
        readFile(resourceName);
        processLines();

        int increases = fixture.sonarSweep(lines);
        assertTrue("increases in report should be 1226", increases == 1226);
    }

    @Test
    @DisplayName("1.2 - 1252 times increased for file")
    void threeMeasurementSlidingWindowWithInputFile() {

        String resourceName = "input-sonar-sweep.txt";
        readFile(resourceName);
        processLines();

        int increases = fixture.threeMeasurementSlidingWindow(lines);
        assertTrue("increases in sliding windows should be ", increases == 1252);
    }

    @Test
    @DisplayName("1.2 - 5 times increased for example")
    void threeMeasurementSlidingWindowWithExampleData() {

        lines.add("199");
        lines.add("200");
        lines.add("208");
        lines.add("210");
        lines.add("200");
        lines.add("207");
        lines.add("240");
        lines.add("269");
        lines.add("260");
        lines.add("263");

        int increases = fixture.threeMeasurementSlidingWindow(lines);
        assertTrue("increases in sliding windows should be 5", increases == 5);
    }
}