public class Recursion {
    
    /**
     * Tính giai thừa sử dụng đệ quy
     * Factorial calculation using recursion
     * 
     * Giai thừa của n (n!) = n × (n-1) × (n-2) × ... × 1
     * Factorial of n (n!) = n × (n-1) × (n-2) × ... × 1
     * 
     * Trường hợp cơ sở: 0! = 1 và 1! = 1
     * Base case: 0! = 1 and 1! = 1
     * 
     * Trường hợp đệ quy: n! = n × (n-1)!
     * Recursive case: n! = n × (n-1)!
     * 
     * @param n số cần tính giai thừa
     * @return giai thừa của n
     * @throws IllegalArgumentException nếu n âm
     */
    public static long factorial(int n) {
        // Kiểm tra đầu vào không hợp lệ - giai thừa không được định nghĩa cho số âm
        // Check for invalid input - factorial is not defined for negative numbers
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        // Trường hợp cơ sở: giai thừa của 0 và 1 đều bằng 1
        // Base case: factorial of 0 and 1 is 1
        if (n == 0 || n == 1) {
            return 1;
        }
        // Trường hợp đệ quy: n! = n × (n-1)!
        // Phương thức tự gọi chính nó với giá trị nhỏ hơn cho đến khi đạt được trường hợp cơ sở
        // Recursive case: n! = n × (n-1)!
        // The method calls itself with a smaller value until it reaches the base case
        return n * factorial(n - 1);
    }
    
