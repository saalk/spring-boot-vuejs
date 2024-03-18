package nl.saalks.springbootvuejs.service.aoc23;

import com.sun.istack.NotNull;
import nl.saalks.springbootvuejs.service.AdventOfCodeTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

class Day1Test implements AdventOfCodeTest {

    nl.saalks.springbootvuejs.service.aoc23.Day1 fixture;

    @BeforeEach
    void setUp() {
        fixture = new Day1();
    }

    @Test
    @DisplayName("1.1 - 4th elf carries most calories")
    void elfWithMostCaloriesSimpleWithExampleInputFile() {

        @NotNull List<String> lines = readFile("aoc23/day1example.txt");
        int[] elfWithMostCalories = fixture.elfWithMostCaloriesSimple(lines);
        assertTrue("elf that carries most calories should be 4th", elfWithMostCalories[0] == 4);
        assertTrue("elf that carries most calories carries ", elfWithMostCalories[1] == 24000);
    }

    @Test
    @DisplayName("1.1 - 26th elf carries x calories")
    void elfWithMostCaloriesSimpleWithRealInputFile() {

        List<String> lines = readFile("aoc23/day1.txt");
        int[] elfWithMostCalories = fixture.elfWithMostCaloriesSimple(lines);
        assertTrue("elf that carries most calories should be 26", elfWithMostCalories[0] == 26);
        assertTrue("elf that carries most calories carries ", elfWithMostCalories[1] == 70764);
    }

    @Test
    @DisplayName("1.2 - top3 for example file")
    void totalCaloriesOftop3elvesWithMostCaloriesWithExampleInputFile() {

        List<String> lines = readFile("aoc23/day1example.txt");
        int top3 = fixture.totalCaloriesOftop3elvesWithMostCalories(lines);
        assertTrue("total Calories Of top3elves WithMostCalories ", top3 == 41000);
    }
    @Test
    @DisplayName("1.2 - top3 for real file")
    void totalCaloriesOftop3elvesWithMostCaloriesWithRealInputFile() {

        List<String> lines = readFile("aoc23/day1.txt");
        int top3 = fixture.totalCaloriesOftop3elvesWithMostCalories(lines);
        assertTrue("total Calories Of top3elves WithMostCalories  ", top3 == 70764+67568+65573);
    }
}