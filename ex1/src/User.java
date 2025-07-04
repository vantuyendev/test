public class User {
    private String name;
    private String email;
    private int age;
    private static int userCount = 0;
    
    // Constructor
    public User(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
        userCount++;
        System.out.println("User created: " + name);
    }
    
    // Copy constructor
    public User(User other) {
        this.name = other.name;
        this.email = other.email;
        this.age = other.age;
        userCount++;
        System.out.println("User copied: " + name);
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public int getAge() {
        return age;
    }
    
    public static int getUserCount() {
        return userCount;
    }
    
    // Setters
    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
    }
    
    // Clean up method
    public void cleanup() {
        System.out.println("Cleaning up user: " + name);
        this.name = null;
        this.email = null;
        this.age = 0;
    }
    
    // Static method to test User functionality
    public static void testUser() {
        System.out.println("\n=== Testing User Class ===");
        
        User user1 = new User("Tuyen", "tuyen@email.com", 19);
        User user2 = new User("ABC", "abc@email.com", 30);
        User user3 = new User(user1); // Copy constructor
        
        System.out.println("Total users created: " + User.getUserCount());
        
        System.out.println("User 1: " + user1.getName() + ", " + user1.getEmail() + ", " + user1.getAge());
        System.out.println("User 2: " + user2.getName() + ", " + user2.getEmail() + ", " + user2.getAge());
        System.out.println("User 3 (copy): " + user3.getName() + ", " + user3.getEmail() + ", " + user3.getAge());
        
        // Test setters
        user1.setAge(26);
        user1.setEmail("abc@newemail.com");
        System.out.println("Updated User 1: " + user1.getName() + ", " + user1.getEmail() + ", " + user1.getAge());
        
        // Clean up
        user1.cleanup();
        user2.cleanup();
        user3.cleanup();
    }
    
    @Override
    public String toString() {
        return "User{name='" + name + "', email='" + email + "', age=" + age + "}";
    }
}
