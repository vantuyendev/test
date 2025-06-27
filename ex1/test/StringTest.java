public class StringTest {
    public static void test() {
        System.out.println("=== Testing Strings ===");
        
        // Test string creation
        String str1 = "Hello";
        String str2 = "World";
        String str3 = new String("Java");
        
        // Test string concatenation
        String result = str1 + " " + str2 + " " + str3;
        System.out.println("Concatenation: " + result);
        
        // Test string methods
        System.out.println("Length of '" + str1 + "': " + str1.length());
        System.out.println("Uppercase: " + str1.toUpperCase());
        System.out.println("Lowercase: " + str1.toLowerCase());
        System.out.println("Character at index 1: " + str1.charAt(1));
        System.out.println("Substring (1,4): " + str1.substring(1, 4));
        
        // Test string comparison
        String str4 = "Hello";
        System.out.println("str1.equals(str4): " + str1.equals(str4));
        System.out.println("str1 == str4: " + (str1 == str4));
        
        System.out.println("=== End Testing Strings ===\n");
    }
}
