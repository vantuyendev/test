public class Leaf {
    int i = 0;
    
    public static void testleaf() {
        Leaf x = new Leaf();
        x.increment().increment().increment().print(); //3
    }   
    Leaf increment() {
        i++;
        return this; //Leaf
    }   
    void print() { 
        System.out.println("=== Testing Leaf Class ===");
        System.out.println("i = " + i); 
        System.out.println("=== Leaf Testing Complete ===\n");
    }
}