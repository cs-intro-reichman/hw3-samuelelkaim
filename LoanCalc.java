public class LoanCalc {

    private static int iterationCounter; // Compteur pour les itérations

    // Calculer le solde restant après n paiements
    public static double endBalance(double loan, double monthlyRate, int periods, double payment) {
        for (int i = 0; i < periods; i++) {
            loan = (loan - payment) * (1 + monthlyRate); // Réduction du prêt et application des intérêts
        }
        return loan;
    }

    // Recherche brute pour trouver le paiement
    public static int bruteForceSolver(double loan, double annualRate, int periods, double epsilon) {
        iterationCounter = 0;
        double monthlyRate = annualRate / 12; // Convertir le taux annuel en taux mensuel
        double payment = loan / periods; // Point de départ initial pour le paiement

        // Augmenter le paiement jusqu'à ce que le solde soit proche de zéro
        while (endBalance(loan, monthlyRate, periods, payment) > 0) {
            payment += epsilon; // Incrément par petites étapes
            iterationCounter++;
        }

        return (int) Math.round(payment); // Arrondi au nombre entier le plus proche
    }

    // Recherche par dichotomie pour trouver le paiement
    public static int bisectionSolver(double loan, double annualRate, int periods, double epsilon) {
        iterationCounter = 0;
        double monthlyRate = annualRate / 12; // Convertir le taux annuel en taux mensuel
        double low = loan / periods; // Limite inférieure pour le paiement
        double high = loan * (1 + monthlyRate); // Limite supérieure pour le paiement
        double mid;

        while ((high - low) > epsilon) {
            mid = (low + high) / 2; // Calculer le point médian
            double balance = endBalance(loan, monthlyRate, periods, mid);

            if (Math.abs(balance) < epsilon) break; // Solde suffisamment proche de zéro
            if (balance > 0) low = mid; else high = mid; // Ajuster les limites

            iterationCounter++;
        }

        return (int) Math.round((low + high) / 2); // Arrondi au nombre entier le plus proche
    }

    public static void main(String[] args) {
        // Cas de test
        double loan = 100000; // Montant du prêt
        double annualRate = 0.03; // Taux d'intérêt annuel (3 %)
        int periods = 12; // Durée du prêt en mois
        double epsilon = 0.01; // Précision pour le paiement

        // Méthode de recherche brute
        int bruteForcePayment = bruteForceSolver(loan, annualRate, periods, epsilon);
        System.out.printf("Paiement périodique, méthode brute : %d%n", bruteForcePayment);
        System.out.printf("Nombre d'itérations : %d%n", iterationCounter);

        // Méthode de recherche par dichotomie
        int bisectionPayment = bisectionSolver(loan, annualRate, periods, epsilon);
        System.out.printf("Paiement périodique, méthode dichotomie : %d%n", bisectionPayment);
        System.out.printf("Nombre d'itérations : %d%n", iterationCounter);
    }
}
