package CreditCalculator;

public class CreditCalculator {

    public static void main(String[] args) {
        CalculatorLogic calculatorLogic = new CalculatorLogic();

        String type = System.getProperty("type");
        String principal = System.getProperty("principal");
        String periods = System.getProperty("periods");
        String interest = System.getProperty("interest");
        String payment = System.getProperty("payment");

        calculatorLogic.dataAnalyze(type, principal, periods, interest, payment);
    }
}

class CalculatorLogic {

    public void dataAnalyze(String type, String principal, String periods, String interest, String payment) {
        if (type == null || interest == null) {
            System.out.println("Incorrect parameters");
            return;
        }

        try {
            double P = principal != null ? Double.parseDouble(principal) : 0;
            double A = payment != null ? Double.parseDouble(payment) : 0;
            int n = periods != null ? Integer.parseInt(periods) : 0;
            double i = Double.parseDouble(interest) / (12 * 100);

            if (P < 0 || A < 0 || n < 0 || i < 0) {
                System.out.println("Incorrect parameters");
                return;
            }

            if (type.equals("diff")) {
                if (payment != null) {
                    System.out.println("Incorrect parameters");
                    return;
                }
                calculateDiff(P, n, i);

            } else if (type.equals("annuity")) {
                if (principal == null) {
                    calculatePrincipal(A, n, i);
                } else if (payment == null) {
                    calculateAnnuity(P, n, i);
                } else if (periods == null) {
                    calculatePeriods(P, A, i);
                } else {
                    System.out.println("Incorrect parameters");
                }
            } else {
                System.out.println("Incorrect parameters");
            }
        } catch (Exception e) {
            System.out.println("Incorrect parameters");
        }
    }

    private void calculateDiff(double P, int n, double i) {
        double totalPayment = 0;
        for (int m = 1; m <= n; m++) {
            double Dm = (P / n) + i * (P - (P * (m - 1) / n));
            int payment = (int) Math.ceil(Dm);
            totalPayment += payment;
            System.out.println("Month " + m + ": payment is " + payment);
        }
        int overpayment = (int) Math.round(totalPayment - P);
        System.out.println("Overpayment = " + overpayment);
    }

    private void calculateAnnuity(double P, int n, double i) {
        double A = P * (i * Math.pow(1 + i, n)) / (Math.pow(1 + i, n) - 1);
        System.out.println("Your annuity payment = " + (int) Math.ceil(A) + "!");
        int overpayment = (int) Math.round((Math.ceil(A) * n) - P);
        System.out.println("Overpayment = " + overpayment);
    }

    private void calculatePrincipal(double A, int n, double i) {
        double P = A / ((i * Math.pow(1 + i, n)) / (Math.pow(1 + i, n) - 1));
        System.out.println("Your loan principal = " + (int) Math.floor(P) + "!");
        int overpayment = (int) Math.round((A * n) - P);
        System.out.println("Overpayment = " + overpayment);
    }

    private void calculatePeriods(double P, double A, double i) {
        double n = Math.log(A / (A - i * P)) / Math.log(1 + i);
        int months = (int) Math.ceil(n);
        int years = months / 12;
        int remMonths = months % 12;

        if (years > 0 && remMonths > 0) {
            System.out.println("It will take " + years + " years and " + remMonths + " months to repay this loan!");
        } else if (years > 0) {
            System.out.println("It will take " + years + " years to repay this loan!");
        } else {
            System.out.println("It will take " + months + " months to repay this loan!");
        }

        int overpayment = (int) Math.round((A * months) - P);
        System.out.println("Overpayment = " + overpayment);
    }
}