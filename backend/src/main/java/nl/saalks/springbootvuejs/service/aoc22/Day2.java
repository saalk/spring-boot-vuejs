package nl.saalks.springbootvuejs.service.aoc22;

import nl.saalks.springbootvuejs.service.AdventOfCode;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
public class Day2 implements AdventOfCode {

    /** Day 2 - 1
     * Calculate the score of a score Rock Paper Scissors tournament.
     * opponent has A = rock, B = paper, C = scissors
     * C beats B beats A beats C - win is
     * - 1 for Rock, 2 for Paper, and 3 for Scissors PLUS
     * - 0 if you lost, 3 if the round was a draw, and 6 if you won
     *
     * BTW you have X = rock, Y = paper, Z = scissors
     * @param encryptedStrategyGuide the lines eg "A X"
     * @return calculated score
     */
    public int scoreRockPaperScissors(List<String> encryptedStrategyGuide) {

        LOG.info("Rucksack - total lines: " + encryptedStrategyGuide.size());

        List<RockPaperScissorsRound> rockPaperScissorRounds = new ArrayList<>();
        RockPaperScissorsRound currentRockPaperScissorsRound;

        String opponentHand  = "";
        String yourHand = "";

        int yourTotalScore = 0;

        // for-each loop the file lines
        for (String round : encryptedStrategyGuide) {
            //LOG.info("Rucksack - " + round + " gives : " + score);

            List<String> split = Pattern
                    .compile(" ")
                    .splitAsStream(round)
                    .collect(Collectors.toList());

            opponentHand = split.get(0);
            yourHand = split.get(1); //Integer.parseInt(split.get(1));

            // populate the
            currentRockPaperScissorsRound = switch(yourHand) {
                case "X" -> new RockPaperScissorsRound(opponentHand,"A",0,null);
                case "Y" -> new RockPaperScissorsRound(opponentHand,"B",0,null);
                case "Z" -> new RockPaperScissorsRound(opponentHand,"C",0,null);
                default -> throw new IllegalStateException("Invalid X,Y,Z status: " + yourHand);
            };
            currentRockPaperScissorsRound.yourScore = rockPaperScissorsScoreOfARound(currentRockPaperScissorsRound);
            yourTotalScore = yourTotalScore + currentRockPaperScissorsRound.yourScore;
            LOG.info("Rock Paper Scissors - round: " + round + " score: " + currentRockPaperScissorsRound.yourScore);
            LOG.info("A/X = rock, B/Y = paper, C/Z = scissors " + lineSeparator);
            rockPaperScissorRounds.add(currentRockPaperScissorsRound);
        }

        LOG.info("Rock Paper Scissors - score: " + yourTotalScore);
        return yourTotalScore;
    }

    @AllArgsConstructor
    @Data
    class RockPaperScissorsRound {
        String opponentHand;
        String yourHand;
        int yourScore;
        RoundResult yourRoundResult;
    };

    // A = rock, B = paper, C = scissors
    static int rockPaperScissorsScoreOfARound(RockPaperScissorsRound rockPaperScissorsRound) {
        if (rockPaperScissorsRound.opponentHand.equals(rockPaperScissorsRound.yourHand)) {
            return rockPaperScissorsScoreCalculateRound(rockPaperScissorsRound.yourHand,RoundResult.draw);
        } else if (rockPaperScissorsRound.opponentHand.equals("A")) {
            if (rockPaperScissorsRound.yourHand.equals("B")) {
                return rockPaperScissorsScoreCalculateRound(rockPaperScissorsRound.yourHand, RoundResult.win);
            } else {
                return rockPaperScissorsScoreCalculateRound(rockPaperScissorsRound.yourHand,
                        RoundResult.lose);
            }
        } else if (rockPaperScissorsRound.opponentHand.equals("B")) {
            if (rockPaperScissorsRound.yourHand.equals("C")) {
                return rockPaperScissorsScoreCalculateRound(rockPaperScissorsRound.yourHand, RoundResult.win);
            } else {
                return rockPaperScissorsScoreCalculateRound(rockPaperScissorsRound.yourHand,
                        RoundResult.lose);
            }
        } else {
            // always opponent has C
            if (rockPaperScissorsRound.yourHand.equals("A")) {
                return rockPaperScissorsScoreCalculateRound(rockPaperScissorsRound.yourHand, RoundResult.win);
            } else {
                return rockPaperScissorsScoreCalculateRound(rockPaperScissorsRound.yourHand,
                        RoundResult.lose);
            }
        }
    }
    enum RoundResult {draw, lose, win}
    static int rockPaperScissorsScoreCalculateRound(String shape, RoundResult result) {
        int outcome = 0;
        switch (shape) {
            // Rock is 1
            case "A" -> outcome = 1 + outcomeOfRoundResultValue(result);
            // Paper is 2
            case "B" -> outcome = 2 + outcomeOfRoundResultValue(result);
            // Scissors is 3
            case "C" -> outcome = 3 + outcomeOfRoundResultValue(result);
        }
        LOG.info("Rock Paper Scissors - total outcome with shape 1,2 or 3 added: " + result);

        return outcome;
    }
    static int outcomeOfRoundResultValue(RoundResult roundResult) {
        int result = 0;
        switch (roundResult) {
            case lose -> result = 0;
            case draw -> result = 3;
            case win -> result = 6;
            default -> result = -999999;
        }
        LOG.info("Rock Paper Scissors - result of round value 0,3,6: " + result);

        return result;
    }

