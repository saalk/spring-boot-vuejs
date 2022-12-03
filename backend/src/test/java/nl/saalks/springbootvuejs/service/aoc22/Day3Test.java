package nl.saalks.springbootvuejs.service.aoc22;

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
    @DisplayName("3.1 - score Rock Paper Scissors")
    void scoreRockPaperScissorsWithExampleFile() {

        List<String> lines = readFile("aoc22/encrypted-strategy-guide-example.txt");
        int score = fixture.scoreRockPaperScissors(lines);
        assertTrue("score Rock Paper Scissors should be: ", score == 15);
    }

    @Test
    @DisplayName("3.1 - score Rock Paper Scissors")
    void scoreRockPaperScissorsWithRealFile() {

        List<String> lines = readFile("aoc22/encrypted-strategy-guide.txt");
        int score = fixture.scoreRockPaperScissors(lines);
        assertTrue("score Rock Paper Scissors should be: ", score == 14531);
    }

    @Test
    @DisplayName("3.2 - score Rock Paper Scissors")
    void scoreRockPaperScissorsSecondWithExampleFile() {

        List<String> lines = readFile("aoc22/encrypted-strategy-guide-example.txt");
        int score = fixture.scoreRockPaperScissorsSecond(lines);
        assertTrue("score Rock Paper Scissors should be: ", score == 12);
    }
    @Test
    @DisplayName("3.2 - score Rock Paper Scissors")
    void scoreRockPaperScissorsSecondWithRealFile() {

        List<String> lines = readFile("aoc22/encrypted-strategy-guide.txt");
        int score = fixture.scoreRockPaperScissorsSecond(lines);
        assertTrue("score Rock Paper Scissors should be: ", score == 11258);
    }

}