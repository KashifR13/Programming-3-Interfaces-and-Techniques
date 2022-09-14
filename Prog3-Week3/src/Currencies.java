import java.util.*;

/**
 * @author Kashif
 */
public class Currencies {
    /**
     * This main method perform currency conversions by accepting the following commands from standard input
     * 1. rate AAA x -> Records that 1 euro equals x units of the currency AAA.
     * 2. convert x AAA -> Prints out a message of form “x AAA = y EUR” that tells the value of x units of the currency AAA converted into euros.
     * 3. rates -> Prints out all currently known currency rates in ascending alphabetical order of currency codes.
     * 4. quit -> Stops the program.
     * @param args
     */
    public static void main(String[] args) {
        
        System.out.print("Enter command: ");
        Scanner input = new Scanner(System.in);

        ArrayList<String> commandParams = new ArrayList<>();
        TreeMap<String, Double> rates = new TreeMap<>();

        commandParams.add(input.next());

        label:
        while (true) {

            switch (commandParams.get(0)) {

                case "rate":
                    String currency = input.next();
                    commandParams.add(currency);
                    String currencyRateStr = input.next();
                    double currencyRate = Double.parseDouble(currencyRateStr);

                    commandParams.add(String.valueOf(currencyRate));

                    System.out.printf(Locale.US, "%s %s %.10f%n", commandParams.get(0), currency, currencyRate);
                    System.out.println(String.format(Locale.US, "Stored the rate 1 EUR = %.3f %s", currencyRate, currency.toUpperCase()));

                    rates.put(currency.toUpperCase(), currencyRate);
                    break;

                case "rates":
                    System.out.println(commandParams.get(0));

                    System.out.println("Stored euro rates:");
                    for (Map.Entry<String, Double> rate : rates.entrySet()) {
                        System.out.printf(Locale.US, "  %s %.3f%n", rate.getKey(), rate.getValue());
                    }
                    break;

                case "convert":
                    try {
                        String amountEntered = input.next();
                        double amount2BConverted = Double.parseDouble(amountEntered);
                        commandParams.set(1, input.next());

                        System.out.printf(Locale.US, "%s %s %s%n", commandParams.get(0), amountEntered, commandParams.get(1));

                        if (rates.containsKey(commandParams.get(1))) {
                            System.out.printf(Locale.US, "%.3f %s = %.3f EUR%n", amount2BConverted, commandParams.get(1), amount2BConverted / rates.get(commandParams.get(1)));
                        } else {
                            System.out.println("No rate for " + commandParams.get(1).toUpperCase() + " has been stored!");
                        }
                        break;

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                case "quit":
                    System.out.println(commandParams.get(0));

                    System.out.println("Quit-command received, exiting...");
                    break label;

                default:
                    System.err.println("Unknown or illegal command!");
                    break label;
            }

            System.out.print("Enter command: ");
            commandParams.set(0, input.next());
        }
    }
}
