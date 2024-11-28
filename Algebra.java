public class Algebra {

    // Addition
    public static int plus(int a, int b) {
        while (b != 0) {
            if (b > 0) {
                a++;
                b--;
            } else {
                a--;
                b++;
            }
        }
        return a;
    }

    // Subtraction
    public static int minus(int a, int b) {
        return plus(a, -b); // Subtraction is addition with a negative number
    }

    // Multiplication (supports negative numbers)
    public static int times(int a, int b) {
        int result = 0;
        boolean isNegative = (a < 0) ^ (b < 0); // XOR to determine if the result should be negative

        a = Math.abs(a);
        b = Math.abs(b);

        while (b > 0) {
            result = plus(result, a);
            b = minus(b, 1);
        }

        return isNegative ? minus(0, result) : result;
    }

    // Power (supports negative base, assumes integer exponent >= 0)
    public static int pow(int a, int b) {
        if (b == 0) return 1;
        if (a == 0) return 0;

        int result = 1;
        boolean isNegativeBase = a < 0 && b % 2 != 0; // Negative result if base is negative and exponent is odd
        a = Math.abs(a);

        while (b > 0) {
            result = times(result, a);
            b = minus(b, 1);
        }

        return isNegativeBase ? minus(0, result) : result;
    }

    // Integer Division (supports negative numbers)
    public static int div(int a, int b) {
        if (b == 0) throw new ArithmeticException("Division by zero");

        boolean isNegative = (a < 0) ^ (b < 0); // XOR to determine if the result should be negative
        a = Math.abs(a);
        b = Math.abs(b);

        int result = 0;
        while (a >= b) {
            a = minus(a, b);
            result++;
        }

        return isNegative ? minus(0, result) : result;
    }

    // Modulo (supports negative dividend)
    public static int mod(int a, int b) {
        if (b == 0) throw new ArithmeticException("Division by zero");

        boolean isNegative = a < 0;
        a = Math.abs(a);
        b = Math.abs(b);

        while (a >= b) {
            a = minus(a, b);
        }

        return isNegative ? minus(0, a) : a;
    }

    // Square root (integer part, assumes non-negative input)
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
        System.out.println("Plus: " + plus(4, -3));       // Expected: 1
        System.out.println("Minus: " + minus(9, -5));     // Expected: 14
        System.out.println("Times: " + times(-3, 4));     // Expected: -12
        System.out.println("Pow: " + pow(-2, 3));         // Expected: -8
        System.out.println("Div: " + div(-10, 3));        // Expected: -3
        System.out.println("Mod: " + mod(-10, 3));        // Expected: -1
        System.out.println("Sqrt: " + sqrt(16));          // Expected: 4
    }
}

   
