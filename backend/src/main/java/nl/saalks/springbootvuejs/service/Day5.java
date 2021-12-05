package nl.saalks.springbootvuejs.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class Day5 implements AdventOfCode {

    ArrayList<LineSegment> lineSegments = new ArrayList<>();
    ArrayList<Diagram> diagrams = new ArrayList<>();

    public int overlap(List<String> inputLines) {

        // fill both inner classes
        for (int line = 1; line < inputLines.size(); line++) {
            lineSegments.add(new LineSegment(line, inputLines.get(line-1)));
            LOG.info("bingo - lineSegments: " + lineSegments.get(line-1));
        }

        int totalNumbers = lineSegments.size();
        LOG.info("bingo - total lineSegments: " + totalNumbers);

        // make diagram
        for (int i = 0; i < totalNumbers; i++) {
            //loop the diagrams
//            LOG.info("--------------------------");
//            LOG.info("bingo - number: " + lineSegments.get(i).getNumber());
        }

        int finalScoreFirst = 0;
        LOG.info("bingo - diagramLines finalScoreFirst: " + finalScoreFirst);
        return finalScoreFirst;
    }

    @Data
    class Diagram implements Comparable<Diagram> {
        private int diagramNumber;
        private int[][] number = new int[5][5];
        private boolean[][] isMarked = new boolean[5][5];
        private boolean hasCompleteRow;
        private boolean hasCompleteColumn;
        private int totalAllUnmarkedNumbers;
        private int winningNumber;
        private int winningNumberSequence;
        private int score;
        private boolean winningCard;

        public Diagram(ArrayList<String> fiveByFive, int sequence) {
            this.diagramNumber = sequence;
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

        // overriding the compareTo method of Comparable class
        @Override
        public int compareTo(Diagram compareDiagram) {
            int winningNumberSeq = ((Diagram) compareDiagram).getWinningNumberSequence();

            //  For Ascending order
            return this.winningNumberSequence - winningNumberSeq;

            // For Descending order do like this
//             return winningNumberSeq-this.winningNumberSequence;
        }
        @Override
        public String toString() {
            return "[ diagramNumber=" + diagramNumber + ", winningNumberSequence="
                    + winningNumberSequence + ", score=" + score + "]";
        }
    }

    @NoArgsConstructor
    @Data
    class LineSegment {
        private int order;
        private int startX;
        private int startY;
        private int endX;
        private int endY;
        private double[][] points;

        public LineSegment(int order, String entry) {
            this.order = order;
            deriveXandY(entry);
            calculatePoints();
        }

        void deriveXandY(String entry) {

            String[] points = entry.split("\\s*->\\s*");
            String[] startPoint = points[0].split("\\s*,\\s*");
            String[] endPoint = points[1].split("\\s*,\\s*");

            this.startX = Integer.parseInt(startPoint[0]);
            this.startY = Integer.parseInt(startPoint[1]);

            this.endX = Integer.parseInt(endPoint[0]);
            this.endY = Integer.parseInt(endPoint[1]);

        }

        void calculatePoints() {
            this.points = new double[endX][endY];
            for (int x = startX; x <= endX; x++) {
                for (int y = startY; y <= endY; y++) {
                    points[x][y] = 1;
                }
            }
        }

        @Override
        public String toString() {
            return "[ order=" + order +
                    ", startX=" + startX +
                    ", startY=" + startY +
                    ", endX=" + endX +
                    ", endY=" + endY +
                    " ]";
        }
    }
}
