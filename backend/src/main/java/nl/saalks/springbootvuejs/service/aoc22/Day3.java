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
	
	public int solutionPartOne(List<String> lines) {
		
		LOG.info(title + "lines: " + lines.size());
		
		int sum = 0;
		for (String line : lines) {
			String first = line.substring(0, line.length() / 2);
			String second = line.substring(line.length() / 2);
			sum += calculatePriority(first, second, "" );
			
			LOG.info(title + "cumulative: " + sum);
		}
		
		LOG.info("answer: " + sum);
		return sum;
	}
	public int solutionPartTwo(List<String> lines) {
		LOG.info(title + "lines: " + lines.size());
		
		int sum = 0;
		// iterate in steps of 3 - 0,1,2 then 3,4,5
		// no split in first and second
		for(int i = 0; i < lines.size(); i += 3) {
			sum += calculatePriority(lines.get(i), lines.get(i + 1), lines.get(i + 2));
			
			LOG.info(title + "cumulative: " + sum);
			
		}
		LOG.info("answer: " + sum);
		
		return sum;
	}
	
	public int calculatePriority(String first, String second, String third) {
		LOG.info(title + "first : " + first);
		LOG.info(title + "second: " + second);
		LOG.info(title + "third : " + third);
		
		// improvement - call this method twice or once depending on part 1 or 2
		String matchingLetter = findTheMatchingChar(first, second, third);
		char matchingChar = matchingLetter.charAt(0);
		// CHAR MAGIC
		// char to int is  A=65, a=97 first capitals and then lower cases
		// we want first lower cases and then upper cases order
		int priority = (matchingChar - (matchingChar <= 'Z' ? 'A' : 'a')) + (matchingChar <= 'Z' ? 27 : 1);
		// eg c (99) - a (97) + 1 = 3
		// eg C (67) - A (65) + 27 = 29
		LOG.info(title + "matchingChar: " + matchingChar + " priority: " + priority);
		return priority;

	}
	
	public String findTheMatchingChar(String first, String second, String third) {
		StringBuilder result = new StringBuilder();
		char[] firstToChars = first.toCharArray();
		char[] secondToChars = second.toCharArray();
		char[] thirdToChars = third.toCharArray();
		
		for (char charOfFirst : firstToChars) {
			for (char charOfSecond : secondToChars) {
				
				if (!third.isEmpty()) {
					for (char charOfThird : thirdToChars) {
						if ((charOfFirst == charOfSecond) &
								    (charOfSecond == charOfThird)) {
							result.append(charOfFirst);
							LOG.info(title + " charOfFirst : " + charOfFirst +
									         " charOfSecond: " + charOfSecond +
									         " charOfThird : " + charOfThird );
							break;
							
						}
					}
				} else {
					if (charOfFirst == charOfSecond) {
						result.append(charOfFirst);
						break;
					}
				}
			}
		}
		return result.toString();
	}
}
