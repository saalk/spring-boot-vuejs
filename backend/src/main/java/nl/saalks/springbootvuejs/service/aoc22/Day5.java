package nl.saalks.springbootvuejs.service.aoc22;

import nl.saalks.springbootvuejs.service.AdventOfCode;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class Day5 implements AdventOfCode {
	
	/**
	 * Day 3
	 * Each rucksack has two large compartments. All items of a given type are meant to go into
	 * exactly one of the two compartments.
	 * Find the item type that appears in both compartments of each rucksack.
	 * What is the sum of the priorities of those item types?
	 *
	 * @param rucksacksContents the lines eg "vJrwpWtwJgWrhcsFMMfFFhFp" first and second half are
	 * compartments
	 * @return sumOfThePriorities
	 */
	
	static String title = "CampCleanup - part 1: ";
	
	public int solutionPartOne(List<String> lines) {
		
		LOG.info(title + "lines: " + lines.size() + lineSeparator);
		
		int sum = 0;
		for (String line : lines) {
			// split logic
			List<String> split = Pattern
					                     .compile(",")
					                     .splitAsStream(line)
					                     .collect(Collectors.toList());
			sum += fullyContainsOther(split);
			LOG.info(title + "cumulative: " + sum + lineSeparator);
		}
		LOG.info("answer: " + sum);
		return sum;
	}
	
	public int fullyContainsOther(List<String> sectionRanges) {
		String first = sectionRanges.get(0);
		String second = sectionRanges.get(1);
		LOG.info(title + "first : [" + first + "] second: [" + second +
				         "]");

		int result = fullyContains(convertSection(first), convertSection(second));
		if (result == 0) {
			result = fullyContains(convertSection(second), convertSection(first));
			if (result == 0 ){
				return 0;
			} else {
				LOG.info(title + "contains second in first!");
				return result;
			}
		} else {
			LOG.info(title + "contains first in second !");
			return result;
		}
	}
	public int[] convertSection(String input) {
		List<String> split = Pattern.compile("-").splitAsStream(input).toList();
		int start = Integer.parseInt(split.get(0));
		int end = Integer.parseInt(split.get(1));
		
		int[] sections = new int[end-start+1];
		int loop = 0;
		for (int i = start; i <= end; i++) {
			sections[loop] = i;
			loop++;
		}
		return sections;
	}
	public int fullyContains(int[] first, int[] second) {
		// first 234 - second 12345
		first: for (int i = 0; i < first.length; i++) {
			for (int j = 0; j < second.length; j++) {
				if (first[i] == second[j]) {
					continue first;
				}
			}
			// no continue so i is not in j
			return 0;
		}
		return 1;
	}
	
	public int solutionPartTwo(List<String> lines) {
		
		LOG.info(title + "lines: " + lines.size() + lineSeparator);
		
		int sum = 0;
		for (String line : lines) {
			// split logic
			List<String> split = Pattern
					                     .compile(",")
					                     .splitAsStream(line)
					                     .collect(Collectors.toList());
			sum += partlyoverlaps(split);
			LOG.info(title + "cumulative: " + sum + lineSeparator);
		}
		LOG.info("answer: " + sum);
		return sum;
	}
	
	public int partlyoverlaps(List<String> sectionRanges) {
		String first = sectionRanges.get(0);
		String second = sectionRanges.get(1);
		LOG.info(title + "first : [" + first + "] second: [" + second +
				         "]");
		
		int result = overlap(convertSection(first), convertSection(second));
		return result;
	}
	
	public int overlap(int[] first, int[] second) {
		// first 234 - second 12345
		first: for (int i = 0; i < first.length; i++) {
			for (int j = 0; j < second.length; j++) {
				if (first[i] == second[j]) {
					LOG.info(title + "overlap found !");
					return 1;
				}
			}
		}
		return 0;
	}
}
