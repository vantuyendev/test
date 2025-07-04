/**
 * Lớp Time để quản lý thời gian với giờ, phút, giây
 * Hỗ trợ các thao tác cơ bản như cộng thời gian, chuyển đổi định dạng
 */
public class Time {
    // Các thuộc tính private để đảm bảo tính đóng gói
    private int hours;   // Giờ (0-23)
    private int minutes; // Phút (0-59)
    private int seconds; // Giây (0-59)
    
    /**
     * Constructor mặc định - tạo thời gian 00:00:00
     * Sử dụng constructor chaining để gọi constructor có tham số
     */
    public Time() {
        this(0, 0, 0);
    }
    
    /**
     * Constructor với tham số để khởi tạo thời gian cụ thể
     * @param hours giờ (0-23)
     * @param minutes phút (0-59) 
     * @param seconds giây (0-59)
     */
    public Time(int hours, int minutes, int seconds) {
        setTime(hours, minutes, seconds); // Sử dụng setter để validate
        System.out.println("Time object created: " + this);
    }
    
    /**
     * Copy constructor - tạo bản sao từ đối tượng Time khác
     * @param other đối tượng Time cần sao chép
     */
    public Time(Time other) {
        this.hours = other.hours;
        this.minutes = other.minutes;
        this.seconds = other.seconds;
        System.out.println("Time object copied: " + this);
    }
    
    // Setter methods với kiểm tra tính hợp lệ của dữ liệu
    
    /**
     * Thiết lập thời gian với giờ, phút, giây
     * @param hours giờ cần set
     * @param minutes phút cần set
     * @param seconds giây cần set
     */
    public void setTime(int hours, int minutes, int seconds) {
        setHours(hours);
        setMinutes(minutes);
        setSeconds(seconds);
    }
    
    /**
     * Thiết lập giờ với validation
     * Nếu giờ không hợp lệ (< 0 hoặc >= 24), sẽ được set về 0
     * @param hours giờ cần thiết lập (0-23)
     */
    public void setHours(int hours) {
        this.hours = (hours >= 0 && hours < 24) ? hours : 0;
    }
    
    /**
     * Thiết lập phút với validation  
     * Nếu phút không hợp lệ (< 0 hoặc >= 60), sẽ được set về 0
     * @param minutes phút cần thiết lập (0-59)
     */
    public void setMinutes(int minutes) {
        this.minutes = (minutes >= 0 && minutes < 60) ? minutes : 0;
    }
    
    /**
     * Thiết lập giây với validation
     * Nếu giây không hợp lệ (< 0 hoặc >= 60), sẽ được set về 0
     * @param seconds giây cần thiết lập (0-59)
     */
    public void setSeconds(int seconds) {
        this.seconds = (seconds >= 0 && seconds < 60) ? seconds : 0;
    }
    
    // Getter methods để truy xuất giá trị các thuộc tính
    
    /**
     * Lấy giá trị giờ
     * @return giờ hiện tại (0-23)
     */
    public int getHours() {
        return hours;
    }
    
    /**
     * Lấy giá trị phút
     * @return phút hiện tại (0-59)
     */
    public int getMinutes() {
        return minutes;
    }
    
    /**
     * Lấy giá trị giây
     * @return giây hiện tại (0-59)
     */
    public int getSeconds() {
        return seconds;
    }
    
    /**
     * Cộng thêm số giây vào thời gian hiện tại
     * Tự động xử lý việc chuyển đổi giây thành phút và giờ
     * Xử lý overflow khi vượt quá 24 giờ (quay về từ đầu)
     * @param secondsToAdd số giây cần cộng thêm
     */
    public void addSeconds(int secondsToAdd) {
        // Tính tổng số giây
        int totalSeconds = this.seconds + secondsToAdd;
        this.seconds = totalSeconds % 60; // Lấy phần dư cho giây
        
        // Tính số phút cần cộng thêm từ giây thừa
        int minutesToAdd = totalSeconds / 60;
        int totalMinutes = this.minutes + minutesToAdd;
        this.minutes = totalMinutes % 60; // Lấy phần dư cho phút
        
        // Tính số giờ cần cộng thêm từ phút thừa  
        int hoursToAdd = totalMinutes / 60;
        this.hours = (this.hours + hoursToAdd) % 24; // Xử lý overflow 24h
    }
    
    /**
     * Chuyển đổi thời gian thành tổng số giây kể từ 00:00:00
     * @return tổng số giây từ nửa đêm
     */
    public int toSeconds() {
        return hours * 3600 + minutes * 60 + seconds;
    }
    
    /**
     * Phương thức dọn dẹp - reset thời gian về 00:00:00
     * Hữu ích cho việc quản lý bộ nhớ và reset trạng thái
     */
    public void cleanup() {
        System.out.println("Cleaning up time object: " + this);
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    }
    
    /**
     * Phương thức static để test chức năng của lớp Time
     * Tạo và thử nghiệm các đối tượng Time với nhiều trường hợp khác nhau
     */
    public static void testTime() {
        System.out.println("\n=== Testing Time Class ===");
        
        // Tạo các đối tượng Time để test
        Time time1 = new Time(); // Constructor mặc định
        Time time2 = new Time(14, 30, 45); // Constructor có tham số
        Time time3 = new Time(time2); // Copy constructor
        
        System.out.println("Time 1 (default): " + time1);
        System.out.println("Time 2: " + time2);
        System.out.println("Time 3 (copy): " + time3);
        
        // Test phương thức cộng giây
        time1.setTime(23, 59, 50);
        System.out.println("Time 1 before adding 20 seconds: " + time1);
        time1.addSeconds(20); // Test overflow qua ngày mới
        System.out.println("Time 1 after adding 20 seconds: " + time1);
        
        // Test chuyển đổi sang giây
        System.out.println("Time 2 in total seconds: " + time2.toSeconds());
        
        // Test với giá trị không hợp lệ
        Time time4 = new Time(25, 70, 80); // Giá trị vượt ngưỡng
        System.out.println("Time 4 (with invalid values): " + time4);
        
        // Dọn dẹp các đối tượng
        time1.cleanup();
        time2.cleanup();
        time3.cleanup();
        time4.cleanup();
    }
    
    /**
     * Override phương thức toString để hiển thị thời gian dạng HH:MM:SS
     * @return chuỗi định dạng thời gian 24h
     */
    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
