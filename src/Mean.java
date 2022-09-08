public class Mean {
    public static void main(String[] args) {

        double sumOfNumbers = 0;
        int count = args.length;

        for(int i=0; i<count; i++) {
            String number = args[i];
            double numberInDouble = Double.parseDouble(number);
            sumOfNumbers += numberInDouble;
        }

        double meanOfNumbers = sumOfNumbers/count;
        System.out.println("Mean: " + meanOfNumbers);
    }
}
