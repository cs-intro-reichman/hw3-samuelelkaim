public class Anagram {

    // Preprocess the string
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
            if (index == -1) return false;
            str2 = str2.substring(0, index) + str2.substring(index + 1);
        }
        return true;
    }

    // Generate a random anagram
    public static String randomAnagram(String str) {
        char[] chars = preProcess(str).toCharArray();
        shuffleArray(chars);
        return new String(chars);
    }

    // Shuffle array using Fisher-Yates algorithm
    private static void shuffleArray(char[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void main(String[] args) {
        System.out.println("PreProcess: " + preProcess("Nag a Ram!")); // Expected: "nagaram"
        System.out.println("Is Anagram (listen, silent): " + isAnagram("listen", "silent")); // Expected: true
        System.out.println("Is Anagram (hello, world): " + isAnagram("hello", "world"));   // Expected: false
        System.out.println("Is Anagram (Nag a Ram, anagram): " + isAnagram("Nag a Ram", "anagram")); // Expected: true
        System.out.println("Random Anagram (java): " + randomAnagram("java")); // Random permutation
    }
}