    /**
     * Dãy số Fibonacci sử dụng đệ quy
     * Fibonacci sequence using recursion
     * 
     * Dãy Fibonacci: 0, 1, 1, 2, 3, 5, 8, 13, 21, ...
     * Fibonacci sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21, ...
     * 
     * Mỗi số là tổng của hai số trước đó
     * Each number is the sum of the two preceding numbers
     * 
     * Trường hợp cơ sở: F(0) = 0, F(1) = 1
     * Base cases: F(0) = 0, F(1) = 1
     * 
     * Trường hợp đệ quy: F(n) = F(n-1) + F(n-2)
     * Recursive case: F(n) = F(n-1) + F(n-2)
     * 
     * Lưu ý: Cách thực hiện này có độ phức tạp thời gian theo cấp số nhân O(2^n)
     * Để có hiệu suất tốt hơn, hãy cân nhắc sử dụng memoization hoặc phương pháp lặp
     * Note: This implementation has exponential time complexity O(2^n)
     * For better performance, consider using memoization or iterative approach
     * 
     * @param n vị trí trong dãy Fibonacci
     * @return số Fibonacci thứ n
     * @throws IllegalArgumentException nếu n âm
     */
    public static long fibonacci(int n) {
        // Kiểm tra đầu vào không hợp lệ - Fibonacci không được định nghĩa cho số âm
        // Check for invalid input - Fibonacci is not defined for negative numbers
        if (n < 0) {
            throw new IllegalArgumentException("Fibonacci is not defined for negative numbers");
        }
        // Trường hợp cơ sở 1: F(0) = 0
        // Base case 1: F(0) = 0
        if (n == 0) {
            return 0;
        }
        // Trường hợp cơ sở 2: F(1) = 1
        // Base case 2: F(1) = 1
        if (n == 1) {
            return 1;
        }
        // Trường hợp đệ quy: F(n) = F(n-1) + F(n-2)
        // Điều này tạo ra một cây nhị phân của các lời gọi đệ quy
        // Recursive case: F(n) = F(n-1) + F(n-2)
        // This creates a binary tree of recursive calls
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    /**
     * Tính lũy thừa sử dụng đệ quy
     * Power calculation using recursion
     * 
     * Tính base^exponent sử dụng phép nhân đệ quy
     * Calculates base^exponent using recursive multiplication
     * 
     * Trường hợp cơ sở: bất kỳ số nào mũ 0 đều bằng 1
     * Base case: any number to the power of 0 equals 1
     * 
     * Trường hợp đệ quy: base^n = base × base^(n-1)
     * Recursive case: base^n = base × base^(n-1)
     * 
     * Đối với số mũ âm: base^(-n) = 1 / base^n
     * For negative exponents: base^(-n) = 1 / base^n
     * 
     * @param base số cơ sở
     * @param exponent số mũ (có thể âm)
     * @return kết quả của base mũ exponent
     */
    public static double power(double base, int exponent) {
        // Trường hợp cơ sở: bất kỳ số nào mũ 0 đều bằng 1
        // Base case: any number to the power of 0 is 1
        if (exponent == 0) {
            return 1;
        }
        // Xử lý số mũ âm: base^(-n) = 1 / base^n
        // Handle negative exponents: base^(-n) = 1 / base^n
        if (exponent < 0) {
            return 1 / power(base, -exponent);
        }
        // Trường hợp đệ quy: base^n = base × base^(n-1)
        // Mỗi lời gọi đệ quy giảm số mũ đi 1
        // Recursive case: base^n = base × base^(n-1)
        // Each recursive call reduces the exponent by 1
        return base * power(base, exponent - 1);
    }
    
    /**
     * Tính tổng các chữ số sử dụng đệ quy
     * Sum of digits using recursion
     * 
     * Tính tổng tất cả các chữ số trong một số
     * Calculates the sum of all digits in a number
     * 
     * Ví dụ: sumOfDigits(123) = 1 + 2 + 3 = 6
     * Example: sumOfDigits(123) = 1 + 2 + 3 = 6
     * 
     * Trường hợp cơ sở: nếu số < 10, trả về chính số đó
     * Base case: if number < 10, return the number itself
     * 
     * Trường hợp đệ quy: chữ số cuối + tổng các chữ số còn lại
     * Recursive case: last digit + sum of remaining digits
     * 
     * @param number số cần tính tổng chữ số (số âm được xử lý)
     * @return tổng tất cả các chữ số trong số đó
     */
    public static int sumOfDigits(int number) {
        // Xử lý số âm bằng cách lấy giá trị tuyệt đối
        // Handle negative numbers by taking absolute value
        number = Math.abs(number);
        // Trường hợp cơ sở: số có một chữ số, trả về chính số đó
        // Base case: single digit number, return the digit itself
        if (number < 10) {
            return number;
        }
        // Trường hợp đệ quy: chữ số cuối (number % 10) + tổng các chữ số còn lại (number / 10)
        // number % 10 lấy chữ số cuối cùng
        // number / 10 loại bỏ chữ số cuối cùng (phép chia nguyên)
        // Recursive case: last digit (number % 10) + sum of remaining digits (number / 10)
        // number % 10 gets the last digit
        // number / 10 removes the last digit (integer division)
        return (number % 10) + sumOfDigits(number / 10);
    }
    
    /**
     * Ước số chung lớn nhất sử dụng đệ quy (thuật toán Euclid)
     * Greatest Common Divisor using recursion (Euclidean algorithm)
     * 
     * GCD của hai số là số lớn nhất chia hết cả hai số mà không có dư
     * The GCD of two numbers is the largest number that divides both without remainder
     * 
     * Thuật toán Euclid: GCD(a, b) = GCD(b, a mod b)
     * Euclidean algorithm: GCD(a, b) = GCD(b, a mod b)
     * 
     * Trường hợp cơ sở: GCD(a, 0) = a
     * Base case: GCD(a, 0) = a
     * 
     * Trường hợp đệ quy: GCD(a, b) = GCD(b, a % b)
     * Recursive case: GCD(a, b) = GCD(b, a % b)
     * 
     * Ví dụ: GCD(48, 18)
     * Example: GCD(48, 18)
     * GCD(48, 18) = GCD(18, 48%18) = GCD(18, 12)
     * GCD(18, 12) = GCD(12, 18%12) = GCD(12, 6)
     * GCD(12, 6) = GCD(6, 12%6) = GCD(6, 0) = 6
     * 
     * @param a số thứ nhất
     * @param b số thứ hai
     * @return ước số chung lớn nhất của a và b
     */
    public static int gcd(int a, int b) {
        // Lấy giá trị tuyệt đối để xử lý số âm
        // Take absolute values to handle negative numbers
        a = Math.abs(a);
        b = Math.abs(b);
        // Trường hợp cơ sở: khi b trở thành 0, a chính là GCD
        // Base case: when b becomes 0, a is the GCD
        if (b == 0) {
            return a;
        }
        // Trường hợp đệ quy: GCD(a, b) = GCD(b, a % b)
        // Điều này tiếp tục cho đến khi b trở thành 0
        // Recursive case: GCD(a, b) = GCD(b, a % b)
        // This continues until b becomes 0
        return gcd(b, a % b);
    }
    
    /**
     * Tìm kiếm nhị phân sử dụng đệ quy
     * Binary search using recursion
     * 
     * Tìm kiếm một giá trị đích trong mảng đã sắp xếp sử dụng phương pháp chia để trị
     * Searches for a target value in a sorted array using divide-and-conquer approach
     * 
     * Độ phức tạp thời gian: O(log n), Độ phức tạp không gian: O(log n) do ngăn xếp đệ quy
     * Time complexity: O(log n), Space complexity: O(log n) due to recursion stack
     * 
     * Thuật toán:
     * Algorithm:
     * 1. Tìm phần tử ở giữa / Find the middle element
     * 2. Nếu giữa == đích, trả về chỉ số / If middle == target, return the index
     * 3. Nếu giữa > đích, tìm ở nửa trái / If middle > target, search in left half
     * 4. Nếu giữa < đích, tìm ở nửa phải / If middle < target, search in right half
     * 5. Nếu trái > phải, không tìm thấy phần tử / If left > right, element not found
     * 
     * @param arr mảng đã sắp xếp để tìm kiếm
     * @param target giá trị cần tìm
     * @param left chỉ số biên trái (bao gồm)
     * @param right chỉ số biên phải (bao gồm)
     * @return chỉ số của target nếu tìm thấy, -1 nếu không tìm thấy
     */
    public static int binarySearch(int[] arr, int target, int left, int right) {
        // Trường hợp cơ sở: phạm vi không hợp lệ, không tìm thấy phần tử
        // Base case: invalid range, element not found
        if (left > right) {
            return -1; // Element not found
        }
        
        // Tính chỉ số giữa (tránh tràn số nguyên)
        // Calculate middle index (avoiding integer overflow)
        int mid = left + (right - left) / 2;
        
        // Trường hợp cơ sở: tìm thấy đích
        // Base case: found the target
        if (arr[mid] == target) {
            return mid;
        } 
        // Trường hợp đệ quy: đích nằm ở nửa trái
        // Recursive case: target is in the left half
        else if (arr[mid] > target) {
            return binarySearch(arr, target, left, mid - 1);
        } 
        // Trường hợp đệ quy: đích nằm ở nửa phải
        // Recursive case: target is in the right half
        else {
            return binarySearch(arr, target, mid + 1, right);
        }
    }
    
    /**
     * Phương thức hỗ trợ cho tìm kiếm nhị phân
     * Helper method for binary search
     * 
     * Cung cấp giao diện đơn giản hơn bằng cách tự động thiết lập ranh giới tìm kiếm
     * Provides a simpler interface by automatically setting the search boundaries
     * 
     * @param arr mảng đã sắp xếp để tìm kiếm
     * @param target giá trị cần tìm
     * @return chỉ số của target nếu tìm thấy, -1 nếu không tìm thấy
     */
    public static int binarySearch(int[] arr, int target) {
        return binarySearch(arr, target, 0, arr.length - 1);
    }
    
    /**
     * Giải bài toán Tháp Hà Nội sử dụng đệ quy
     * Tower of Hanoi puzzle solution using recursion
     * 
     * Bài toán cổ điển: di chuyển n đĩa từ cọc nguồn đến cọc đích
     * Classic problem: move n disks from source peg to destination peg
     * 
     * Quy tắc:
     * Rules:
     * 1. Chỉ có thể di chuyển một đĩa tại một thời điểm / Only one disk can be moved at a time
     * 2. Chỉ có thể di chuyển đĩa ở trên cùng / Only the top disk can be moved
     * 3. Đĩa lớn không thể đặt lên đĩa nhỏ / A larger disk cannot be placed on a smaller disk
     * 
     * Thuật toán:
     * Algorithm:
     * 1. Chuyển n-1 đĩa từ cọc nguồn đến cọc phụ / Move n-1 disks from source to auxiliary peg
     * 2. Chuyển đĩa lớn nhất từ cọc nguồn đến cọc đích / Move the largest disk from source to destination
     * 3. Chuyển n-1 đĩa từ cọc phụ đến cọc đích / Move n-1 disks from auxiliary to destination peg
     * 
     * Độ phức tạp thời gian: O(2^n) - theo cấp số nhân
     * Time complexity: O(2^n) - exponential
     * 
     * Số bước di chuyển cần thiết: 2^n - 1
     * Number of moves required: 2^n - 1
     * 
     * @param n số lượng đĩa cần di chuyển
     * @param source cọc nguồn (vị trí bắt đầu)
     * @param destination cọc đích (vị trí mục tiêu)
     * @param auxiliary cọc phụ (lưu trữ tạm thời)
     */
    public static void towerOfHanoi(int n, char source, char destination, char auxiliary) {
        // Trường hợp cơ sở: chỉ có một đĩa cần di chuyển
        // Base case: only one disk to move
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " + destination);
            return;
        }
        
        // Bước 1: Chuyển n-1 đĩa từ nguồn đến phụ (sử dụng đích làm tạm thời)
        // Step 1: Move n-1 disks from source to auxiliary (using destination as temporary)
        towerOfHanoi(n - 1, source, auxiliary, destination);
        
        // Bước 2: Chuyển đĩa lớn nhất từ nguồn đến đích
        // Step 2: Move the largest disk from source to destination
        System.out.println("Move disk " + n + " from " + source + " to " + destination);
        
        // Bước 3: Chuyển n-1 đĩa từ phụ đến đích (sử dụng nguồn làm tạm thời)
        // Step 3: Move n-1 disks from auxiliary to destination (using source as temporary)
        towerOfHanoi(n - 1, auxiliary, destination, source);
    }
    
