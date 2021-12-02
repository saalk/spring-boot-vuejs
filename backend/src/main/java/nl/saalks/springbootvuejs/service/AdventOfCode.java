package nl.saalks.springbootvuejs.service;

import nl.saalks.springbootvuejs.controller.BackendController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public interface AdventOfCode {

    Logger LOG = LoggerFactory.getLogger(AdventOfCode.class);

    static <T, U> List<U> convertStringListToIntList(List<T> listOfString,
                                                     Function<T, U> function) {
        // Function can be Integer::parseInt
        return listOfString
                .stream()                       // List of String to Stream of String
                .map(function)                  // Stream of String to Stream of Integer.
                .collect(Collectors.toList());  // Collect Stream of Integer into List of Integer.
    }

    static Map<String, String> convertStringToStringStringHashMap(String stringToSplit) {
        // regex \s*,\s* considers white-space as part of the separator, hence trimming the entries
        // for: "name peter, add mumbai, md v ,refNo "
        return Pattern.compile("\\s*,\\s*")
                .splitAsStream(stringToSplit.trim())
                .map(s -> s.split(" ", 2))
                .collect(toMap(a -> a[0], a -> a.length > 1 ? a[1] : ""));
    }

    static Map<String, Integer> convertStringToStringIntegerHashMap(String stringToSplit) {
        // regex \s*,\s* considers white-space as part of the separator, hence trimming the entries
        // for: "age 5, time 6, temperature 10 ,distance 6"
        return Pattern.compile("\\s*,\\s*")
                .splitAsStream(stringToSplit.trim())
                .map(s -> s.split(" ", 2))
                .collect(toMap(a -> a[0], a -> Integer.parseInt(a[1]) ));
    }

    static int computeSlidingWindow(List<Integer> depthReport, int windowSize) {

        int increases = 0;
        int item, previous = depthReport
                .subList(0, windowSize)
                .stream()
                .reduce(0, Integer::sum);

        for (int i = 1;                              // initialization
             i <= depthReport.size() - windowSize;   // condition
             i++, previous = item )                  // multi incr/decr
        {
            item = depthReport
                    .subList(i, i + windowSize)
                    .stream()
                    .reduce(0, Integer::sum);
            LOG.info("computeSlidingWindow - previous: " + previous);
            LOG.info("computeSlidingWindow - item: " + item);

            if (item > previous) {
                increases++;
                LOG.info("computeSlidingWindow - INCREASES: " + increases);
            }
        }
        return increases;
    }
}
