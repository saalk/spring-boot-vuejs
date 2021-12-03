package nl.saalks.springbootvuejs.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class Day3 implements AdventOfCode {

    // https://www.deadcoderising.com/java-8-no-more-loops/

    public int binaryDiagnostic(List<String> binaryNumbers) {

        int totalBinaryNumbers = binaryNumbers.size();
        LOG.info("binaryDiagnostic - total lines: " + totalBinaryNumbers);

        int[] countOneBits = new int[5];
        int[] mostCommonBitArray = new int[5];
        int[] leastCommonBitArray = new int[5];

        String mostCommonBit = "     ";
        String leastCommonBit = "     ";

        int mostCommonBitToDecimal = 0;
        int leastCommonBitToDecimal = 0;

        int gammaRate = 0;
        int epsilonRate = 0;

        int powerConsumption = 0;

        // count one bits
        for (String binary : binaryNumbers) {
            int bit = 0;
            for (int i = 0; i < 5; i++) {
                bit = Integer.parseInt(binary.substring(i, 1));
                countOneBits[i] += bit;
            }
            LOG.info("binaryDiagnostic - countOneBits: " + countOneBits);
        }

        // most and least common bits
        for (int i = 0; i < 5; i++) {
            int oneBits = countOneBits[i];
            int zeroBits = totalBinaryNumbers - countOneBits[i];

            if (oneBits >= zeroBits) {
                mostCommonBitArray[i] = 1;
            } else {
                leastCommonBitArray[i] = 1;
            }
            LOG.info("binaryDiagnostic - mostCommonBitArray: " + mostCommonBitArray);
            LOG.info("binaryDiagnostic - leastCommonBitArray: " + leastCommonBitArray);
        }

        // common bit
        mostCommonBit = Arrays.toString(mostCommonBitArray);
        leastCommonBit = Arrays.toString(leastCommonBitArray);
        LOG.info("binaryDiagnostic - mostCommonBit: " + mostCommonBit);
        LOG.info("binaryDiagnostic - leastCommonBit: " + leastCommonBit);

        // decimal
        gammaRate = Integer.parseInt(mostCommonBit,2);
        epsilonRate = Integer.parseInt(leastCommonBit,2);
        LOG.info("binaryDiagnostic - gammaRate: " + gammaRate);
        LOG.info("binaryDiagnostic - epsilonRate: " + epsilonRate);

        powerConsumption = gammaRate * epsilonRate;
        LOG.info("binaryDiagnostic - powerConsumption: " + powerConsumption);

        return powerConsumption;
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

            switch (command) {
                case "forward":
                    horizontalPosition = horizontalPosition + count;
                    depth = depth + (aim * count);
                    LOG.info("aimTheSub - " + split + " gives horizon: " + horizontalPosition);
                    LOG.info("aimTheSub - " + split + " gives depth: " + depth);
                    break;
                case "down":
                    aim = aim + count;
                    LOG.info("aimTheSub - " + split + " gives : " + aim);
                    break;
                case "up":
                    aim = aim - count;
                    LOG.info("aimTheSub - " + split + " gives : " + aim);

                    break;
                default:
                    LOG.info("aimTheSub - " + split + " gives : ?");
            }
        }

        LOG.info("aimTheSub - horizontalPosition: " + horizontalPosition);
        LOG.info("aimTheSub - depth: " + depth);
        LOG.info("aimTheSub - horizontalPosition * depth: " + horizontalPosition * depth);

        return horizontalPosition * depth;
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

            if (split[0].equals("down")) {
                map.put("aim",
                        (map.get("aim") + Integer.parseInt(split[1]))
                );
            } else if (split[0].equals("up")) {
                map.put("aim",
                        (map.get("aim") - Integer.parseInt(split[1]))
                );
            } else if (split[0].equals("forward")) {
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
        LOG.info("aimTheSubHashMap - horizontalPosition * depth: " + map.get("forward") * map.get("depth"));

        return map.get("forward") * map.get("depth");
    }

}
