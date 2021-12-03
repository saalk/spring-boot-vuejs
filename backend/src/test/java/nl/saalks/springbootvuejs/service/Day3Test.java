package nl.saalks.springbootvuejs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

class Day3Test implements AdventOfCodeTest {

    Day3 fixture;

    @BeforeEach
    void setUp() {
        fixture = new Day3();
    }

    @Test
    void binaryDiagnostic() {
    }

    @Test
    @DisplayName("3.1 - 198 powerConsumption for example")
    void binaryDiagnosticWithExampleInput() {

        List<String> lines = new ArrayList<>();
        lines.add("00100");
        lines.add("11110");
        lines.add("10110");
        lines.add("10111");
        lines.add("10101");
        lines.add("01111");
        lines.add("00111");
        lines.add("11100");
        lines.add("10000");
        lines.add("11001");
        lines.add("00010");
        lines.add("01010");

        int powerConsumption = fixture.powerConsumption(lines, lines.get(0).length());
        assertTrue("powerConsumption in report should be: ", powerConsumption == 198);
    }

    @Test
    @DisplayName("3.1 - 2250414 powerConsumption for file")
    void binaryDiagnosticWithInputFile() {

        List<String> lines = readFile("input-binary-diagnostic.txt");
        int powerConsumption = fixture.powerConsumption(lines, lines.get(0).length());
        assertTrue("powerConsumption in report should be: ", powerConsumption == 2250414);
    }

}