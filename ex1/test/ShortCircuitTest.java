public class ShortCircuitTest {
    public static void test() {
        System.out.println("=== Testing Short Circuit Evaluation ===");
        
        System.out.println("Testing && (logical AND):");
        boolean result1 = false && printAndReturn("This won't print", true);
        System.out.println("Result: " + result1);
        
        System.out.println("\nTesting || (logical OR):");
        boolean result2 = true || printAndReturn("This won't print", false);
        System.out.println("Result: " + result2);
        
        System.out.println("\nTesting with method calls:");
        boolean result3 = testValue(5) > 10 && testValue(3) < 5;
        System.out.println("Final result: " + result3);
        
        System.out.println("=== End Testing Short Circuit Evaluation ===\n");
    }
    
    private static boolean printAndReturn(String message, boolean value) {
        System.out.println(message);
        return value;
    }
    
    private static int testValue(int val) {
        System.out.println("Testing value: " + val);
        return val;
    }
}
