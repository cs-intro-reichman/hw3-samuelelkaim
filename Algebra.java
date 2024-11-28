public class Algebra {

    // Addition
    public static int plus(int a, int b) {
        while (b != 0) {
            a++;
            b--;
        }
        return a;
    }

    // Soustraction
    public static int minus(int a, int b) {
        while (b != 0) {
            a--;
            b--;
        }
        return a;
    }

    // Multiplication
    public static int times(int a, int b) {
        int result = 0;
        while (b > 0) {
            result = plus(result, a);
            b = minus(b, 1);
        }
        return result;
    }

    // Puissance
    public static int pow(int a, int b) {
        if (b == 0) return 1; // Toute valeur puissance 0 est 1
        int result = 1;
        while (b > 0) {
            result = times(result, a);
            b = minus(b, 1);
        }
        return result;
    }

    // Division entière
    public static int div(int a, int b) {
        if (b == 0) throw new ArithmeticException("Division by zero"); // Gestion d'erreur
        int result = 0;
        while (a >= b) {
            a = minus(a, b);
            result++;
        }
        return result;
    }

    // Modulo
    public static int mod(int a, int b) {
        if (b == 0) throw new ArithmeticException("Division by zero"); // Gestion d'erreur
        while (a >= b) {
            a = minus(a, b);
        }
        return a;
    }

    // Racine carrée (partie entière seulement)
    public static int sqrt(int a) {
        if (a < 0) throw new IllegalArgumentException("Negative input"); // Gestion d'erreur
        int result = 0;
        while (times(result, result) <= a) {
            result++;
        }
        return minus(result, 1); // On ajuste pour ne pas dépasser la valeur exacte
    }

    // Test des fonctions
    public static void main(String[] args) {
        System.out.println("Plus: " + plus(4, 3));       // 7
        System.out.println("Minus: " + minus(9, 5));     // 4
        System.out.println("Times: " + times(3, 4));     // 12
        System.out.println("Pow: " + pow(2, 3));         // 8
        System.out.println("Div: " + div(10, 3));        // 3
        System.out.println("Mod: " + mod(10, 3));        // 1
        System.out.println("Sqrt: " + sqrt(16));         // 4
    }
}


	
	
