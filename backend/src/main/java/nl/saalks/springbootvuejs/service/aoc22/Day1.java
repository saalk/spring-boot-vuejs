package nl.saalks.springbootvuejs.service.aoc22;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.saalks.springbootvuejs.service.AdventOfCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static nl.saalks.springbootvuejs.service.AdventOfCode.*;
import static nl.saalks.springbootvuejs.service.AdventOfCode.convertStringListToIntList;

@Service
public class Day1 implements AdventOfCode {

    /**
     * Returns the nth elf carrying the most calories.
     *
     * @param inventory - the calorie of a series of item eg "1000 ", "400 ", " " for an elf
     * @return the nth elf with most calories
     */
    public int elfWithMostCaloriesSimple(List<String> inventory) {

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

        return elfWithMostCalories;
    }

    /**
     * Returns the times depth increases for a given depth report.
     *
     * @param depthReport the int array
     * @return the times a depth increases
     */
    public int elfWithMostCaloriesForEach(List<String> depthReport) {

        LOG.info("sonarSweep - total lines: " + depthReport.size());

        int previousDepth = 0;

        int timesIncreased = 0;
        int timesNotIncreased = 0;
        int timesSkipped = 0;

        // for-each loop the file lines
        for (String stringDepth : depthReport) {

            int depth = Integer.parseInt(stringDepth);
            if (previousDepth == 0) {
                timesSkipped++;
            } else if (depth > previousDepth) {
                timesIncreased++; // easily add 1
            } else {
                timesNotIncreased++;
            }
            previousDepth = depth;
        }

        LOG.info("sonarSweep - increased: " + timesIncreased);
        LOG.info("sonarSweep - not increased: " + timesNotIncreased);
        LOG.info("sonarSweep - skipped: " + timesSkipped);

        return timesIncreased;
    }

    public int elfWithMostCaloriesStream(List<String> depthReport) {

        LOG.info("sonarSweepStream - total lines: " + depthReport.size());

        List<Integer> intDepthReport = convertStringListToIntList(depthReport, Integer::parseInt);

        int timesIncreased = computeSlidingWindow(intDepthReport, 1);  //Part 1
//        int valuePart2 = compute(intDepthReport, 3); //Part 2

        LOG.info("sonarSweepStream - increased: " + timesIncreased);

        return timesIncreased;
    }

    /**
     * Returns the 3 sliding window depth increases for a given depth report.
     *
     * @param depthReport the int array
     * @return the times a slidingWindow increases
     */
    public int threeMeasurementSlidingWindow(List<String> depthReport) {

        LOG.info("threeMeasurementSlidingWindow - total lines: " + depthReport.size());
        LOG.info("threeMeasurementSlidingWindow - calculated slidingWindows: " + (depthReport.size() - 2));

        List<SlidingWindow> slidingWindows = new ArrayList<>();

        int currentWindowKey = 0;
        int maxWindowKeys = depthReport.size() - 2;

        int olderWindowDepth = 0;
        int oldWindowDepth = 0;

        // for-each - loop the file lines create sliding windows
        for (String stringDepth : depthReport) {
            int depth = Integer.parseInt(stringDepth);
            currentWindowKey++;

            if (currentWindowKey == 1) {
                // first
                slidingWindows.add(new SlidingWindow(currentWindowKey, depth));
            } else if (currentWindowKey == 2) {
                // second
                slidingWindows.add(new SlidingWindow(currentWindowKey, depth));
                // update first
                SlidingWindow oldWindow = slidingWindows.get(currentWindowKey - 2);
                oldWindowDepth = oldWindow.getSum() + depth;
                oldWindow.setSum(oldWindowDepth);
                slidingWindows.set(currentWindowKey - 2, oldWindow);
            } else {
                // new
                if (currentWindowKey <= maxWindowKeys) {
                    slidingWindows.add(new SlidingWindow(currentWindowKey, depth));
                    // update old
                    SlidingWindow oldWindow = slidingWindows.get(currentWindowKey - 2);
                    oldWindowDepth = oldWindow.getSum() + depth;
                    oldWindow.setSum(oldWindowDepth);
                    slidingWindows.set(currentWindowKey - 2, oldWindow);
                    // update older
                    SlidingWindow olderWindow = slidingWindows.get(currentWindowKey - 3);
                    olderWindowDepth = olderWindow.getSum() + depth;
                    olderWindow.setSum(olderWindowDepth);
                    slidingWindows.set(currentWindowKey - 3, olderWindow);
                } else if (currentWindowKey == maxWindowKeys + 1) {
                    // update last but current window key is already + 2
                    SlidingWindow oldWindow = slidingWindows.get(currentWindowKey - 2);
                    oldWindowDepth = oldWindow.getSum() + depth;
                    oldWindow.setSum(oldWindowDepth);
                    slidingWindows.set(currentWindowKey - 2, oldWindow);
                    // update old
                    SlidingWindow olderWindow = slidingWindows.get(currentWindowKey - 3);
                    olderWindowDepth = olderWindow.getSum() + depth;
                    olderWindow.setSum(olderWindowDepth);
                    slidingWindows.set(currentWindowKey - 3, olderWindow);
                } else {
                    // update last but current window key is already + 3
                    SlidingWindow oldWindow = slidingWindows.get(currentWindowKey - 3);
                    oldWindowDepth = oldWindow.getSum() + depth;
                    oldWindow.setSum(oldWindowDepth);
                    slidingWindows.set(currentWindowKey - 3, oldWindow);
                }
            }

        }
        LOG.info("threeMeasurementSlidingWindow - real slidingWindows: " + (slidingWindows.size()));

        int previousSum = 0;
        int timesIncreased = 0;
        int timesNotIncreased = 0;
        int timesSkipped = 0;
        int timesNoChange = 0;

        // for-each - loop the sliding windows
        for (SlidingWindow slidingWindow : slidingWindows) {

            int sum = slidingWindow.getSum();
            if (previousSum == 0) {
                timesSkipped++;
            } else if (sum > previousSum) {
                timesIncreased++; // easily add 1
            } else if (sum == previousSum) {
                timesNoChange++;
            } else {
                timesNotIncreased++;
            }
            previousSum = sum;

            LOG.info("threeMeasurementSlidingWindow - slidingWindows: " + slidingWindow);

        }

        LOG.info("threeMeasurementSlidingWindow - increased: " + timesIncreased);
        LOG.info("threeMeasurementSlidingWindow - not increased: " + timesNotIncreased);
        LOG.info("threeMeasurementSlidingWindow - no change: " + timesNoChange);
        LOG.info("threeMeasurementSlidingWindow - skipped: " + timesSkipped);

        return timesIncreased;
    }

    @AllArgsConstructor
    @Data
    static class SlidingWindow {
        private int mark;
        private int sum;
    }

}
