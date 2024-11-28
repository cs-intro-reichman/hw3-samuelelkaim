

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
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double payment = loan / n;

        while (endBalance(loan, rate, n, payment) > epsilon) {
            payment += epsilon;
            iterationCounter++;
        }
        return payment;
    }

    // Bisection search for finding the payment
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double low = loan / n, high = loan * (1 + rate), mid = 0;

        while ((high - low) > epsilon) {
            mid = (low + high) / 2;
            double balance = endBalance(loan, rate, n, mid);

            if (Math.abs(balance) < epsilon) break;
            if (balance > 0) low = mid; else high = mid;

            iterationCounter++;
        }
        return mid;
    }

    public static void main(String[] args) {
        double loan = 100000;
        double rate = 0.03;
        int years = 12;
        double epsilon = 0.01;

        // Brute force
        double bruteForcePayment = bruteForceSolver(loan, rate, years, epsilon);
        System.out.printf("Brute Force Payment: %.2f Shekels (Iterations: %d)%n", bruteForcePayment, iterationCounter);

        // Bisection
        double bisectionPayment = bisectionSolver(loan, rate, years, epsilon);
        System.out.printf("Bisection Payment: %.2f Shekels (Iterations: %d)%n", bisectionPayment, iterationCounter);
    }
}
