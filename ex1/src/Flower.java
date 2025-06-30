public class Flower {
    int petalCount = 0;
    String s;
    
    Flower(int petals) { petalCount = petals; }
    Flower(String ss) { s = ss; }
    Flower(String s, int petals) {
        this(petals);
        //! this(s); // can't do it twice
        this.s = s;
    }
    Flower() { this("hi", 47); 
    } // default constructor
    
    public static void testFlower() {
        System.out.println("=== Testing Flower Class ===");
        String s = new String("null");
        System.out.println("=== Flower Testing Complete ===\n");
    }
}
