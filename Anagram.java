

   import java.util.Random;

public class Anagram {

    // Preprocess the string: Remove non-alphabetic characters (except spaces) and convert to lowercase
    public static String preProcess(String str) {
        return str.replaceAll("[^a-zA-Z ]", "").toLowerCase(); // Allow spaces but remove punctuation
    }

    // Check if two strings are anagrams
    public static boolean isAnagram(String str1, String str2) {
        str1 = preProcess(str1).replace(" ", ""); // Remove spaces for comparison
        str2 = preProcess(str2).replace(" ", ""); // Remove spaces for comparison

        if (str1.length() != str2.length()) return false;

        for (char c : str1.toCharArray()) {
            int index = str2.indexOf(c);
            if (index == -1) return false; // Character not found
            str2 = str2.substring(0, index) + str2.substring(index + 1); // Remove character from str2
        }
        return true;
    }

    // Generate a random anagram
    public static String randomAnagram(String str) {
        str = preProcess(str); // Retain spaces and lowercase only
        char[] chars = str.toCharArray();
        shuffleArray(chars); // Shuffle the array of characters
        return new String(chars);
    }

    // Shuffle array using Fisher-Yates algorithm
    private static void shuffleArray(char[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void main(String[] args) {
        // Test preProcess method
        System.out.println("PreProcess (simple): " + preProcess("Nag a Ram!")); // Expected: "nag a ram"
        System.out.println("PreProcess (spaces): " + preProcess("Hello   World!")); // Expected: "hello   world"
        System.out.println("PreProcess (case): " + preProcess("ABCdef")); // Expected: "abcdef"
        System.out.println("PreProcess (empty): " + preProcess("")); // Expected: ""

        // Test isAnagram method
        System.out.println("Is Anagram (listen, silent): " + isAnagram("listen", "silent")); // Expected: true
        System.out.println("Is Anagram (hello, world): " + isAnagram("hello", "world"));   // Expected: false
        System.out.println("Is Anagram (Nag a Ram, anagram): " + isAnagram("Nag a Ram", "anagram")); // Expected: true

        // Test randomAnagram method
        System.out.println("Random Anagram (java): " + randomAnagram("java")); // Random permutation of "java"
        System.out.println("Random Anagram (hello world): " + randomAnagram("hello world")); // Random permutation
    }
}
