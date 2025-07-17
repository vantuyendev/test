public class TestUser {
    
    public static void testUserClass() {
        System.out.println("\n=== Advanced User Testing ===");
        
        // Test multiple users
        System.out.println("Creating multiple users...");
        for (int i = 1; i <= 3; i++) {
            new User("User" + i, "user" + i + "@test.com", 20 + i);
        }
        
        // Test edge cases
        System.out.println("\nTesting edge cases...");
        User testUser = new User("Test User", "test@example.com", 25);
        
        // Test invalid age
        testUser.setAge(-5);
        System.out.println("Age after setting to -5: " + testUser.getAge());
        
        // Test valid age
        testUser.setAge(30);
        System.out.println("Age after setting to 30: " + testUser.getAge());
        
        // Test toString method
        System.out.println("User toString: " + testUser.toString());
        
        // Clean up
        testUser.cleanup();
        System.out.println("User after cleanup: " + testUser.toString());
    }
    
    public static void main(String[] args) {
        testUserClass();
    }
}
