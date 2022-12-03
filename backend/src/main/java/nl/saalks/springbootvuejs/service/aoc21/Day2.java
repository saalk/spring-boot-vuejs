package nl.saalks.springbootvuejs.service.aoc21;

import nl.saalks.springbootvuejs.service.AdventOfCode;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class Day2 implements AdventOfCode {

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

        LOG.info("aimTheSub - total lines: " + plannedCourse.size());

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
                    LOG.info("aimTheSub - " + split + " gives horizon: " + horizontalPosition);
                    LOG.info("aimTheSub - " + split + " gives depth: " + depth);
                    break;
                case "down":
                    aim = aim+count;
                    LOG.info("aimTheSub - " + split + " gives : " + aim);
                    break;
                case "up":
                    aim = aim-count;
                    LOG.info("aimTheSub - " + split + " gives : " + aim);

                    break;
                default:
                    LOG.info("aimTheSub - " + split + " gives : ?");
            }
        }

        LOG.info("aimTheSub - horizontalPosition: " + horizontalPosition);
        LOG.info("aimTheSub - depth: " + depth);
        LOG.info("aimTheSub - horizontalPosition * depth: " + horizontalPosition*depth);


        return horizontalPosition*depth;
    }

    public int aimTheSubHashMap(List<String> plannedCourse) {

        LOG.info("aimTheSubHashMap - total lines: " + plannedCourse.size());

        HashMap<String, Integer> map = new HashMap();
        map.put("depth", 0);
        map.put("forward", 0);
        map.put("aim", 0);

        // for-each loop the file lines
        for (String line : plannedCourse) {

            String[] split = line.split(" ");

            if(split[0].equals("down")){
                map.put("aim",
                        (map.get("aim") + Integer.parseInt(split[1]))
                );
            } else if (split[0].equals("up")){
                map.put("aim",
                        (map.get("aim") - Integer.parseInt(split[1]))
                );
            } else if (split[0].equals("forward")){
                map.put("forward",
                        (map.get("forward") + Integer.parseInt(split[1]))
                );
                map.put("depth",
                        (map.get("depth") +
                                (map.get("aim") * Integer.parseInt(split[1]))
                        )
                );
            }
        }

        LOG.info("aimTheSubHashMap - horizontalPosition: " + map.get("forward"));
        LOG.info("aimTheSubHashMap - depth: " + map.get("depth"));
        LOG.info("aimTheSubHashMap - horizontalPosition * depth: " + map.get("forward")*map.get("depth"));


        return map.get("forward")*map.get("depth");
    }

}
