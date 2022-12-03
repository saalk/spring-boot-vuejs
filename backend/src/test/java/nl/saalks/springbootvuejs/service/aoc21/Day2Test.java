package nl.saalks.springbootvuejs.service.aoc21;

import nl.saalks.springbootvuejs.service.AdventOfCodeTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

class Day2Test implements AdventOfCodeTest {


    Day2 fixture;

    @BeforeEach
    void setUp() {
        fixture = new Day2();
    }

    @Test
    @DisplayName("2.1 - 1989265 plannedCourse for example")
    void pilotTheSubWithExampleData() {

        List<String> lines = new ArrayList<>();
        lines.add("forward 5");
        lines.add("down 5");
        lines.add("forward 8");
        lines.add("up 3");
        lines.add("down 8");
        lines.add("forward 2");

        int plannedCourse = fixture.pilotTheSub(lines);
        assertTrue("planned course in report should be: ", plannedCourse == 150);
    }

    @Test
    @DisplayName("2.1 - 1989265 plannedCourse for file")
    void pilotTheSubWithInputFile() {

        List<String> lines = readFile("aoc21/input-planned-course.txt");
        int plannedCourse = fixture.pilotTheSub(lines);
        assertTrue("planned course in report should be: ", plannedCourse == 1989265);
    }

    @Test
    @DisplayName("2.2 - 900 plannedCourse for file")
    void aimTheSubWithExampleData() {

        List<String> lines = new ArrayList<>();
        lines.add("forward 5");
        lines.add("down 5");
        lines.add("forward 8");
        lines.add("up 3");
        lines.add("down 8");
        lines.add("forward 2");

        int plannedCourse = fixture.aimTheSub(lines);
        assertTrue("planned course in report should be: ", plannedCourse == 900);
    }

    @Test
    @DisplayName("2.2 - 2089174012 plannedCourse for file")
    void aimTheSubWithInputFile() {

        List<String> lines = readFile("aoc21/input-planned-course.txt");
        int plannedCourse = fixture.aimTheSub(lines);
        assertTrue("planned course in report should be: ", plannedCourse == 2089174012);
    }

    @Test
    @DisplayName("2.2 - 2089174012 plannedCourse for file")
    void aimTheSubHashMapWithInputFile() {

        List<String> lines = readFile("aoc21/input-planned-course.txt");
        int plannedCourse = fixture.aimTheSubHashMap(lines);
        assertTrue("planned course in report should be: ", plannedCourse == 2089174012);
    }
}