package nl.saalks.springbootvuejs.service.aoc21;

import nl.saalks.springbootvuejs.service.AdventOfCodeTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;

import static org.springframework.test.util.AssertionErrors.assertTrue;

class Day5Test implements AdventOfCodeTest {

    Day5 fixture;

    @BeforeEach
    void setUp() {
        fixture = new Day5();
    }



    @DisplayName("5.1 - 5 the number of lines which cover that point")
    void bingoWithExampleInput() {

        ArrayList<String> lines = new ArrayList<>();
        lines.add("0,9 -> 5,9");
        lines.add("8,0 -> 0,8");
        lines.add("9,4 -> 3,4");
        lines.add("2,2 -> 2,1");
        lines.add("7,0 -> 7,4");
        lines.add("6,4 -> 2,0");
        lines.add("0,9 -> 2,9");
        lines.add("3,4 -> 1,4");
        lines.add("0,0 -> 8,8");
        lines.add("5,5 -> 8,2");

        int numberOfPoints = fixture.overlap(lines);
        assertTrue("numberOfPoints in report should be: ", numberOfPoints == 5);
    }


    @DisplayName("5.1 - 51776 finalScore bingo for file")
    void bingoWithInputFile() {

        ArrayList<String> lines = readFile("aoc21/input-lines-of-vents.txt");
        int numberOfPoints = fixture.overlap(lines);
        assertTrue("numberOfPoints in report should be: ", numberOfPoints == 5);

    }

}