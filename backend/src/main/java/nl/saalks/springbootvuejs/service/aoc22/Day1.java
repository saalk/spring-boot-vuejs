package nl.saalks.springbootvuejs.service.aoc22;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.saalks.springbootvuejs.service.AdventOfCode;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static nl.saalks.springbootvuejs.service.AdventOfCode.*;

@Service
public class Day1 implements AdventOfCode {

    /**
     * Returns the nth elf carrying the most calories.
     *
     * @param inventory - the calorie of a series of item eg "1000 ", "400 ", " " for an elf
     * @return the nth elf with most calories
     */
    public int[] elfWithMostCaloriesSimple(List<String> inventory) {

        LOG.info("elfWithMostCalories - total lines: " + inventory.size());
        int countCalories = 0;
        int currentElf = 1;
        int elfWithMostCalories = 0;
        int caloriesForElfWithMostCalories = 0;

        for (int i = 0; i < inventory.size(); i++) {
            LOG.info("elfWithMostCalories - line: " + i + " content: " + inventory.get(i));

            if (isBlankString(inventory.get(i))) {
                // end of current elf inventory
                if (countCalories >= caloriesForElfWithMostCalories) {
                    elfWithMostCalories = currentElf;
                    caloriesForElfWithMostCalories = countCalories;
                    LOG.info("elfWithMostCalories - elf: " + elfWithMostCalories + " calories: "
                            + caloriesForElfWithMostCalories);
                }
                LOG.info(lineSeparator);
                // reset for next elf
                countCalories = 0;
                currentElf++;
                LOG.info("elfWithMostCalories - next elf: " + currentElf);

            } else {
                // count calories for this elf
                List<String> split = Pattern
                        .compile(" ")
                        .splitAsStream(inventory.get(i))
                        .collect(Collectors.toList());
                int calories = Integer.parseInt(split.get(0));
                countCalories = countCalories + calories;
            }
        }

        LOG.info("elfWithMostCalories - elfWithMostCalories: " + elfWithMostCalories);
        LOG.info("elfWithMostCalories - calories of elfWithMostCalories: " + caloriesForElfWithMostCalories);

       int[] intArray = {elfWithMostCalories,caloriesForElfWithMostCalories} ;
       return intArray;
    }

    /**
     * Returns the total calories of the top 3 elves carrying the most calories.
     *
     * @param inventory - the calorie of a series of item eg "1000 ", "400 ", " " for an elf
     * @return the top 3 elves with most calories total calories
     */
    public int totalCaloriesOftop3elvesWithMostCalories(List<String> inventory) {

        LOG.info("top3elvesWithMostCalories - total lines: " + inventory.size());

        List<ElfAndCalorie> elfAndCalories = new ArrayList<>();

        int countCalories = 0;
        int currentElf = 1;
        int elfWithMostCalories = 0;
        int caloriesForElfWithMostCalories = 0;

        // get a list of all elves and calories
        for (int i = 0; i < inventory.size(); i++) {
            // LOG.info("elfWithMostCalories - line: " + i + " content: " + inventory.get(i));

            if (isBlankString(inventory.get(i))) {
                // end of current elf inventory
                elfAndCalories.add(new ElfAndCalorie(currentElf, countCalories));
                LOG.info("top3elvesWithMostCalories - elf: " + currentElf + " calories: "
                            + countCalories);
                // LOG.info(lineSeparator);
                // reset for next elf
                countCalories = 0;
                currentElf++;
            } else {
                // count calories for this elf
                List<String> split = Pattern
                        .compile(" ")
                        .splitAsStream(inventory.get(i))
                        .collect(Collectors.toList());
                int calories = Integer.parseInt(split.get(0));
                countCalories = countCalories + calories;
            }
        }

        LOG.info("top3elvesWithMostCalories - get top 3 from total elves: " + (elfAndCalories.size()));

        int number1 = 0;
        int number2 = 0;
        int number3 = 0;
        int countTop3Calories = 0;

        // sort the list desc - first make a comparator
        Comparator<ElfAndCalorie> compareById = new Comparator<ElfAndCalorie>() {
            @Override
            public int compare(ElfAndCalorie o1, ElfAndCalorie o2) {
                return o1.getCalories().compareTo(o2.getCalories());
            }
        };
        // sort the list desc - use comparator to sort desc
        Collections.sort(elfAndCalories, compareById.reversed());

        int caloriesForTop3ElvesWithMostCalories = 0 ;
        caloriesForTop3ElvesWithMostCalories = elfAndCalories.get(0).calories;
        caloriesForTop3ElvesWithMostCalories =
                caloriesForTop3ElvesWithMostCalories + elfAndCalories.get(1).calories;
        caloriesForTop3ElvesWithMostCalories =
                caloriesForTop3ElvesWithMostCalories + elfAndCalories.get(2).calories;

        LOG.info("top3elvesWithMostCalories - top3 - elf: " + elfAndCalories.get(0).elf + " - " +
                "calories :" + elfAndCalories.get(0).calories );
        LOG.info("top3elvesWithMostCalories - top3 - elf: " + elfAndCalories.get(1).elf + " - " +
                "calories :" + elfAndCalories.get(1).calories );
        LOG.info("top3elvesWithMostCalories - top3 - elf: " + elfAndCalories.get(2).elf + " - " +
                "calories :" + elfAndCalories.get(2).calories );

        LOG.info("top3TotalCalories: " + caloriesForTop3ElvesWithMostCalories );

        return caloriesForTop3ElvesWithMostCalories;
    }

    @AllArgsConstructor
    @Data
    static class ElfAndCalorie {
        private Integer elf;
        private Integer calories;
    }

}
