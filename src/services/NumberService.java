package services;

import java.util.ArrayList;
import java.util.List;

public class NumberService {

    protected static List<List<String>> tempResults = new ArrayList<>();

    protected static void clearAndFillTempResults(List<List<String>> results) {
        tempResults.clear();
        for (List<String> sublist : results) {
            tempResults.add(new ArrayList<>(sublist));
        }
    }

    public static void checkForInterpretations(List<List<String>> results) {
        List<String> startingList = results.get(0);
        int numberSize = 0;
        for (int i = 0; i < startingList.size(); i++) {
            numberSize = startingList.get(i).length();
            switch (numberSize) {
                case 1:
                    continue;
                case 2:
                    TwoDigitService.addResults(i, startingList, results);
                    continue;
                case 3:
                    ThreeDigitService.addResults(i, startingList, results);
                    continue;
            }
        }
    }

    public static List<String> resultsToStrings(List<List<String>> results) {
        String result;
        List<String> stringResults = new ArrayList();
        for (List<String> numbers : results) {
            result = "";
            for (String number : numbers) {
                result += number;
            }
            stringResults.add(result);
        }
        return stringResults;
    }

    public static void printResults(List<String> results) {
        int k = 0;
        for (String result : results) {
            k += 1;
            String firstPart="Interpretaion " + k + ": " + result;
            String secondPart= (isValid(result) ? "VALID" : "INVALID") + "]";
            System.out.printf("%-40s%s%n",firstPart,secondPart);
        }
    }

    private static boolean isValid(String number) {
        boolean valid = false;
        if (number.length() == 14) {
            if (number.startsWith("00302") || number.startsWith("003069")) {
                valid = true;
            }
        }
        if (number.length() == 10) {
            if (number.startsWith("2") || number.startsWith("69")) {
                valid = true;
            }
        }
        return valid;
    }

}
