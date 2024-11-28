public class LoanCalc {

    private static int iterationCounter; // To track iterations

    // Calculate the ending balance after n payments
    public static double endBalance(double loan, double rate, int n, double payment) {
        for (int i = 0; i < n; i++) {
            loan = (loan - payment) * (1 + rate);
        }
        return loan;
    }

    // Brute force search for finding the payment
    public static int bruteForceSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double payment = loan / n; // Start with a naive guess

        while (endBalance(loan, rate, n, payment) > 0) {
            payment += epsilon; // Increment by a small step
            iterationCounter++;
        }

        return (int) Math.floor(payment); // Ensure results align with expected precision
    }

    // Bisection search for finding the payment
    public static int bisectionSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double low = loan / n, high = loan * (1 + rate), mid = 0;

        while ((high - low) > epsilon) {
            mid = (low + high) / 2;
            double balance = endBalance(loan, rate, n, mid);

            if (Math.abs(balance) < epsilon) break; // Close enough to zero
            if (balance > 0) low = mid; else high = mid;

            iterationCounter++;
        }

        return (int) Math.floor((low + high) / 2); // Ensure results align with expected precision
    }

    public static void main(String[] args) {
        double loan = 100000; // Loan amount
        double rate = 0.03 / 12; // Monthly interest rate (3% annual rate)
        int periods = 12;       // Loan duration in months
        double epsilon = 0.01;  // Small step for precision

        // Brute force method
        int bruteForcePayment = bruteForceSolver(loan, rate, periods, epsilon);
        System.out.printf("Periodical payment, using brute force: %d%n", bruteForcePayment);
        System.out.printf("Number of iterations: %d%n", iterationCounter);

        // Bisection method
        int bisectionPayment = bisectionSolver(loan, rate, periods, epsilon);
        System.out.printf("Periodical payment, using bi-section search: %d%n", bisectionPayment);
        System.out.printf("Number of iterations: %d%n", iterationCounter);
    }
}
