package nl.saalks.springbootvuejs.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class Day2 implements AdventOfCode {

    @AllArgsConstructor
    @Data
    static class SlidingWindow {
        private int mark;
        private int sum;
    }

    /**
     * Calculate the horizontal position and depth.
     * @param plannedCourse the lines eg "forward 5"
     * @return multiply your final horizontal position by your final depth
     */
    public int pilotTheSub(List<String> plannedCourse) {

        LOG.info("pilotTheSub - total lines: " + plannedCourse.size());

        int horizontalPosition = 0;
        int depth = 0;


        // for-each loop the file lines
        for (String step : plannedCourse) {

            List<String> split = Pattern
                    .compile(" ")
                    .splitAsStream(step)
                    .collect(Collectors.toList());

            String command = split.get(0);
            int count = Integer.parseInt(split.get(1));

            switch(command) {
                case "forward":
                    horizontalPosition = horizontalPosition+count;
                    LOG.info("pilotTheSub - " + split + " gives : " + horizontalPosition);
                    break;
                case "down":
                    depth = depth+count;
                    LOG.info("pilotTheSub - " + split + " gives : " + depth);
                    break;
                case "up":
                    depth = depth-count;
                    LOG.info("pilotTheSub - " + split + " gives : " + depth);

                    break;
                default:
                    LOG.info("pilotTheSub - " + split + " gives : ?");
            }
        }

        LOG.info("pilotTheSub - horizontalPosition: " + horizontalPosition);
        LOG.info("pilotTheSub - depth: " + depth);
        LOG.info("pilotTheSub - horizontalPosition * depth: " + horizontalPosition*depth);


        return horizontalPosition*depth;
    }

    public int aimTheSub(List<String> plannedCourse) {

        LOG.info("pilotTheSub - total lines: " + plannedCourse.size());

        int horizontalPosition = 0;
        int depth = 0;
        int aim = 0;


        // for-each loop the file lines
        for (String step : plannedCourse) {

            List<String> split = Pattern
                    .compile(" ")
                    .splitAsStream(step)
                    .collect(Collectors.toList());

            String command = split.get(0);
            int count = Integer.parseInt(split.get(1));

            switch(command) {
                case "forward":
                    horizontalPosition = horizontalPosition+count;
                    depth = depth + (aim * count);
                    LOG.info("pilotTheSub - " + split + " gives horizon: " + horizontalPosition);
                    LOG.info("pilotTheSub - " + split + " gives depth: " + depth);
                    break;
                case "down":
                    aim = aim+count;
                    LOG.info("pilotTheSub - " + split + " gives : " + aim);
                    break;
                case "up":
                    aim = aim-count;
                    LOG.info("pilotTheSub - " + split + " gives : " + aim);

                    break;
                default:
                    LOG.info("pilotTheSub - " + split + " gives : ?");
            }
        }

        LOG.info("pilotTheSub - horizontalPosition: " + horizontalPosition);
        LOG.info("pilotTheSub - depth: " + depth);
        LOG.info("pilotTheSub - horizontalPosition * depth: " + horizontalPosition*depth);


        return horizontalPosition*depth;
    }

}
