public class TestTime {
    
    public static void testTimeClass() {
        System.out.println("\n=== Advanced Time Testing ===");
        
        // Test time arithmetic
        Time morning = new Time(9, 30, 0);
        Time evening = new Time(17, 45, 30);
        
        System.out.println("Morning time: " + morning);
        System.out.println("Evening time: " + evening);
        
        // Test time addition
        Time lunchTime = new Time(12, 0, 0);
        System.out.println("Lunch time before adding 30 minutes: " + lunchTime);
        lunchTime.addSeconds(30 * 60); // Add 30 minutes
        System.out.println("Lunch time after adding 30 minutes: " + lunchTime);
        
        // Test boundary conditions
        Time nearMidnight = new Time(23, 59, 55);
        System.out.println("Near midnight: " + nearMidnight);
        nearMidnight.addSeconds(10); // Should wrap to next day
        System.out.println("After adding 10 seconds: " + nearMidnight);
        
        // Test conversion to seconds
        Time testTime = new Time(2, 30, 45);
        System.out.println("Time " + testTime + " in seconds: " + testTime.toSeconds());
        
        // Clean up all times
        morning.cleanup();
        evening.cleanup();
        lunchTime.cleanup();
        nearMidnight.cleanup();
        testTime.cleanup();
    }
    
    public static void main(String[] args) {
        testTimeClass();
    }
}
