package testEx1;
public class TestNumber {
    public static void testNumber() {
        System.out.println("=== Testing Numbers ===");
        
        // Test different number types
        byte b = 127;
        short s = 32767;
        int i = 2147483647;
        long l = 9223372036854775807L;
        
        float f = 3.14f;
        double d = 3.14159265359;
        
        System.out.println("Byte: " + b);
        System.out.println("Short: " + s);
        System.out.println("Int: " + i);
        System.out.println("Long: " + l);
        System.out.println("Float: " + f);
        System.out.println("Double: " + d);
        
        // Test number operations
        System.out.println("Addition: " + (10 + 5));
        System.out.println("Subtraction: " + (10 - 5));
        System.out.println("Multiplication: " + (10 * 5));
        System.out.println("Division: " + (10 / 5));
        System.out.println("Modulus: " + (10 % 3));
        
        System.out.println("=== End Testing Numbers ===\n");
    }
}
