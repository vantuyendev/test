public class ShortCircuit {
    static boolean test1(int val) {
        System.out.println("test1(" + val + "): " + (val < 1));
        return val < 1;
    }
    
    static boolean test2(int val) {
        System.out.println("test2(" + val + "): " + (val < 2));
        return val < 2;
    }
    
    static boolean test3(int val) {
        System.out.println("test3(" + val + "): " + (val < 3));
        return val < 3;
    }
    
    public static void main(String[] args) {
        System.out.println("Testing short-circuit evaluation:");
        
        if (ShortCircuit.test1(0) && 
            ShortCircuit.test2(2) && 
            ShortCircuit.test3(2)) {
            System.out.println("Expression is true");
        } else {
            System.out.println("Expression is false");
        }
        
        System.out.println("\nExplanation:");
        System.out.println("test1(0): 0 < 1 = true");
        System.out.println("test2(2): 2 < 2 = false");
        System.out.println("Since test2 returns false, test3 is never called due to short-circuit evaluation");
    }
}
