public class TestRecursion {
    
    public static void testRecursionClass() {
        System.out.println("\n=== Advanced Recursion Testing ===");
        
        // Test performance and accuracy
        System.out.println("Testing larger factorial calculations:");
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + "! = " + Recursion.factorial(i));
        }
        
        // Test fibonacci performance
        System.out.println("\nTesting fibonacci performance:");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i <= 20; i++) {
            long fib = Recursion.fibonacci(i);
            System.out.println("F(" + i + ") = " + fib);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Fibonacci calculation took: " + (endTime - startTime) + " ms");
        
        // Test power with negative exponents
        System.out.println("\nTesting power calculations:");
        System.out.println("5^0 = " + Recursion.power(5, 0));
        System.out.println("2^10 = " + Recursion.power(2, 10));
        System.out.println("3^-3 = " + Recursion.power(3, -3));
        
        // Test edge cases for sum of digits
        System.out.println("\nTesting sum of digits edge cases:");
        System.out.println("Sum of digits in 0: " + Recursion.sumOfDigits(0));
        System.out.println("Sum of digits in 9: " + Recursion.sumOfDigits(9));
        System.out.println("Sum of digits in 999999: " + Recursion.sumOfDigits(999999));
        
        // Test GCD with various cases
        System.out.println("\nTesting GCD edge cases:");
        System.out.println("GCD(0, 5) = " + Recursion.gcd(0, 5));
        System.out.println("GCD(17, 13) = " + Recursion.gcd(17, 13)); // Coprime numbers
        System.out.println("GCD(1001, 1331) = " + Recursion.gcd(1001, 1331));
        
        // Test binary search with edge cases
        System.out.println("\nTesting binary search edge cases:");
        int[] emptyArray = {};
        int[] singleElement = {42};
        int[] evenLength = {1, 3, 5, 7};
        
        System.out.println("Search in empty array: " + Recursion.binarySearch(emptyArray, 5));
        System.out.println("Search in single element array for existing: " + Recursion.binarySearch(singleElement, 42));
        System.out.println("Search in single element array for non-existing: " + Recursion.binarySearch(singleElement, 10));
        System.out.println("Search first element: " + Recursion.binarySearch(evenLength, 1));
        System.out.println("Search last element: " + Recursion.binarySearch(evenLength, 7));
    }
    
    public static void main(String[] args) {
        testRecursionClass();
    }
}
