import java.util.*;

import static java.lang.Math.*;

/**
 * @author Kashif
 */
public class Currencies {
    public static void main(String[] args) {
        System.out.print("Enter command: ");
        Scanner input = new Scanner(System.in);

        ArrayList<String> commandParams = new ArrayList<>();
        HashMap<String, Double> rates = new HashMap<>();

        commandParams.add(input.next());
        while (true) {
            if (commandParams.get(0).equals("rate")) {
                String currency = input.next();
                commandParams.add(currency);
                String currencyRateStr = input.next();
                double currencyRate = Double.parseDouble(currencyRateStr);
                commandParams.add(String.valueOf(currencyRate));
                System.out.println(String.format(Locale.US, "Stored the rate 1 EUR = %.3f %s", currencyRate, currency.toUpperCase()));
                rates.put(currency.toUpperCase(), currencyRate);
            }
            else if (commandParams.get(0).equals("rates")) {
                System.out.println("Stored euro rates:");
                for(Map.Entry<String, Double> rate : rates.entrySet()){
                    System.out.printf(Locale.US, "  %s %.3f%n", rate.getKey(), rate.getValue());
                }
            }
            else if (commandParams.get(0).equals("convert")) {
                String amountEntered = input.next();
                double amount2BConverted = Double.parseDouble(amountEntered);
                commandParams.set(1, input.next());
                if (rates.containsKey(commandParams.get(1))) {
                    System.out.printf(Locale.US, "%.3f %s = %.3f EUR%n", amount2BConverted, commandParams.get(1), amount2BConverted / rates.get(commandParams.get(1)));
                }
            } else if (commandParams.get(0).equals("quit")){
                System.out.println("Quit-command received, exiting...");
                break;
            }

            System.out.print("Enter command: ");
            commandParams.set(0, input.next());
        }
        
    }
}
