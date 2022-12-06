package nl.saalks.springbootvuejs.service.aoc22;

import nl.saalks.springbootvuejs.service.AdventOfCodeTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

class Day5Test implements AdventOfCodeTest {
	
	
	Day5 fixture;
	
	@BeforeEach
	void setUp() {
		fixture = new Day5();
	}
	
	@Test
	@DisplayName("5.1 - CampCleanup example")
	void sumOfThePrioritiesWithExampleFile() {
		
		List<String> lines = readFile("aoc22/day4example.txt");
		int score = fixture.solutionPartOne(lines);
		assertTrue("CampCleanup: ", score == 2);
	}
	
	@Test
	@DisplayName("5.1 - CampCleanup real")
	void sumOfThePrioritiesWithRealFile() {
		
		List<String> lines = readFile("aoc22/day4.txt");
		int score = fixture.solutionPartOne(lines);
		assertTrue("CampCleanup: ", score == 538);
	}
	
	@Test
	@DisplayName("4.2 - CampCleanup example")
	void part2WithExampleFile() {
		
		List<String> lines = readFile("aoc22/day4example.txt");
		int score = fixture.solutionPartTwo(lines);
		assertTrue("sumOfThePriorities: ", score == 4);
	}
	
	@Test
	@DisplayName("4.2 - CampCleanup real")
	void part2WithRealFile() {
		
		List<String> lines = readFile("aoc22/day4.txt");
		int score = fixture.solutionPartTwo(lines);
		assertTrue("sumOfThePriorities: ", score == 792);
	}
	
}