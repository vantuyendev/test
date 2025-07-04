package ex1;
public class BreakAndContinue {
    
    public static void WhileTest(int limit) {
        System.out.println("=== Testing While Loop with Break and Continue ===");
        int i = 0;
        while (i < limit) {
            i++;
            if (i == 5) {
                System.out.println("Skipping " + i + " with continue");
                continue;
            }
            if (i == 8) {
                System.out.println("Breaking at " + i);
                break;
            }
            System.out.println("While loop iteration: " + i);
        }
        System.out.println("=== End While Loop Test ===\n");
    }
    
    public static void DoWhileTest(int limit) {
        System.out.println("=== Testing Do-While Loop with Break and Continue ===");
        int i = 0;
        do {
            i++;
            if (i == 3) {
                System.out.println("Skipping " + i + " with continue");
                continue;
            }
            if (i == 7) {
                System.out.println("Breaking at " + i);
                break;
            }
            System.out.println("Do-While loop iteration: " + i);
        } while (i < limit);
        System.out.println("=== End Do-While Loop Test ===\n");
    }
}
