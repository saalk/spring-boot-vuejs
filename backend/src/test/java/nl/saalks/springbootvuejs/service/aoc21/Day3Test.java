package nl.saalks.springbootvuejs.service.aoc21;

import nl.saalks.springbootvuejs.service.AdventOfCodeTest;
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

        List<String> lines = readFile("aoc21/input-binary-diagnostic.txt");
        int powerConsumption = fixture.powerConsumption(lines, lines.get(0).length());
        assertTrue("powerConsumption in report should be: ", powerConsumption == 2250414);
    }

    @Test
    @DisplayName("3.2 - 230 lifeSupportRating for example")
    void lifeSupportRatingWithExampleInput() {

        ArrayList<String> lines = new ArrayList<>();
        lines.add("00100"); //1  d
        lines.add("11110"); //2  .d
        lines.add("10110"); //3
        lines.add("10111"); //4      d
        lines.add("10101"); //5     d
        lines.add("01111"); //6  d
        lines.add("00111"); //7  d
        lines.add("11100"); //8  .d
        lines.add("10000"); //9    d
        lines.add("11001"); //10  d
        lines.add("00010"); //11 d
        lines.add("01010"); //12 d

        int powerConsumption = fixture.lifeSupportRating(lines);
        assertTrue("lifeSupportRating in report should be: ", powerConsumption == 230);
    }

    @Test
    @DisplayName("3.2 - 6085575 lifeSupportRating for file")
    void lifeSupportRatingWithInputFile() {

        ArrayList<String> lines = readFile("aoc21/input-binary-diagnostic.txt");
        int powerConsumption = fixture.lifeSupportRating(lines);
        assertTrue("lifeSupportRating in report should be: ", powerConsumption == 6085575);
    }


}