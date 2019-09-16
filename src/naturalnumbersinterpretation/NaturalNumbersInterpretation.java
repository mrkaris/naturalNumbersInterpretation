package naturalnumbersinterpretation;

import services.InputService;
import services.NumberService;
import java.util.ArrayList;
import java.util.List;

public class NaturalNumbersInterpretation {

    public static void main(String[] args) {

        // The genereal idea is to convert the input string to a list to manipulate the numbers
        // the list is saved into a list containing lists ( List<List<String>> )
        // which calls the services to add interpretations for each number
        
        
        // Initiate Scanner input and save it as a list     e.g. "2 10 55 45 382" -> [2,10,55,45,382]
        List<String> inputAsList = InputService.inputToList();
        // Add it to the list containing the interpretations
        List<List<String>> interpretationResults = new ArrayList<>();
        interpretationResults.add(inputAsList);
        System.out.println("");
        System.out.println(interpretationResults);

        // Check and add interpretations in interpretationResults
        NumberService.checkForInterpretations(interpretationResults);
        System.out.println("");
        
        // Convert Lists to Strings and print
        List<String> results = NumberService.resultsToStrings(interpretationResults);
        NumberService.printResults(results);
        
        
    }

}
