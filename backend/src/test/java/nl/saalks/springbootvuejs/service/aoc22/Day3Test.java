package nl.saalks.springbootvuejs.service.aoc22;

import nl.saalks.springbootvuejs.service.AdventOfCodeTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

class Day3Test implements AdventOfCodeTest {


    Day3 fixture;

    @BeforeEach
    void setUp() {
        fixture = new Day3();
    }
    @Test
    @DisplayName("3.1 - sumOfThePriorities example")
    void sumOfThePrioritiesWithExampleFile() {

        List<String> lines = readFile("aoc22/day3example.txt");
        int score = fixture.solutionPartOne(lines);
        assertTrue("sumOfThePriorities: ", score == 157);
    }

    @Test
    @DisplayName("3.1 - sumOfThePriorities real")
    void sumOfThePrioritiesWithRealFile() {

        List<String> lines = readFile("aoc22/day3.txt");
        int score = fixture.solutionPartOne(lines);
        assertTrue("sumOfThePriorities: ", score == 7746);
    }
    
    @Test
    @DisplayName("3.2 - sumOfThePriorities example")
    void part2WithExampleFile() {
        
        List<String> lines = readFile("aoc22/day3example.txt");
        int score = fixture.solutionPartTwo(lines);
        assertTrue("sumOfThePriorities: ", score == 70);
    }
    
    @Test
    @DisplayName("3.2 - sumOfThePriorities real")
    void part2WithRealFile() {
        
        List<String> lines = readFile("aoc22/day3.txt");
        int score = fixture.solutionPartTwo(lines);
        assertTrue("sumOfThePriorities: ", score == 2604);
    }

}