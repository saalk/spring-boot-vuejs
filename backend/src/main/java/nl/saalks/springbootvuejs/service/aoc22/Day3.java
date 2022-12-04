package nl.saalks.springbootvuejs.service.aoc22;

import nl.saalks.springbootvuejs.service.AdventOfCode;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day3 implements AdventOfCode {
	
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
	
	static String title = "RucksackContents - ";
	
	public int solution(List<String> lines) {
		
		LOG.info(title + "lines: " + lines.size());
		
		int sum = 0;
		for (String line : lines) {
			
			String first = line.substring(0, line.length() / 2);
			String second = line.substring(line.length() / 2);
			sum += calculatePriority(first,second );
			
			LOG.info(title + "cumulative: " + sum);
		}
		
		LOG.info("answer: " + sum);
//
//		sum = 0;
//		for(int i = 0; i < input.size(); i += 3)
//			sum += calculatePriority(input.get(i), input.get(i + 1), input.get(i + 2));
//		LOG.info("answer: " + sum);
		return sum;
	}
	
	public int calculatePriority(String first, String second) {
		LOG.info(title + "first: " + first + " second: " + second);
		
		String matchingLetter = findTheMatchingChar(first, second);
		char martchingChar = matchingLetter.charAt(0);
		
		// CHAR MAGIC
		// char to int is  A=65, a=97 first capitals and then lower cases
		// we want first lower cases and then upper cases order
		int priority = (martchingChar - (martchingChar <= 'Z' ? 'A' : 'a')) + (martchingChar <= 'Z' ? 27 : 1);
		// eg c (99) - a (97) + 1 = 3
		// eg C (67) - A (65) + 27 = 29
		
		LOG.info(title + "martchingChar: " + martchingChar + " priority: " + priority);
		return priority;

	}
	
	public String findTheMatchingChar(String first, String second) {
		StringBuilder result = new StringBuilder();
		char[] secondToChars = second.toCharArray();
		
		for (char charOfFirst : first.toCharArray()) {
			for (char charOfSecond : secondToChars) {
				if (charOfFirst == charOfSecond) {
					result.append(charOfFirst);
					break;
				}
			}
		}
		return result.toString();
	}
}
