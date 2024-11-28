public class LoanCalc {

    static double epsilon = 0.001;  // Approximation accuracy
    static int iterationCounter;    // Number of iterations 

    // Main method to compute the periodic payment using brute force and bisection
    public static void main(String[] args) {		
        // Parse input arguments
        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);
        System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

        // Compute the payment using brute force search
        System.out.print("\nPeriodical payment, using brute force: ");
        double bruteForcePayment = bruteForceSolver(loan, rate, n, epsilon);
        System.out.printf("%.2f (Iterations: %d)%n", bruteForcePayment, iterationCounter);

        // Compute the payment using bisection search
        System.out.print("\nPeriodical payment, using bisection search: ");
        double bisectionPayment = bisectionSolver(loan, rate, n, epsilon);
        System.out.printf("%.2f (Iterations: %d)%n", bisectionPayment, iterationCounter);
    }

    // Calculate the ending balance after n payments
    private static double endBalance(double loan, double rate, int n, double payment) {
        double balance = loan;
        rate = rate / 100; // Convert percentage rate to decimal
        for (int i = 0; i < n; i++) {
            balance = balance * (1 + rate) - payment; // Apply interest first, then subtract payment
        }
        return balance;
    }

    // Brute force search to compute periodic payment
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0; // Reset iteration counter
        double payment = loan / n; // Initial guess
        while (true) {
            double balance = endBalance(loan, rate, n, payment);
            if (balance <= 0) break; // Stop when the balance is zero or negative
            payment += epsilon; // Increment payment by epsilon
            iterationCounter++;
        }
        return payment;
    }

    // Bisection search to compute periodic payment
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0; // Reset iteration counter
        double lo = loan / n; // Lower bound
        double hi = loan / (n / 2); // Upper bound (reasonable guess for maximum payment)
        double mid;

        while (hi - lo > epsilon) {
            mid = (lo + hi) / 2;
            double balance = endBalance(loan, rate, n, mid);
            if (balance > epsilon) {
                lo = mid; // Balance is too high, increase payment
            } else if (balance < -epsilon) {
                hi = mid; // Balance is too low, decrease payment
            } else {
                return mid; // Close enough to zero
            }
            iterationCounter++;
        }
        return (lo + hi) / 2; // Final approximation
    }
}
