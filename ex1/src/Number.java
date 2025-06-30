public class Number {
    public int i;

    public static void testNumber() {
        System.out.println("=== Testing Number Class (Object References) ===");
        
        Number n1 = new Number();
        Number n2 = new Number();
        n1.i = 2;
        n2.i = 5;
        
        System.out.println("Initial values:");
        System.out.println("n1.i = " + n1.i);
        System.out.println("n2.i = " + n2.i);
        
        System.out.println("\nAfter n1 = n2 (reference assignment):");
        n1 = n2;
        System.out.println("n1.i = " + n1.i);
        System.out.println("n2.i = " + n2.i);
        
        System.out.println("\nAfter n2.i = 10:");
        n2.i = 10;
        System.out.println("n1.i = " + n1.i + " (should also be 10, same object)");
        System.out.println("n2.i = " + n2.i);
        
        System.out.println("\nAfter n1.i = 20:");
        n1.i = 20;
        System.out.println("n1.i = " + n1.i);
        System.out.println("n2.i = " + n2.i + " (should also be 20, same object)");
        
        System.out.println("=== End Testing Number Class ===\n");
    }
}