    /** day 2 -2
     * opponent has A = rock, B = paper, C = scissors
     * C beats B beats A beats C - win is
     * - 1 for Rock, 2 for Paper, and 3 for Scissors PLUS
     * - 0 if you lost, 3 if the round was a draw, and 6 if you won
     *
     * BTW you have X = you need to lose, Y = you need to draw, Z = you need to win
     * @param encryptedStrategyGuide the lines eg "A X"
     * @return calculated score
     */
    public int scoreRockPaperScissorsSecond(List<String> encryptedStrategyGuide) {

        LOG.info("Rucksack - total lines: " + encryptedStrategyGuide.size());

        List<RockPaperScissorsRound> rockPaperScissorRounds = new ArrayList<>();
        RockPaperScissorsRound currentRockPaperScissorsRound;

        String opponentHand  = "";
        String yourResult = "";

        int yourTotalScore = 0;

        // for-each loop the file lines
        for (String round : encryptedStrategyGuide) {
            //LOG.info("Rucksack - " + round + " gives : " + score);

            List<String> split = Pattern
                    .compile(" ")
                    .splitAsStream(round)
                    .collect(Collectors.toList());

            opponentHand = split.get(0);
            yourResult = split.get(1); //Integer.parseInt(split.get(1));

            // populate the class
            currentRockPaperScissorsRound = switch(yourResult) {
                case "X" -> new RockPaperScissorsRound(opponentHand,"",0,RoundResult.lose);
                case "Y" -> new RockPaperScissorsRound(opponentHand,"",0,RoundResult.draw);
                case "Z" -> new RockPaperScissorsRound(opponentHand,"",0,RoundResult.win);
                default -> throw new IllegalStateException("Invalid X,Y,Z status: " + yourResult);
            };

            currentRockPaperScissorsRound.yourScore = rockPaperScissorsHandOfARound(currentRockPaperScissorsRound);

            yourTotalScore = yourTotalScore + currentRockPaperScissorsRound.yourScore;
            LOG.info("Rock Paper Scissors - round: " + round + " score: " + currentRockPaperScissorsRound.yourScore);
            LOG.info("A/X = rock, B/Y = paper, C/Z = scissors " + lineSeparator);
            rockPaperScissorRounds.add(currentRockPaperScissorsRound);
        }
        LOG.info("Rock Paper Scissors - score: " + yourTotalScore);
        return yourTotalScore;
    }


    // X = you need to lose, Y = you need to draw, Z = you need to win
    // opponent has A = rock, B = paper, C = scissors
    static int rockPaperScissorsHandOfARound(RockPaperScissorsRound rockPaperScissorsRound) {
        if (rockPaperScissorsRound.yourRoundResult.equals(RoundResult.draw)) {
            // draw
            rockPaperScissorsRound.yourHand = rockPaperScissorsRound.opponentHand;
        } else if (rockPaperScissorsRound.opponentHand.equals("A")) {
            // you win with opponent A
            if (rockPaperScissorsRound.yourRoundResult.equals(RoundResult.win)) {
                rockPaperScissorsRound.yourHand = "B";
            } else {
                // you lose with opponent A
                rockPaperScissorsRound.yourHand = "C";
            }
        } else if (rockPaperScissorsRound.opponentHand.equals("B")) {
            // you win with opponent B
            if (rockPaperScissorsRound.yourRoundResult.equals(RoundResult.win)) {
                rockPaperScissorsRound.yourHand = "C";
            } else {
                // you lose with opponent B
                rockPaperScissorsRound.yourHand = "A";
            }
        } else {
            // always opponent has C
            if (rockPaperScissorsRound.yourRoundResult.equals(RoundResult.win)) {
                // you win with opponent C
                rockPaperScissorsRound.yourHand = "A";
            } else {
                // you lose with opponent C
                rockPaperScissorsRound.yourHand = "B";
            }
        }
        return rockPaperScissorsScoreCalculateRound(rockPaperScissorsRound.yourHand,
                rockPaperScissorsRound.yourRoundResult);
    }
}
