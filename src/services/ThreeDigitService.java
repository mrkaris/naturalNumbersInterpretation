package services;

import java.util.ArrayList;
import java.util.List;

public class ThreeDigitService extends NumberService {
    
    // This service forms and adds interpretations for 3 digit numbers
    private static List<String> interpretations = new ArrayList<>();

    protected static void addInterpretation(int index, String interpretation, List<List<String>> results) {
        for (List l : tempResults) {
            l.set(index, interpretation);
            results.add(l);
        }
    }

    protected static void addInterpretations(int index, List<String> interpretations, List<List<String>> results) {
        for (String interpretation : interpretations) {
            for (List l : tempResults) {
                l.set(index, interpretation);
                results.add(new ArrayList(l));
            }
        }
    }

    private static void noZero(int index, List<String> startingList, List<List<String>> results) {
        if (!startingList.get(index).contains("0")) {
            interpretations.clear();
            // interpretation for d + 0 + 0 + d + d   e.g. 721 -> 700 21
            String number = startingList.get(index);
            String interpretation = number.charAt(0) + "0" + "0" + number.charAt(1) + number.charAt(2);
            interpretations.add(interpretation);
            if (!startingList.get(index).matches("\\d11") && !startingList.get(index).matches("\\d12")) {
                // interpretation for d+ d + 0 + d   e.g. 721 -> 720 1
                number = startingList.get(index);
                interpretation = number.charAt(0) + "" + number.charAt(1) + "0" + number.charAt(2);
                interpretations.add(interpretation);
                // interpretation for d + 0 + 0 d + 0 + d    e.g. 721 -> 700 20 1
                interpretation = number.charAt(0) + "0" + "0" + number.charAt(1) + "0" + number.charAt(2);
                interpretations.add(interpretation);
            }
            clearAndFillTempResults(results);
            addInterpretations(index, interpretations, results);
        }

    }

    private static void withAZeroInTheMid(int index, List<String> startingList, List<List<String>> results) {
        if (startingList.get(index).matches("\\d0[1-9]")) {
            String number = startingList.get(index);
            // interpretation for d + 0 + 0 + d     e.g. 705 -> 700 5
            String interpretation = number.charAt(0) + "0" + "0" + number.charAt(2);
            clearAndFillTempResults(results);
            addInterpretation(index, interpretation, results);
        }

    }

    private static void withAZeroAtTheEnd(int index, List<String> startingList, List<List<String>> results) {
        if (startingList.get(index).matches("\\d[1-9]0")) {
            interpretations.clear();
            String number = startingList.get(index);
            //interpretation for e.g 720 -> 700 20
            String interpretation = number.charAt(0) + "0" + "0" + number.charAt(1) + "0";
            interpretations.add(interpretation);
            if (index + 1 < startingList.size() && startingList.get(index + 1).length() == 1) {
                //interpretation when next number is single digit e.g. 720 8 -> 728 ||700 28
                number = startingList.get(index);
                interpretation = number.charAt(0) + "" + number.charAt(1);
                interpretations.add(interpretation);
                interpretation = number.charAt(0) + "0" + "0" + number.charAt(1);
                interpretations.add(interpretation);
            }
            clearAndFillTempResults(results);
            addInterpretations(index, interpretations, results);
        }
    }

    private static void withDoubleZero(int index, List<String> startingList, List<List<String>> results) {
        if (startingList.get(index).contains("00")) {
            interpretations.clear();
            // interpretation for d00 when next number is single digit e.g 700 1 -> 701
            if (index + 1 < startingList.size() && startingList.get(index + 1).length() == 1) {
                String number = startingList.get(index);
                String interpretation = number.charAt(0) + "0";
                interpretations.add(interpretation);
            }
            // interpretation for d00 when next number is double digit e.g 700 22 -> 722
            if (index + 1 < startingList.size() && startingList.get(index + 1).length() == 2) {
                String number = startingList.get(index);
                String interpretation = number.charAt(0) + "";
                interpretations.add(interpretation);
            }
            clearAndFillTempResults(results);
            addInterpretations(index, interpretations, results);
        }
    }

    public static void addResults(int index, List<String> startingList, List<List<String>> results) {
        noZero(index, startingList, results);
        withAZeroInTheMid(index, startingList, results);
        withAZeroAtTheEnd(index, startingList, results);
        withDoubleZero(index, startingList, results);
    }

}
