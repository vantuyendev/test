public class ShortCircuitTest {
    public static void test() {
        System.out.println("=== Testing Short Circuit Evaluation ===");
        
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
