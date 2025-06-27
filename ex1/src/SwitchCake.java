public class SwitchCake {
    public static void switchCase() {
        System.out.println("=== Testing Switch Case ===");
        
        // Test with different values
        int[] testValues = {1, 2, 3, 4, 5, 99};
        
        for (int value : testValues) {
            System.out.print("Testing value " + value + ": ");
            
            switch (value) {
                case 1:
                    System.out.println("One");
                    break;
                case 2:
                    System.out.println("Two");
                    break;
                case 3:
                    System.out.println("Three");
                    break;
                case 4:
                case 5:
                    System.out.println("Four or Five");
                    break;
                default:
                    System.out.println("Other number");
                    break;
            }
        }
        
        // Test with String switch (Java 7+)
        String[] fruits = {"apple", "banana", "orange", "grape"};
        System.out.println("\nTesting String switch:");
        
        for (String fruit : fruits) {
            System.out.print("Fruit " + fruit + ": ");
            
            switch (fruit) {
                case "apple":
                    System.out.println("Red fruit");
                    break;
                case "banana":
                    System.out.println("Yellow fruit");
                    break;
                case "orange":
                    System.out.println("Orange fruit");
                    break;
                default:
                    System.out.println("Unknown fruit");
                    break;
            }
        }
        
        System.out.println("=== End Testing Switch Case ===\n");
    }
}
