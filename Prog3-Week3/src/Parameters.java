import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Parameters {
    public static void main(String[] args) {

        ArrayList<String> listOfNumbers = new ArrayList<>();

        Collections.addAll(listOfNumbers, args);
        Collections.sort(listOfNumbers);

        int longestParam = listOfNumbers.stream()
                .max(Comparator.comparingInt(String::length))
                .get().length();

        int digitCountOfNumber = String.valueOf(args.length).length();
        System.out.println("#".repeat(digitCountOfNumber+longestParam+7));

        for (int i=1; i<=args.length; i++) {
            System.out.printf("# %" + digitCountOfNumber +"d | %-" + longestParam + "s%s%n", i, listOfNumbers.get(i - 1), " #");

            if (i == args.length) break;
            else System.out.println("#" + "-".repeat(digitCountOfNumber+2) + "+-" + "-".repeat(longestParam) + "-#");
        }
        System.out.println("#".repeat(digitCountOfNumber+longestParam+7));
    }
}
