package nl.saalks.springbootvuejs.service.aoc21;

import nl.saalks.springbootvuejs.service.AdventOfCodeTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

class Day1Test implements AdventOfCodeTest {

    Day1 fixture;

    @BeforeEach
    void setUp() {
        fixture = new Day1();
    }

    @Test
    @DisplayName("1.1 - 1226 times increased for file")
    void sonarSweepWithInputFile() {

        List<String> lines = readFile("aoc21/input-sonar-sweep.txt");
        int increases = fixture.sonarSweep(lines);
        assertTrue("increases in report should be 1226", increases == 1226);
    }

    @Test
    @DisplayName("1.1 - 1226 times increased for file")
    void sonarSweepStreamWithInputFile() {

        List<String> lines = readFile("aoc21/input-sonar-sweep.txt");
        int increases = fixture.sonarSweepStream(lines);
        assertTrue("increases in report should be 1226", increases == 1226);
    }

    @Test
    @DisplayName("1.1 - 1226 times increased for file")
    void sonarSweepSimpleWithInputFile() {

        List<String> lines = readFile("aoc21/input-sonar-sweep.txt");
        int increases = fixture.sonarSweepSimple(lines);
        assertTrue("increases in report should be 1226", increases == 1226);
    }

    @Test
    @DisplayName("1.2 - 1252 times increased for file")
    void threeMeasurementSlidingWindowWithInputFile() {

        List<String> lines = readFile("aoc21/input-sonar-sweep.txt");
        int increases = fixture.threeMeasurementSlidingWindow(lines);
        assertTrue("increases in sliding windows should be ", increases == 1252);
    }

    @Test
    @DisplayName("1.2 - 5 times increased for example")
    void threeMeasurementSlidingWindowWithExampleData() {

        List<String> lines = new ArrayList<>();
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