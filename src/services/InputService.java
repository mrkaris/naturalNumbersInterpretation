package services;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputService {
    
    // InputService takes the input converts it to a list and makes sure its a series of valid numbers
    public static List<String> inputToList() {
        Scanner sc = new Scanner(System.in);
        String input;
        System.out.println("Type in a sequence of numbers to test\n");
        input = sc.nextLine();
        List<String> inputList=convertToList(input);
        while (!isInputValid(inputList)) {
            System.out.println("Input must be numbers(3 digits max , numbers with more than 1 digit can't start with 0)"
                    + "\n and spaces only (can't start with space)\n");
            input = sc.nextLine();
            inputList=convertToList(input);
        }
        sc.close();
        return inputList;
    }
    
    private static boolean isInputValid(List<String> input){
        return maxThreeDigits(input)&&inputOnlyDigits(input)&&doesntStartWithZero(input);
    }
    
    private static boolean doesntStartWithZero(List<String> input){
        for (String number : input){
            if (number.length()>1){
                if (number.startsWith("0")) return false;
            }
        }
        return true;
    }
    
    private static boolean maxThreeDigits(List<String> input) {
        for (String number : input){
            if (number.length()>3) return false;
        }
        return true;
    }

    private static boolean inputOnlyDigits(List<String> input) {
        for (String number : input){
            if (!(number!=null&&number.matches("[0-9]+"))) return false;
        }
        return true;
    }
    private static List<String> convertToList(String input) {
        List<String> inputList = Arrays.asList(input.split("\\s+"));
        return inputList;
    }

}
