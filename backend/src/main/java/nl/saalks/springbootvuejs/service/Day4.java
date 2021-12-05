package nl.saalks.springbootvuejs.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Day4 implements AdventOfCode {

    ArrayList<BingoCard> bingoCards = new ArrayList<>();
    ArrayList<NumbersDrawn> numbersDrawns = new ArrayList<>();

    public int bingo(List<String> inputLines) {

        // fill both inner classes
        int boardCounter = 0;
        boolean allLinesRead = false;
        OUTER:
        for (int line = 1; line < inputLines.size(); line++) {
            // numbers drawn are on the first line
            if (line == 1) {
                String[] splitArray = inputLines.get(line-1).split("\\s*,\\s*");
//                LOG.info("bingo - splitArray: " + splitArray.length);
                int[] array = new int[splitArray.length];
                for (int i = 0; i < splitArray.length; i++) {
                    array[i] = Integer.parseInt(splitArray[i]);
                    numbersDrawns.add(new NumbersDrawn(i + 1, array[i]));
//                    LOG.info("bingo - numbersDrawns: " + numbersDrawns.get(i));
                }
                continue OUTER;
            }
            // boards are seperated with empty row
            if (inputLines.get(line-1).length() <= 1) {
                boardCounter++;
                allLinesRead = false;
//                LOG.info("bingo - new card nr: " + boardCounter);
                continue OUTER;
            }
            // boards are in groups of 5
            if (!allLinesRead) {
                ArrayList<String> fiveLines = new ArrayList<>();

                fiveLines.add(inputLines.get(line-1).trim());
//                LOG.info("bingo - fiveLines: " + fiveLines.get(0));
                fiveLines.add(inputLines.get(line).trim());
//                LOG.info("bingo - fiveLines: " + fiveLines.get(1));
                fiveLines.add(inputLines.get(line+1).trim());
//                LOG.info("bingo - fiveLines: " + fiveLines.get(2));
                fiveLines.add(inputLines.get(line+2).trim());
//                LOG.info("bingo - fiveLines: " + fiveLines.get(3));
                fiveLines.add(inputLines.get(line+3).trim());
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
            LOG.info("--------------------------");
            LOG.info("bingo - number: " + numbersDrawns.get(i).getNumber());
            for (BingoCard bingoCard : bingoCards) {
                bingoCard.markNumber(numbersDrawns.get(i).getNumber());
            }
            // evaluate the boards
            int rowsComplete = 0;
            int columnsComplete = 0;
            for (BingoCard bingoCard : bingoCards) {
//                LOG.info("bingo - total bingoCard no: " + bingoCard.getBoardNumber());
                rowsComplete = bingoCard.checkRows();
//                LOG.info("bingo - rowsComplete: " + rowsComplete);
                columnsComplete = bingoCard.checkColumns();
//                LOG.info("bingo - columnsComplete: " + columnsComplete);
                if (rowsComplete > 0) {
                    boardWins = bingoCard.getBoardNumber();
                    winningNumber = numbersDrawns.get(i).getNumber();
                    break OUTER;
                }
                if (columnsComplete > 0) {
                    boardWins = bingoCard.getBoardNumber();
                    winningNumber = numbersDrawns.get(i).getNumber();
                    break OUTER;
                }
            }
        }

        LOG.info("bingo - boardWins: " + boardWins);
        LOG.info("bingo - winningNumber: " + winningNumber);
        int score = bingoCards.get(boardWins-1).calcAllUnmarkedNumbers();
        LOG.info("bingo - bingoCard score: " + score);

        int finalScore = score * winningNumber;
        LOG.info("bingo - bingoCard finalScore: " + finalScore);

        return finalScore;
    }

    @Data
    class BingoCard {
        private int boardNumber;
        private int[][] number = new int[5][5];
        private boolean[][] isMarked = new boolean[5][5];
        private boolean hasCompleteRow;
        private boolean hasCompleteColumn;
        public BingoCard(ArrayList<String> fiveByFive, int sequence) {
            this.boardNumber = sequence;
            this.hasCompleteRow = false;
            this.hasCompleteColumn = false;
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
        int checkRows() {
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
            return rowsComplete;
        }
        int checkColumns() {
            int columnsComplete = 0;
            int count = 0;
            for (int column = 0; column < 5; column++) {
                count = 0;
                for (int row = 0; row < 5; row++) {
                    if (this.isMarked[row][column] == true) {
                        count++;
                    }
                }
                if (count == 5) {
                    this.hasCompleteColumn = true;
//                    LOG.info("bingo - hasCompleteColumn: " + column);
                    columnsComplete++;
                }
            }
            return columnsComplete;
        }
        int calcAllUnmarkedNumbers() {
            int count = 0;
            for (int row = 0; row < 5; row++) {
                for (int column = 0; column < 5; column++) {
                    LOG.info("bingo - number: " + this.number[row][column]);
                    LOG.info("bingo - marked: " + this.isMarked[row][column]);

                    if (this.isMarked[row][column] == false) {
                        count += this.number[row][column];
                        LOG.info("bingo - count: " + count);
                    }
                }
            }
            return count;
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    class NumbersDrawn {
        private int order;
        private int number;
    }
}
