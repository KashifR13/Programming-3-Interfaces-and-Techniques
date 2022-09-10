import java.util.ArrayList;
import java.util.Collections;

public class Median {
    public static void main(String[] args) {

        int count = args.length;
        ArrayList<Double> listOfNumbers = new ArrayList<>();
        double medianOfNumbers;

        for (String arg : args) {
            double thisNumber = Double.parseDouble(arg);
            listOfNumbers.add(thisNumber);
        }

        Collections.sort(listOfNumbers);

        if (count%2 != 0) medianOfNumbers = listOfNumbers.get(count / 2);
        else medianOfNumbers = (listOfNumbers.get(count / 2 - 1) + listOfNumbers.get(count / 2)) / 2;

        System.out.println("Median: " + medianOfNumbers);
    }
}
