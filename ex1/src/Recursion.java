public class Recursion {
    
    // Factorial calculation using recursion
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
    
    // Fibonacci sequence using recursion
    public static long fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Fibonacci is not defined for negative numbers");
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    // Power calculation using recursion
    public static double power(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent < 0) {
            return 1 / power(base, -exponent);
        }
        return base * power(base, exponent - 1);
    }
    
    // Sum of digits using recursion
    public static int sumOfDigits(int number) {
        number = Math.abs(number); // Handle negative numbers
        if (number < 10) {
            return number;
        }
        return (number % 10) + sumOfDigits(number / 10);
    }
    
    // Greatest Common Divisor using recursion (Euclidean algorithm)
    public static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    
    // Binary search using recursion
    public static int binarySearch(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1; // Element not found
        }
        
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            return binarySearch(arr, target, left, mid - 1);
        } else {
            return binarySearch(arr, target, mid + 1, right);
        }
    }
    
    // Helper method for binary search
    public static int binarySearch(int[] arr, int target) {
        return binarySearch(arr, target, 0, arr.length - 1);
    }
    
    // Tower of Hanoi puzzle
    public static void towerOfHanoi(int n, char source, char destination, char auxiliary) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " + destination);
            return;
        }
        towerOfHanoi(n - 1, source, auxiliary, destination);
        System.out.println("Move disk " + n + " from " + source + " to " + destination);
        towerOfHanoi(n - 1, auxiliary, destination, source);
    }
    
    // Static method to test Recursion functionality
    public static void testRecursion() {
        System.out.println("\n=== Testing Recursion Class ===");
        
        // Test factorial
        System.out.println("Factorial of 5: " + factorial(5));
        System.out.println("Factorial of 0: " + factorial(0));
        
        // Test fibonacci
        System.out.print("Fibonacci sequence (first 10 numbers): ");
        for (int i = 0; i < 10; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();
        
        // Test power
        System.out.println("2^8 = " + power(2, 8));
        System.out.println("3^-2 = " + power(3, -2));
        
        // Test sum of digits
        System.out.println("Sum of digits in 12345: " + sumOfDigits(12345));
        System.out.println("Sum of digits in -987: " + sumOfDigits(-987));
        
        // Test GCD
        System.out.println("GCD of 48 and 18: " + gcd(48, 18));
        System.out.println("GCD of 100 and 25: " + gcd(100, 25));
        
        // Test binary search
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        System.out.println("Binary search for 7 in sorted array: " + binarySearch(sortedArray, 7));
        System.out.println("Binary search for 4 in sorted array: " + binarySearch(sortedArray, 4));
        
        // Test Tower of Hanoi (small example)
        System.out.println("\nTower of Hanoi solution for 3 disks:");
        towerOfHanoi(3, 'A', 'C', 'B');
    }
}
