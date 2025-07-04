package ex1;
public class PassObject {
    static void f(Number m) {
        System.out.println("Inside f(): Changing m.i from " + m.i + " to 15");
        m.i = 15;
    }

    public static void testPassObject() {
        System.out.println("=== Testing PassObject (Object Parameter Passing) ===");
        
        Number n = new Number();
        n.i = 14;
        System.out.println("Before calling f(): n.i = " + n.i);
        
        PassObject.f(n);
        
        System.out.println("After calling f(): n.i = " + n.i);
        System.out.println("Objects are passed by reference, so n.i is now 15");
        
        System.out.println("=== End Testing PassObject ===\n");
    }
}