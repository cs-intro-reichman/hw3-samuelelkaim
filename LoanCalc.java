public class LoanCalculator {

    private static int iterationCounter; // To track the number of iterations

    // Calculate the ending balance after n payments
    public static double endBalance(double loan, double rate, int periods, double payment) {
        for (int i = 0; i < periods; i++) {
            loan = (loan - payment) * (1 + rate); // Subtract payment, then apply interest
        }
        return loan;
    }

    // Brute force method to find the payment
    public static double bruteForceSolver(double loan, double rate, int periods, double epsilon) {
        iterationCounter = 0; // Reset counter
        double payment = loan / periods; // Initial guess for payment

        while (endBalance(loan, rate, periods, payment) > epsilon) {
            payment += epsilon; // Increment payment by epsilon
            iterationCounter++;
        }
        return payment;
    }

    // Bisection search method to find the payment
    public static double bisectionSolver(double loan, double rate, int periods, double epsilon) {
        iterationCounter = 0; // Reset counter
        double low = loan / periods, high = loan * (1 + rate), mid;

        while ((high - low) > epsilon) {
            mid = (low + high) / 2;
            double balance = endBalance(loan, rate, periods, mid);

            if (Math.abs(balance) < epsilon) break; // Stop if balance is close to zero
            if (balance > 0) low = mid; else high = mid; // Adjust bounds

            iterationCounter++;
        }
        return (low + high) / 2; // Return the midpoint as the best approximation
    }

    public static void main(String[] args) {
        double loan = 100000; // Loan amount
        double rate = 0.05;   // Annual interest rate
        int years = 10;       // Loan duration in years
        double epsilon = 0.01;// Precision for the solution

        // Solve using brute force
        double bruteForcePayment = bruteForceSolver(loan, rate, years, epsilon);
        System.out.printf("Brute Force Payment: %.2f Shekels (Iterations: %d)%n", bruteForcePayment, iterationCounter);

        // Solve using bisection search
        double bisectionPayment = bisectionSolver(loan, rate, years, epsilon);
        System.out.printf("Bisection Payment: %.2f Shekels (Iterations: %d)%n", bisectionPayment, iterationCounter);
    }
}

    