    /**
     * Phương thức tĩnh để kiểm tra tất cả chức năng của lớp Recursion
     * Static method to test all Recursion functionality
     * 
     * Minh họa cách sử dụng từng phương thức đệ quy với các đầu vào ví dụ
     * Demonstrates the usage of each recursive method with example inputs
     * 
     * Phương thức này phục vụ như một bộ kiểm tra toàn diện cho lớp
     * This method serves as a comprehensive test suite for the class
     */
    public static void testRecursion() {
        System.out.println("\n=== Kiểm tra lớp Recursion / Testing Recursion Class ===");
        
        // Kiểm tra giai thừa - minh họa đệ quy tuyến tính đơn giản
        // Test factorial - demonstrates simple linear recursion
        System.out.println("Giai thừa của 5 / Factorial of 5: " + factorial(5));  // Kỳ vọng: 120
        System.out.println("Giai thừa của 0 / Factorial of 0: " + factorial(0));  // Kỳ vọng: 1
        
        // Kiểm tra fibonacci - minh họa đệ quy nhị phân (kém hiệu quả hơn)
        // Test fibonacci - demonstrates binary recursion (less efficient)
        System.out.print("Dãy Fibonacci (10 số đầu tiên) / Fibonacci sequence (first 10 numbers): ");
        for (int i = 0; i < 10; i++) {
            System.out.print(fibonacci(i) + " ");  // Kỳ vọng: 0 1 1 2 3 5 8 13 21 34
        }
        System.out.println();
        
        // Kiểm tra lũy thừa - minh họa đệ quy với xử lý số âm
        // Test power - demonstrates recursion with negative handling
        System.out.println("2^8 = " + power(2, 8));      // Kỳ vọng: 256.0
        System.out.println("3^-2 = " + power(3, -2));    // Kỳ vọng: 0.1111... (1/9)
        
        // Kiểm tra tổng chữ số - minh họa đệ quy xử lý số
        // Test sum of digits - demonstrates number processing recursion
        System.out.println("Tổng chữ số trong 12345 / Sum of digits in 12345: " + sumOfDigits(12345));  // Kỳ vọng: 15
        System.out.println("Tổng chữ số trong -987 / Sum of digits in -987: " + sumOfDigits(-987));    // Kỳ vọng: 24
        
        // Kiểm tra GCD - minh họa đệ quy thuật toán Euclid
        // Test GCD - demonstrates Euclidean algorithm recursion
        System.out.println("GCD của 48 và 18 / GCD of 48 and 18: " + gcd(48, 18));    // Kỳ vọng: 6
        System.out.println("GCD của 100 và 25 / GCD of 100 and 25: " + gcd(100, 25));  // Kỳ vọng: 25
        
        // Kiểm tra tìm kiếm nhị phân - minh họa đệ quy chia để trị
        // Test binary search - demonstrates divide-and-conquer recursion
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        System.out.println("Tìm kiếm nhị phân số 7 trong mảng / Binary search for 7 in sorted array: " + binarySearch(sortedArray, 7));   // Kỳ vọng: 3
        System.out.println("Tìm kiếm nhị phân số 4 trong mảng / Binary search for 4 in sorted array: " + binarySearch(sortedArray, 4));   // Kỳ vọng: -1
        
        // Kiểm tra Tháp Hà Nội - minh họa đệ quy phức tạp với nhiều lời gọi
        // Test Tower of Hanoi - demonstrates complex recursion with multiple calls
        System.out.println("\nGiải pháp Tháp Hà Nội cho 3 đĩa / Tower of Hanoi solution for 3 disks:");
        towerOfHanoi(3, 'A', 'C', 'B');  // Hiển thị giải pháp từng bước
    }
}
