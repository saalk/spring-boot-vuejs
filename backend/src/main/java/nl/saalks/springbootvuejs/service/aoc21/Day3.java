package nl.saalks.springbootvuejs.service.aoc21;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.saalks.springbootvuejs.service.AdventOfCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class Day3 implements AdventOfCode {

    ArrayList<BinaryNumbers> binaryNumbers = new ArrayList<>();
    ArrayList<CommonBit> mostCommonBits = new ArrayList<>();
    ArrayList<CommonBit> leastCommonBits = new ArrayList<>();


    public void calculateMostOrLeastCommonBitCriteriaForSpecificPosition(int position, MostOrLeast mostOrLeast) {

        LOG.info("calculateMostOrLeastCommon - begin - input: " + (position+1) + " - " + mostOrLeast );

        int binaryNumberArraySize = this.binaryNumbers.size();
        CommonBit commonBitToUpdate = new CommonBit(position, 0, 0, 0);

        // loop for all binaryNumbers eg 1000 in p
        LOOP:
        for (int b = 0; b < binaryNumberArraySize; b++) {
            // Skip needed?
            if (mostOrLeast == MostOrLeast.MOST && binaryNumbers.get(b).isSkipForMostCommonBit()) {
                LOG.info("calculateMostOrLeastCommon - skip most: " + (b+1) + " - " + binaryNumbers.get(b));
                continue LOOP;
            }
            if (mostOrLeast == MostOrLeast.LEAST && binaryNumbers.get(b).isSkipForLeastCommonBit()) {
                LOG.info("calculateMostOrLeastCommon - skip least: " + (b+1) + " - " + binaryNumbers.get(b));
                continue LOOP;
            }
            commonBitToUpdate.setCommonBitsToConsider(commonBitToUpdate.getCommonBitsToConsider() + 1);
            commonBitToUpdate.setCountOneBits(commonBitToUpdate.getCountOneBits() +
                    binaryNumbers.get(b).getBinaryNumber()[position]);
        }
        LOG.info("calculateMostOrLeastCommon - end all binaryNumbers");

        int getCountZeroBits =
                commonBitToUpdate.getCommonBitsToConsider()-commonBitToUpdate.getCountOneBits();
        if (commonBitToUpdate.getCountOneBits() >= getCountZeroBits) {
            commonBitToUpdate.setCommonBit(mostOrLeast == MostOrLeast.MOST ? 1 : 0);
        } else {
            commonBitToUpdate.setCommonBit(mostOrLeast == MostOrLeast.MOST ? 0 : 1);
        }
        LOG.info("calculateMostOrLeastCommon - commonBitToUpdate: " + commonBitToUpdate);

        if (mostOrLeast == MostOrLeast.MOST) {
            mostCommonBits.add(commonBitToUpdate);
            LOG.info("calculateMostOrLeastCommon - mostNumbersDrawns: " + mostCommonBits);

        } else {
            leastCommonBits.add(commonBitToUpdate);
            LOG.info("calculateMostOrLeastCommon - leastNumbersDrawns: " + leastCommonBits);
        }
        LOG.info("calculateMostOrLeastCommon - end method: " + (position+1) + " - " + mostOrLeast );

    }

    public int powerConsumption(List<String> binaryNumbers, int binaryLength) {

        int totalBinaryNumbers = binaryNumbers.size();
        LOG.info("binaryDiagnostic - total lines: " + totalBinaryNumbers);

        int[] countOneBits = new int[binaryLength];
        int[] mostCommonBitArray = new int[binaryLength];
        int[] leastCommonBitArray = new int[binaryLength];

        String mostCommonBit = "     ";
        String leastCommonBit = "     ";

        int mostCommonBitToDecimal = 0;
        int leastCommonBitToDecimal = 0;

        int gammaRate = 0;
        int epsilonRate = 0;

        int powerConsumption = 0;

        // count one bits
        for (String binary : binaryNumbers) {
            for (int i = 0; i < binaryLength; i++) {
                int bit = Integer.parseInt(binary.substring(i, i + 1));
                countOneBits[i] += bit;
            }
            LOG.info("binaryDiagnostic - binary: " + binary);
            LOG.info("binaryDiagnostic - countOneBits: " + Arrays.toString(countOneBits));
        }

        // most and least common bits
        for (int i = 0; i < binaryLength; i++) {
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

    public int lifeSupportRating(ArrayList<String> binaryNumbersGiven) {

        int binaryLength = binaryNumbersGiven.size();
        int binaryNumberLength = binaryNumbersGiven.get(0).length();

        LOG.info("lifeSupportRating - total lines: " + binaryNumbersGiven.size());
        String oxygenGeneratorRatingBinary = "";
        String CO2scrubberRatingBinary = "";

        // fill the local binary numbers with given binary number list
        for (int b = 0; b < binaryLength; b++) {
            binaryNumbers.add(new BinaryNumbers(binaryNumbersGiven.get(b), b));
            LOG.info("lifeSupportRating - binaryNumbersGiven: " + binaryNumbersGiven.get(b));
            LOG.info("lifeSupportRating - binaryNumbers: " + binaryNumbers.get(b));
        }

        OUTER:
        // do max 12 = each position once times
        for (int p = 0; p < binaryNumberLength; p++) {

            // calc the most adn lest common bit for current position
            calculateMostOrLeastCommonBitCriteriaForSpecificPosition(p, MostOrLeast.MOST);
            calculateMostOrLeastCommonBitCriteriaForSpecificPosition(p, MostOrLeast.LEAST);

            // update the skip flag for most and least common for all eg 1000
            for (int b = 0; b < binaryLength; b++) {
                if (binaryNumbers.get(b).binaryNumber[p] != mostCommonBits.get(p).commonBit) {
                    binaryNumbers.get(b).setSkipForMostCommonBit(true);
                    LOG.info("lifeSupportRating - binaryNumber pos" + (b+1) + " skip for most " +
                            "common: "
                            + binaryNumbers.get(b).getSequence() + " - "
                            + Arrays.toString(binaryNumbers.get(b).getBinaryNumber()));
                }
                if (binaryNumbers.get(b).binaryNumber[p] != leastCommonBits.get(p).commonBit) {
                    binaryNumbers.get(b).setSkipForLeastCommonBit(true);
                    LOG.info("lifeSupportRating - binaryNumber pos" + (b+1) + " skip for least " +
                            "common: "
                            + binaryNumbers.get(b).getSequence() + " - "
                            + Arrays.toString(binaryNumbers.get(b).getBinaryNumber()));
                }
            }

            // check if only one is present ?
            int mostCommonCount = (int) binaryNumbers.stream().filter(a -> !a.isSkipForMostCommonBit()).count();
            int leastCommonCount = (int) binaryNumbers.stream().filter(a -> !a.isSkipForLeastCommonBit()).count();

            if (mostCommonCount == 1) {
                Optional<BinaryNumbers> mostCommon = binaryNumbers.stream().filter(a -> !a.isSkipForMostCommonBit()).findFirst();
                if (mostCommon.isPresent()) {
                    oxygenGeneratorRatingBinary = Arrays.toString(mostCommon.get().binaryNumber).replaceAll("\\[|\\]|,|\\s", "");;
                    LOG.info("lifeSupportRating - oxygenGeneratorRatingBinary only one: " + oxygenGeneratorRatingBinary);
                }
            }
            if (leastCommonCount == 1) {
                Optional<BinaryNumbers> leastCommon =
                        binaryNumbers.stream().filter(a -> !a.isSkipForLeastCommonBit()).findFirst();
                if (leastCommon.isPresent()) {
                    CO2scrubberRatingBinary = Arrays.toString(leastCommon.get().binaryNumber).replaceAll("\\[|\\]|,|\\s", "");;
                    LOG.info("lifeSupportRating - CO2scrubberRatingBinary only one: " + CO2scrubberRatingBinary);
                }
            }

            if (mostCommonCount == 1 && leastCommonCount == 1) {
//            if (mostCommonCount == 1) {
                break OUTER;
            }
        }

        // decimal
        int oxygenGeneratorRating = Integer.parseInt(oxygenGeneratorRatingBinary, 2);
        int CO2scrubberRating = Integer.parseInt(CO2scrubberRatingBinary, 2);

        LOG.info("lifeSupportRating - oxygenGeneratorRating: " + oxygenGeneratorRating);
        LOG.info("lifeSupportRating - CO2scrubberRating: " + CO2scrubberRating);

        int lifeSupportRating = oxygenGeneratorRating * CO2scrubberRating;
        LOG.info("lifeSupportRating - lifeSupportRating: " + lifeSupportRating);

        return lifeSupportRating;
    }

    enum MostOrLeast {MOST, LEAST}

    @Data
    class BinaryNumbers {
        private int[] binaryNumber;
        private int sequence;
        private boolean skipForMostCommonBit;
        private String mostCommonBit;
        private boolean skipForLeastCommonBit;
        private String leastCommonBit;
        private boolean theOneLeftForOxygen;
        private boolean theOneLeftForCO2;

        public BinaryNumbers(String binaryNumbers, int position) {
            this.binaryNumber = new int[binaryNumbers.length()];
            for (int i = 0; i < binaryNumbers.length(); i++) {
                this.binaryNumber[i] = Integer.parseInt(binaryNumbers.substring(i, i + 1));
            }
            this.sequence = position;
            this.skipForMostCommonBit = false;
            this.mostCommonBit = "";
            this.skipForLeastCommonBit = false;
            this.leastCommonBit = "";
            for (int i = 0; i < binaryNumbers.length(); i++) {
                this.mostCommonBit =
                        this.mostCommonBit.substring(0, i) + "?";
                this.leastCommonBit =
                        this.leastCommonBit.substring(0, i) + "?";
            }
            this.theOneLeftForOxygen = false;
            this.theOneLeftForCO2 = false;
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    class CommonBit {
        private int position;
        private int countOneBits;
        private int commonBitsToConsider;
        private int commonBit;
    }
}
