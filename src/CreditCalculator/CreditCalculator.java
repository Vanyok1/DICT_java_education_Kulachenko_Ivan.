package CreditCalculator;

import java.util.Scanner;

public class CreditCalculator {

    public static void main(String[] args) {
        CalculatorLogic logic = new CalculatorLogic();
        logic.runAnnuityCalculator();
    }
}

class CalculatorLogic {

    public void runAnnuityCalculator() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What do you want to calculate?");
        System.out.println("type \"n\" for number of monthly payments,");
        System.out.println("type \"a\" for annuity monthly payment amount,");
        System.out.println("type \"p\" for loan principal:");
        String choice = scanner.next();

        if (choice.equals("n")) {
            System.out.println("Enter the loan principal:");
            double principal = scanner.nextDouble();

            System.out.println("Enter the monthly payment:");
            double payment = scanner.nextDouble();

            System.out.println("Enter the loan interest:");
            double interest = scanner.nextDouble();

            double i = interest / (12 * 100);
            double n = Math.log(payment / (payment - i * principal)) / Math.log(1 + i);
            int months = (int) Math.ceil(n);

            int years = months / 12;
            int remainingMonths = months % 12;

            if (years > 0 && remainingMonths > 0) {
                System.out.println("It will take " + years + " years and " + remainingMonths + " months to repay this loan!");
            } else if (years > 0) {
                System.out.println("It will take " + years + " years to repay this loan!");
            } else {
                System.out.println("It will take " + months + " months to repay this loan!");
            }

        } else if (choice.equals("a")) {
            System.out.println("Enter the loan principal:");
            double principal = scanner.nextDouble();

            System.out.println("Enter the number of periods:");
            int periods = scanner.nextInt();

            System.out.println("Enter the loan interest:");
            double interest = scanner.nextDouble();

            double i = interest / (12 * 100);
            double annuity = principal * (i * Math.pow(1 + i, periods)) / (Math.pow(1 + i, periods) - 1);
            System.out.println("Your monthly payment = " + Math.ceil(annuity) + "!");

        } else if (choice.equals("p")) {
            System.out.println("Enter the annuity payment:");
            double annuity = scanner.nextDouble();

            System.out.println("Enter the number of periods:");
            int periods = scanner.nextInt();

            System.out.println("Enter the loan interest:");
            double interest = scanner.nextDouble();

            double i = interest / (12 * 100);
            double principal = annuity / ((i * Math.pow(1 + i, periods)) / (Math.pow(1 + i, periods) - 1));
            System.out.println("Your loan principal = " + Math.floor(principal) + "!");
        } else {
            System.out.println("Incorrect option selected.");
        }

        scanner.close();
    }
}