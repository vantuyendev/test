package p1;
public class Person {
    private String name; // private = restricted access
    
    // Getter
    public String getName() {
        return name;
    }
    
    // Setter
    public void setName(String newName) {
        this.name = newName;
    }
    
    public static void testPerson() {
        System.out.println("\n=== Testing Person Class ===");
        
        // Create a Person object
        Person person1 = new Person();
        
        // Test setter
        person1.setName("John");
        
        // Test getter
        System.out.println("Person's name: " + person1.getName());
        
        System.out.println("Person class testing completed!");
    }
}
