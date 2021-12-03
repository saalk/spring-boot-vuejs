package nl.saalks.springbootvuejs.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class Day3 implements AdventOfCode {

    // https://www.deadcoderising.com/java-8-no-more-loops/

    public int powerConsumption(List<String> binaryNumbers, int binaryLenght) {

        int totalBinaryNumbers = binaryNumbers.size();
        LOG.info("binaryDiagnostic - total lines: " + totalBinaryNumbers);

        int[] countOneBits = new int[binaryLenght];
        int[] mostCommonBitArray = new int[binaryLenght];
        int[] leastCommonBitArray = new int[binaryLenght];

        String mostCommonBit = "     ";
        String leastCommonBit = "     ";

        int mostCommonBitToDecimal = 0;
        int leastCommonBitToDecimal = 0;

        int gammaRate = 0;
        int epsilonRate = 0;

        int powerConsumption = 0;

        // count one bits
        for (String binary : binaryNumbers) {
            for (int i = 0; i < binaryLenght; i++) {
                int bit = Integer.parseInt(binary.substring(i, i + 1));
                countOneBits[i] += bit;
            }
            LOG.info("binaryDiagnostic - binary: " + binary);
            LOG.info("binaryDiagnostic - countOneBits: " + Arrays.toString(countOneBits));
        }

        // most and least common bits
        for (int i = 0; i < binaryLenght; i++) {
            int oneBits = countOneBits[i];
            int zeroBits = totalBinaryNumbers - countOneBits[i];

            if (oneBits >= zeroBits) {
                mostCommonBitArray[i] = 1;
            } else {
                leastCommonBitArray[i] = 1;
            }
        }
        LOG.info("binaryDiagnostic - mostCommonBitArray: " + Arrays.toString(mostCommonBitArray));
        LOG.info("binaryDiagnostic - leastCommonBitArray: " + Arrays.toString(leastCommonBitArray));

        // common bit - use regex to exlude "[" and "]" and "," from string
        mostCommonBit = Arrays.toString(mostCommonBitArray).replaceAll("\\[|\\]|,|\\s", "");
        leastCommonBit = Arrays.toString(leastCommonBitArray).replaceAll("\\[|\\]|,|\\s", "");
        LOG.info("binaryDiagnostic - mostCommonBit: " + mostCommonBit);
        LOG.info("binaryDiagnostic - leastCommonBit: " + leastCommonBit);

        // decimal
        gammaRate = Integer.parseInt(mostCommonBit, 2);
        epsilonRate = Integer.parseInt(leastCommonBit, 2);
        LOG.info("binaryDiagnostic - gammaRate: " + gammaRate);
        LOG.info("binaryDiagnostic - epsilonRate: " + epsilonRate);

        powerConsumption = gammaRate * epsilonRate;
        LOG.info("binaryDiagnostic - powerConsumption: " + powerConsumption);

        return powerConsumption;
    }

    public int lifeSupportRating(List<String> binaryNumbers, int binaryLenght) {

        int totalBinaryNumbers = binaryNumbers.size();
        LOG.info("binaryDiagnostic - total lines: " + totalBinaryNumbers);

        int[] countOneBits = new int[binaryLenght];
        int[] mostCommonBitArray = new int[binaryLenght];
        int[] leastCommonBitArray = new int[binaryLenght];

        String mostCommonBit = "     ";
        String leastCommonBit = "     ";

        int mostCommonBitToDecimal = 0;
        int leastCommonBitToDecimal = 0;

        int oxygenGeneratorRating = 0;
        int CO2scrubberRating = 0;

        int lifeSupportRating = 0;

        // count the first bit, then second, etc
        START_LEFT:
        for (int i = 0; i < binaryLenght; i++)

            // count the one bits at postion i
            LOOP_ALL_NUMBERS:
            for (String binary : binaryNumbers) {
                {
                    int bit = Integer.parseInt(binary.substring(i, i + 1));
                    countOneBits[i] += bit;
                }
                LOG.info("binaryDiagnostic - binary: " + binary + "at position: " + i);
                LOG.info("binaryDiagnostic - countOneBits: " + Arrays.toString(countOneBits));
            }

        // most and least common bits
        for (int i = 0; i < binaryLenght; i++) {
            int oneBits = countOneBits[i];
            int zeroBits = totalBinaryNumbers - countOneBits[i];

            if (oneBits >= zeroBits) {
                mostCommonBitArray[i] = 1;
            } else {
                leastCommonBitArray[i] = 1;
            }
        }
        LOG.info("binaryDiagnostic - mostCommonBitArray: " + Arrays.toString(mostCommonBitArray));
        LOG.info("binaryDiagnostic - leastCommonBitArray: " + Arrays.toString(leastCommonBitArray));

        // common bit - use regex to exlude "[" and "]" and "," from string
        mostCommonBit = Arrays.toString(mostCommonBitArray).replaceAll("\\[|\\]|,|\\s", "");
        leastCommonBit = Arrays.toString(leastCommonBitArray).replaceAll("\\[|\\]|,|\\s", "");
        LOG.info("binaryDiagnostic - mostCommonBit: " + mostCommonBit);
        LOG.info("binaryDiagnostic - leastCommonBit: " + leastCommonBit);

        // decimal
        oxygenGeneratorRating = Integer.parseInt(mostCommonBit, 2);
        CO2scrubberRating = Integer.parseInt(leastCommonBit, 2);
        LOG.info("binaryDiagnostic - oxygenGeneratorRating: " + oxygenGeneratorRating);
        LOG.info("binaryDiagnostic - CO2scrubberRating: " + CO2scrubberRating);

        lifeSupportRating = oxygenGeneratorRating * CO2scrubberRating;
        LOG.info("binaryDiagnostic - lifeSupportRating: " + lifeSupportRating);

        return lifeSupportRating;
    }

}
