public class Time {
    private int hours;
    private int minutes;
    private int seconds;
    
    // Default constructor
    public Time() {
        this(0, 0, 0);
    }
    
    // Constructor with parameters
    public Time(int hours, int minutes, int seconds) {
        setTime(hours, minutes, seconds);
        System.out.println("Time object created: " + this);
    }
    
    // Copy constructor
    public Time(Time other) {
        this.hours = other.hours;
        this.minutes = other.minutes;
        this.seconds = other.seconds;
        System.out.println("Time object copied: " + this);
    }
    
    // Setters with validation
    public void setTime(int hours, int minutes, int seconds) {
        setHours(hours);
        setMinutes(minutes);
        setSeconds(seconds);
    }
    
    public void setHours(int hours) {
        this.hours = (hours >= 0 && hours < 24) ? hours : 0;
    }
    
    public void setMinutes(int minutes) {
        this.minutes = (minutes >= 0 && minutes < 60) ? minutes : 0;
    }
    
    public void setSeconds(int seconds) {
        this.seconds = (seconds >= 0 && seconds < 60) ? seconds : 0;
    }
    
    // Getters
    public int getHours() {
        return hours;
    }
    
    public int getMinutes() {
        return minutes;
    }
    
    public int getSeconds() {
        return seconds;
    }
    
    // Add seconds to time
    public void addSeconds(int secondsToAdd) {
        int totalSeconds = this.seconds + secondsToAdd;
        this.seconds = totalSeconds % 60;
        
        int minutesToAdd = totalSeconds / 60;
        int totalMinutes = this.minutes + minutesToAdd;
        this.minutes = totalMinutes % 60;
        
        int hoursToAdd = totalMinutes / 60;
        this.hours = (this.hours + hoursToAdd) % 24;
    }
    
    // Convert to total seconds since midnight
    public int toSeconds() {
        return hours * 3600 + minutes * 60 + seconds;
    }
    
    // Clean up method
    public void cleanup() {
        System.out.println("Cleaning up time object: " + this);
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    }
    
    // Static method to test Time functionality
    public static void testTime() {
        System.out.println("\n=== Testing Time Class ===");
        
        Time time1 = new Time();
        Time time2 = new Time(14, 30, 45);
        Time time3 = new Time(time2); // Copy constructor
        
        System.out.println("Time 1 (default): " + time1);
        System.out.println("Time 2: " + time2);
        System.out.println("Time 3 (copy): " + time3);
        
        // Test adding seconds
        time1.setTime(23, 59, 50);
        System.out.println("Time 1 before adding 20 seconds: " + time1);
        time1.addSeconds(20);
        System.out.println("Time 1 after adding 20 seconds: " + time1);
        
        // Test total seconds
        System.out.println("Time 2 in total seconds: " + time2.toSeconds());
        
        // Test invalid values
        Time time4 = new Time(25, 70, 80); // Invalid values
        System.out.println("Time 4 (with invalid values): " + time4);
        
        // Clean up
        time1.cleanup();
        time2.cleanup();
        time3.cleanup();
        time4.cleanup();
    }
    
    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
