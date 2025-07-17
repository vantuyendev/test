package p1;
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
}
