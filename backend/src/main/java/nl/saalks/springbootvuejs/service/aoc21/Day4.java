package nl.saalks.springbootvuejs.service.aoc21;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.saalks.springbootvuejs.service.AdventOfCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class Day4 implements AdventOfCode {

    ArrayList<BingoCard> bingoCards = new ArrayList<>();
    ArrayList<NumbersDrawn> numbersDrawns = new ArrayList<>();

    public int[] bingo(List<String> inputLines) {

        // fill both inner classes
        int boardCounter = 0;
        boolean allLinesRead = false;
        OUTER:
        for (int line = 1; line < inputLines.size(); line++) {
            // numbers drawn are on the first line
            if (line == 1) {
                String[] splitArray = inputLines.get(line - 1).split("\\s*,\\s*");
//                LOG.info("bingo - splitArray: " + splitArray.length);
                int[] array = new int[splitArray.length];
                for (int i = 0; i < splitArray.length; i++) {
                    array[i] = Integer.parseInt(splitArray[i]);
                    numbersDrawns.add(new NumbersDrawn(i + 1, array[i]));
//                    LOG.info("bingo - lineSegments: " + lineSegments.get(i));
                }
                continue OUTER;
            }
            // boards are seperated with empty row
            if (inputLines.get(line - 1).length() <= 1) {
                boardCounter++;
                allLinesRead = false;
//                LOG.info("bingo - new card nr: " + boardCounter);
                continue OUTER;
            }
            // boards are in groups of 5
            if (!allLinesRead) {
                ArrayList<String> fiveLines = new ArrayList<>();

                fiveLines.add(inputLines.get(line - 1).trim());
//                LOG.info("bingo - fiveLines: " + fiveLines.get(0));
                fiveLines.add(inputLines.get(line).trim());
//                LOG.info("bingo - fiveLines: " + fiveLines.get(1));
                fiveLines.add(inputLines.get(line + 1).trim());
//                LOG.info("bingo - fiveLines: " + fiveLines.get(2));
                fiveLines.add(inputLines.get(line + 2).trim());
//                LOG.info("bingo - fiveLines: " + fiveLines.get(3));
                fiveLines.add(inputLines.get(line + 3).trim());
//                LOG.info("bingo - fiveLines: " + fiveLines.get(4));

                bingoCards.add(new BingoCard(fiveLines, boardCounter));
                allLinesRead = true;
            }
        }

        int totalBoards = bingoCards.size();
        int totalNumbers = numbersDrawns.size();
        LOG.info("bingo - total bingoCard: " + totalBoards);

        // play bingo
        int boardWins = 0;
        int winningNumber = 0;
        OUTER:
        for (int i = 0; i < totalNumbers; i++) {
            //loop the boards
//            LOG.info("--------------------------");
//            LOG.info("bingo - number: " + lineSegments.get(i).getNumber());
            for (BingoCard bingoCard : bingoCards) {
                bingoCard.markNumber(numbersDrawns.get(i).getNumber());
            }
            // evaluate the boards
            int rowsComplete = 0;
            int columnsComplete = 0;
            for (BingoCard bingoCard : bingoCards) {
                bingoCard.checkRowsAndScore(numbersDrawns.get(i).getNumber(),
                        numbersDrawns.get(i).getOrder());
                bingoCard.checkColumnsAndScore(numbersDrawns.get(i).getNumber(),
                        numbersDrawns.get(i).getOrder());
            }
        }

        Collections.sort(bingoCards);
        int count = 0;
        for (BingoCard bingoCard : bingoCards) {
            LOG.info("bingoCard - order: " + count + " bingoCard sorted: " + bingoCard);
            count++;
        }

//        for (LineSegment numbersDrawn : lineSegments) {
//            LOG.info("bingoCard - numbersDrawn sorted: " + numbersDrawn);
//        }

        int finalScoreFirst = bingoCards.get(0).getScore();;
        int finalScoreLast = bingoCards.get(bingoCards.size()-1).getScore();;
        LOG.info("bingo - bingoCard finalScore first board: " + finalScoreFirst);
        LOG.info("bingo - bingoCard finalScore last board: " + finalScoreLast);

        return new int[]{ finalScoreFirst, finalScoreLast};
    }

    @Data
    class BingoCard implements Comparable<BingoCard> {
        private int boardNumber;
        private int[][] number = new int[5][5];
        private boolean[][] isMarked = new boolean[5][5];
        private boolean hasCompleteRow;
        private boolean hasCompleteColumn;
        private int totalAllUnmarkedNumbers;
        private int winningNumber;
        private int winningNumberSequence;
        private int score;
        private boolean winningCard;

        public BingoCard(ArrayList<String> fiveByFive, int sequence) {
            this.boardNumber = sequence;
            this.hasCompleteRow = false;
            this.hasCompleteColumn = false;
            this.totalAllUnmarkedNumbers = 0;
            this.winningNumber = 0;
            this.winningNumberSequence = 0;
            this.score = 0;
            this.winningCard = false;

            for (int row = 0; row < 5; row++) {
                String[] splitArray = fiveByFive.get(row).split(" +");
                int[] array = new int[splitArray.length];
                for (int column = 0; column < 5; column++) {
                    array[column] = Integer.parseInt(splitArray[column]);
                    number[row][column] = array[column];
                    isMarked[row][column] = false;
                }
            }
        }

        void markNumber(int drawn) {
            for (int row = 0; row < 5; row++) {
                for (int column = 0; column < 5; column++) {
                    if (this.number[row][column] == drawn) {
                        isMarked[row][column] = true;
                    }
                }
            }
        }

        int checkRowsAndScore(int number,int sequence) {
            int rowsComplete = 0;
            int count = 0;
            for (int row = 0; row < 5; row++) {
                count = 0;
                for (int column = 0; column < 5; column++) {
                    if (this.isMarked[row][column] == true) {
                        count++;
                    }
                }
                if (count == 5) {
                    this.hasCompleteRow = true;
//                    LOG.info("bingo - hasCompleteRow: " + row);
                    rowsComplete++;
                }
            }
            if (!this.winningCard && this.hasCompleteRow) {
                this.totalAllUnmarkedNumbers = this.calcAllUnmarkedNumbers(number);
                this.winningNumber = number;
                this.winningNumberSequence = sequence;
                this.score = this.totalAllUnmarkedNumbers * this.winningNumber;
                this.winningCard = true;

            }
            return rowsComplete;
        }

        int checkColumnsAndScore(int number,int sequence) {
            int columnsComplete = 0;
            int count = 0;
            for (int column = 0; column < 5; column++) {
                count = 0;
                for (int row = 0; row < 5; row++) {
                    if (this.isMarked[row][column]) {
                        count++;
                    }
                }
                if (count == 5) {
                    this.hasCompleteColumn = true;
//                    LOG.info("bingo - hasCompleteColumn: " + column);
                    columnsComplete++;
                }
            }
            if (!this.winningCard && this.hasCompleteColumn) {
                this.totalAllUnmarkedNumbers = this.calcAllUnmarkedNumbers(number);
                this.winningNumber = number;
                this.winningNumberSequence = sequence;
                this.score = this.totalAllUnmarkedNumbers * this.winningNumber;
                this.winningCard = true;
            }
            return columnsComplete;
        }

        int calcAllUnmarkedNumbers(int number) {
            int count = 0;
            for (int row = 0; row < 5; row++) {
                for (int column = 0; column < 5; column++) {
                    if (!this.isMarked[row][column]) {
                        count += this.number[row][column];
                    }
                }
            }
            return count;
        }

        // overriding the compareTo method of Comparable class
        @Override public int compareTo(BingoCard compareBingoCard)
        {
            int winningNumberSeq = ((BingoCard)compareBingoCard).getWinningNumberSequence();

            //  For Ascending order
            return this.winningNumberSequence - winningNumberSeq;

            // For Descending order do like this
//             return winningNumberSeq-this.winningNumberSequence;
        }

        @Override public String toString()
        {
            return "[ boardNumber=" + boardNumber + ", winningNumberSequence="
                    + winningNumberSequence + ", score=" + score + "]";
        }

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    class NumbersDrawn {
        private int order;
        private int number;

        @Override public String toString()
        {
            return "[ order=" + order + ", number="
                    + number + " ]";
        }
    }
}
