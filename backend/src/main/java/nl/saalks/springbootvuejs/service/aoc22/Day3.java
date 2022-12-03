package nl.saalks.springbootvuejs.service.aoc22;

import nl.saalks.springbootvuejs.service.AdventOfCode;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
public class Day3 implements AdventOfCode {
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	class Rucksack {
		String firstCompartment;
		String secondCompartment;
		List<CommonItem> commonItems;
	}
	
	;
	
	class CommonItem {
		String commonItem;
		int priority;
	}
	
	/**
	 * Day 3 - 1
	 * Each rucksack has two large compartments. All items of a given type are meant to go into
	 * exactly one of the two compartments.
	 * Find the item type that appears in both compartments of each rucksack.
	 * What is the sum of the priorities of those item types?
	 *
	 * @param rucksacksContents the lines eg "vJrwpWtwJgWrhcsFMMfFFhFp" first and second half are
	 * compartments
	 * @return sumOfThePriorities
	 */
	
	static String title = "RucksacksContents - ";
	
	public int sumOfThePriorities(List<String> rucksacksContents) {
		
		//
		
		LOG.info(title + "lines: " + rucksacksContents.size());
		
		List<Rucksack> rucksackList = new ArrayList<>();
		Rucksack currentRucksack = new Rucksack();
		String itemType = "";
		
		int sumOfThePriorities = 0;
		
		// a.length => int[] a = new int[5] => capacity
		// length() is a method used by Strings
		// size() is a method implemented by all members of Collection
		//
		// for-each loop all lines to find common item(s)
		for (String rucksack : rucksacksContents) {

//            List<String> split = Pattern
//                    .compile(" ")
//                    .splitAsStream(rucksack)
//                    .collect(Collectors.toList());
			
			
			int rucksackLength = rucksack.length(); //11 or 12
			int firstCompartmentLength = rucksackLength / 2; // 5 or 6
			currentRucksack.firstCompartment = rucksack.substring(0, firstCompartmentLength - 1);
			currentRucksack.secondCompartment = rucksack.substring(firstCompartmentLength,
					rucksackLength);
			
			LOG.info(title + "rucksack: " + rucksack);
			LOG.info(title + "first: " + currentRucksack.firstCompartment + "sec: "
					         + currentRucksack.secondCompartment);
			
			// for loop to get the common from first in second
			for (int item = 0; item < firstCompartmentLength; item++) {
				itemType = currentRucksack.firstCompartment.substring(item, 1);
				
			}
		}
		LOG.info(title + "rucksack: " + " score: ");
		LOG.info("A/X = rock, B/Y = paper, C/Z = scissors " + lineSeparator);
		
		LOG.info(title + "sumOfThA=ePriorities: " + sumOfThePriorities);
		return sumOfThePriorities;
		
	}
	
	// A = rock, B = paper, C = scissors
	static int isItemAlsoInSecondCompartment(Rucksack rucksack) {
		return 0;
	}
	
	static String allItemTypes = "abcdefghijklmnopqrstuvwqyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	static int calculateItemPriority(String itemType) {
		
		for (int i = 1; i <= 26; i++) {
			if (itemType.equals(allItemTypes.substring(i, 1))) {
				LOG.info(title + "priority: " + i);
				return i;
			}
		}
		throw new ArithmeticException(itemType + " not found in " + allItemTypes);
	}
	
}
