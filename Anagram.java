public class Anagram {

    // Preprocess the string: Remove non-alphabetic characters and convert to lowercase
    public static String preProcess(String str) {
        return str.replaceAll("[^a-zA-Z]", "").toLowerCase();
    }

    // Check if two strings are anagrams
    public static boolean isAnagram(String str1, String str2) {
        str1 = preProcess(str1);
        str2 = preProcess(str2);

        if (str1.length() != str2.length()) return false;

        for (char c : str1.toCharArray()) {
            int index = str2.indexOf(c);
            if (index == -1) {
                return false; // Character not found
            }
            str2 = str2.substring(0, index) + str2.substring(index + 1); // Remove character from str2
        }
        return true;
    }

    // Generate a random anagram of a string
    public static String randomAnagram(String str) {
        str = preProcess(str);
        char[] chars = str.toCharArray();
        shuffleArray(chars); // Shuffle the array of characters
        return new String(chars);
    }

    // Shuffle a character array (Fisher-Yates shuffle algorithm)
    private static void shuffleArray(char[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1)); // Random index from 0 to i
            // Swap array[i] and array[j]
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void main(String[] args) {
        // Test preProcess function
        System.out.println("PreProcess: " + preProcess("Nag a Ram!")); // Expected: "nagaram"

        // Test isAnagram function
        System.out.println("Is Anagram (listen, silent): " + isAnagram("listen", "silent")); // Expected: true
        System.out.println("Is Anagram (hello, world): " + isAnagram("hello", "world"));   // Expected: false
        System.out.println("Is Anagram (Nag a Ram, anagram): " + isAnagram("Nag a Ram", "anagram")); // Expected: true

        // Test randomAnagram function
        System.out.println("Random Anagram (java): " + randomAnagram("java")); // Output: Random permutation of "java"
        System.out.println("Random Anagram (hello): " + randomAnagram("hello")); // Output: Random permutation of "hello"
    }
}

