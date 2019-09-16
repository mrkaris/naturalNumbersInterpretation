package services;

import java.util.ArrayList;
import java.util.List;

public class TwoDigitService extends NumberService {
    
    // This service forms and adds interpretations for 2 digit numbers
    protected static void addInterpretation(int index, String interpretation, List<List<String>> results) {
        for (List l : tempResults) {
            l.set(index, interpretation);
            results.add(new ArrayList(l));
        }
    }
    // adds interpretation for numbers with no zero     e. g. "22" -> "20 2"
    private static void noZero(int index, List<String> startingList, List<List<String>> results) {
        if (!startingList.get(index).contains("0") && !startingList.get(index).equals("11")
                && !startingList.get(index).equals("12")) {
            clearAndFillTempResults(results);
            String number = startingList.get(index);
            String interpretation = number.charAt(0) + "0" + number.charAt(1);
            addInterpretation(index, interpretation, results);
        }
    }
    // adds interpretation when the number ends in zero and the next number is a single digit
    //  e. g.   50 1 - > 5 1
    private static void withZero(int index, List<String> startingList, List<List<String>> results) {
        if (startingList.get(index).endsWith("0") && index + 1 < startingList.size()
                && startingList.get(index + 1).length() == 1) {
            if (startingList.get(index).equals("10")
                    && (startingList.get(index + 1).equals("1") || startingList.get(index + 1).equals("2"))) {
                return;
            }
            clearAndFillTempResults(results);
            String interpretation = startingList.get(index).charAt(0) + "";
            addInterpretation(index, interpretation, results);
        }
    }

    public static void addResults(int index, List<String> startingList, List<List<String>> results) {
        noZero(index, startingList, results);
        withZero(index, startingList, results);
    }
}
