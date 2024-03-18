package nl.saalks.springbootvuejs.service.aoc21;

import nl.saalks.springbootvuejs.service.AdventOfCodeTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.springframework.test.util.AssertionErrors.assertTrue;

class Day4Test implements AdventOfCodeTest {

    Day4 fixture;

    @BeforeEach
    void setUp() {
        fixture = new Day4();
    }

    @Test
    @DisplayName("4.1 - 4512 finalScore bingo for example")
    void bingoWithExampleInput() {

        ArrayList<String> lines = new ArrayList<>();
        lines.add("7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1");
        lines.add("");
        lines.add("22 13 17 11  0");
        lines.add(" 8  2 23  4 24");
        lines.add("21  9 14 16  7");
        lines.add(" 6 10  3 18  5");
        lines.add(" 1 12 20 15 19");
        lines.add("");
        lines.add(" 3 15  0  2 22");
        lines.add(" 9 18 13 17  5");
        lines.add("19  8  7 25 23");
        lines.add("20 11 10 24  4");
        lines.add("14 21 16 12  6");
        lines.add("");
        lines.add("14 21 17 24  4");
        lines.add("10 16 15  9 19");
        lines.add("18  8 23 26 20");
        lines.add("22 11 13  6  5");
        lines.add(" 2  0 12  3  7");

        int finalScores[] = fixture.bingo(lines);
        assertTrue("finalScoreFirst in report should be: ", finalScores[0] == 4512);
//        assertTrue("finalScoreLast in report should be: ", finalScores[1] == 51776);
    }

    @Test
    @DisplayName("4.1 - 51776 finalScore bingo for file")
    void bingoWithInputFile() {

        ArrayList<String> lines = readFile("aoc21/input-bingo.txt");
        int[] finalScores = fixture.bingo(lines);
        assertTrue("finalScoreFirst in report should be: ", finalScores[0] == 51776);
//        assertTrue("finalScoreLast in report should be: ", finalScores[1] == 51776);
    }

}