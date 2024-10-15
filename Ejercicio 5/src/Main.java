import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //Sample array, can be modified to any array and it should still work;
        List<String> myArray = List.of("FirstWord", "SecondWord", "THIRDWORD");

        String result = myArray.stream() //iterate through the array
                .map(String::toLowerCase) //turn string to lowercase
                .map(String::trim) //remove leading and trailing whitespaces
                .collect(Collectors.joining(" ")); //collect each word joining with a whitespace

        //print the original array
        System.out.println("Current array:");
        myArray.stream().forEach(System.out::println);

        //print the processed result
        System.out.println("Result: " + result);
    }
}