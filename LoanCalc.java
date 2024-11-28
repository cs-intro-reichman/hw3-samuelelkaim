public class LoanCalc {

    private static int iterationCounter; // To track iterations

    // Calculate the ending balance after n payments
    public static double endBalance(double loan, double rate, int periods, double payment) {
        for (int i = 0; i < periods; i++) {
            loan = (loan - payment) * (1 + rate);
        }
        return loan;
    }

    // Brute force search for finding the payment
    public static int bruteForceSolver(double loan, double annualRate, int periods, double epsilon) {
        iterationCounter = 0;
        double monthlyRate = annualRate / 12; // Convert annual rate to monthly
        double payment = loan / periods; // Start with a naive guess

        while (endBalance(loan, monthlyRate, periods, payment) > 0) {
            payment += epsilon; // Increment payment by small steps
            iterationCounter++;
        }

        return (int) Math.floor(payment); // Ensure integer result matches expectations
    }

    // Bisection search for finding the payment
    public static int bisectionSolver(double loan, double annualRate, int periods, double epsilon) {
        iterationCounter = 0;
        double monthlyRate = annualRate / 12; // Convert annual rate to monthly
        double low = loan / periods; // Lower bound for payment
        double high = loan * (1 + monthlyRate); // Upper bound for payment
        double mid;

        while ((high - low) > epsilon) {
            mid = (low + high) / 2;
            double balance = endBalance(loan, monthlyRate, periods, mid);

            if (Math.abs(balance) < epsilon) break; // Close enough to zero
            if (balance > 0) low = mid; else high = mid;

            iterationCounter++;
        }

        return (int) Math.floor((low + high) / 2); // Ensure integer result matches expectations
    }

    public static void main(String[] args) {
        // Test Case 1
        double loan = 100000; // Loan amount
        double annualRate = 0.03; // Annual interest rate (3%)
        int periods = 12; // Loan duration in months
        double epsilon = 0.01; // Small precision step

        // Brute force method
        int bruteForcePayment = bruteForceSolver(loan, annualRate, periods, epsilon);
        System.out.printf("Periodical payment, using brute force: %d%n", bruteForcePayment);
        System.out.printf("Number of iterations: %d%n", iterationCounter);

        // Bisection method
        int bisectionPayment = bisectionSolver(loan, annualRate, periods, epsilon);
        System.out.printf("Periodical payment, using bi-section search: %d%n", bisectionPayment);
        System.out.printf("Number of iterations: %d%n", iterationCounter);
    }
}
