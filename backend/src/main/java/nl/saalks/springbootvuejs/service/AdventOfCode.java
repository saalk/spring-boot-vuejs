package nl.saalks.springbootvuejs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public interface AdventOfCode {

    Logger LOG = LoggerFactory.getLogger(AdventOfCode.class);

    static String lineSeparator = System.getProperty("line.separator");

    static boolean isBlankString(String string) {
        return string == null || string.isBlank();
    }

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

        // The subList() returns a view of the portion of this list using from-until
        // Sublist magic, take 1,2,3 and sum them in item AND previous
        int item, previous = depthReport
                .subList(0, windowSize)
                .stream()
                .reduce(0, Integer::sum);

        for (int i = 1; i <= depthReport.size() - windowSize; i++, previous = item ) {
            // multi incr/decr conditions: also make previous same as item

            // sublist magic, take 1,2,3 or 2,3,4 etc and sum them in item
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

    default HashMap<Integer, String> convertArrayListToHashMap(ArrayList<String> arrayList) {
        HashMap<Integer, String> hashMap = new LinkedHashMap<>();
        int counter = 0;
        for (String str : arrayList) {
            counter++;
            hashMap.put(counter, str);
        }
        return hashMap;
    }

    default ArrayList<String> convertHashMapToArrayListTo(HashMap<Integer, String> hashMap) {
        ArrayList<String> arrayList = new ArrayList<>();
        SKIP:
        for (int i=0; i < hashMap.size(); i++) {
            if (hashMap.get(i) == "") continue SKIP;
            arrayList.add(hashMap.get(i));
        }
        return arrayList;
    }

    // function to sort hashmap (key, value) by value ASC
    default HashMap<Integer, Integer> sortHashMapByValue(HashMap<Integer, Integer> hm)
    {
        // Create a linked list from elements of HashMap
        List<Map.Entry<Integer, Integer> > list =
                new LinkedList<Map.Entry<Integer, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer> >() {
            public int compare(Map.Entry<Integer, Integer> o1,
                               Map.Entry<Integer, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Integer, Integer> temp = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

}
