package testEx1;
public class CastingTest {
    public static void testDouble() {
        System.out.println("=== Testing Double Casting ===");
        
        double d = 123.456;
        System.out.println("Original double: " + d);
        
        // Implicit casting (widening)
        double d2 = 100; // int to double
        System.out.println("Int to double: " + d2);
        
        // Explicit casting (narrowing)
        int i = (int) d;
        System.out.println("Double to int (truncated): " + i);
        
        float f = (float) d;
        System.out.println("Double to float: " + f);
        
        System.out.println("=== End Testing Double Casting ===\n");
    }
    
    public static void testFloat() {
        System.out.println("=== Testing Float Casting ===");
        
        float f = 123.456f;
        System.out.println("Original float: " + f);
        
        // Casting float to other types
        int i = (int) f;
        double d = f; // implicit casting
        long l = (long) f;
        
        System.out.println("Float to int: " + i);
        System.out.println("Float to double: " + d);
        System.out.println("Float to long: " + l);
        
        System.out.println("=== End Testing Float Casting ===\n");
    }
    
    public static void testCasting() {
        System.out.println("=== Testing General Casting ===");
        
        // Integer casting
        int i = 1000;
        byte b = (byte) i; // Overflow will occur
        short s = (short) i;
        long l = i; // implicit casting
        
        System.out.println("Original int: " + i);
        System.out.println("Int to byte (with overflow): " + b);
        System.out.println("Int to short: " + s);
        System.out.println("Int to long: " + l);
        
        // Character casting
        char c = 'A';
        int charAsInt = c;
        System.out.println("Char 'A' as int: " + charAsInt);
        
        int intValue = 66;
        char intAsChar = (char) intValue;
        System.out.println("Int 66 as char: " + intAsChar);
        
        System.out.println("=== End Testing General Casting ===\n");
    }
}
