public class Algebra {

    // Addition
    public static int plus(int a, int b) {
        while (b != 0) {
            a++;
            b--;
        }
        return a;
    }

    // Subtraction
    public static int minus(int a, int b) {
        while (b != 0) {
            a--;
            b--;
        }
        return a;
    }

    // Multiplication using addition
    public static int times(int a, int b) {
        int result = 0;
        while (b > 0) {
            result = plus(result, a);
            b = minus(b, 1);
        }
        return result;
    }

    // Power using multiplication
    public static int pow(int a, int b) {
        if (b == 0) return 1;
        int result = 1;
        while (b > 0) {
            result = times(result, a);
            b = minus(b, 1);
        }
        return result;
    }

    // Integer division using subtraction
    public static int div(int a, int b) {
        if (b == 0) throw new ArithmeticException("Division by zero");
        int result = 0;
        while (a >= b) {
            a = minus(a, b);
            result++;
        }
        return result;
    }

    // Modulo using subtraction
    public static int mod(int a, int b) {
        if (b == 0) throw new ArithmeticException("Division by zero");
        while (a >= b) {
            a = minus(a, b);
        }
        return a;
    }

    // Square root using repeated subtraction
    public static int sqrt(int a) {
        if (a < 0) throw new IllegalArgumentException("Negative input");
        int result = 0;
        while (times(result, result) <= a) {
            result++;
        }
        return minus(result, 1);
    }

    // Main function for testing
    public static void main(String[] args) {
        System.out.println("Plus: " + plus(4, 3));       // Expected: 7
        System.out.println("Minus: " + minus(9, 5));     // Expected: 4
        System.out.println("Times: " + times(3, 4));     // Expected: 12
        System.out.println("Pow: " + pow(2, 3));         // Expected: 8
        System.out.println("Div: " + div(10, 3));        // Expected: 3
        System.out.println("Mod: " + mod(10, 3));        // Expected: 1
        System.out.println("Sqrt: " + sqrt(16));         // Expected: 4
    }
}


	
	
