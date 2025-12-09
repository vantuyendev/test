# Hệ Thống Quản Lý Điểm Học Tập THCS - Spring Boot Web Application

## 📋 Giới Thiệu

Đây là **Spring Boot Web Application** của **Hệ Thống Quản Lý Điểm Học Tập** cho học sinh Trung Học Cơ Sở (THCS), được phát triển hoàn toàn bằng **Java Spring Boot** với giao diện web hiện đại, dựa trên các yêu cầu trong báo cáo môn **Lập Trình Hướng Đối Tượng (OOP)** của trường Đại Học Phenikaa.

Ứng dụng này áp dụng **toàn bộ nguyên lý OOP** bao gồm:
- ✅ **Đóng gói (Encapsulation)**
- ✅ **Kế thừa (Inheritance)**
- ✅ **Đa hình (Polymorphism)**
- ✅ **Trừu tượng (Abstraction)**

## 🌐 Live Demo

**Link ứng dụng**: Có thể deploy lên Railway (free) để có link cố định
- Xem hướng dẫn: `springboot-web/DEPLOYMENT_GUIDE.md`

## 🚀 Tính Năng Chính

### 1. **Bảng Điều Khiển (Dashboard)**
- Thống kê tổng học sinh
- Biểu đồ phân loại học lực
- Top 5 học sinh có DTBC cao nhất
- Hiển thị số lượng học sinh theo xếp loại

### 2. **Quản Lý Học Sinh**
- ✨ Thêm, sửa, xóa thông tin học sinh
- Quản lý mã HS, họ tên, lớp, năm sinh
- Kiểm tra trùng lặp mã HS
- Danh sách hiển thị trực quan

### 3. **Quản Lý Điểm**
- 📝 Thêm/sửa điểm cho từng môn học
- Hỗ trợ điểm Thường Xuyên (TX) - có thể nhập nhiều lần
- Hỗ trợ điểm Giữa Kỳ (GK) và Cuối Kỳ (CK)
- **Tính toán DTB Môn tự động** theo công thức trọng số:
  ```
  DTB_Mon = (Σ(Điểm_TX × 1) + (Điểm_GK × 2) + (Điểm_CK × 3)) / (Số_lần_TX × 1 + 2 + 3)
  ```

### 4. **Báo Cáo & Thống Kê**
- 📊 Thống kê học sinh theo loại xếp loại
- Xếp hạng học sinh theo DTBC
- Thống kê theo môn học (max, min, average)
- Hiển thị tỷ lệ phần trăm

### 5. **Lưu Trữ Dữ Liệu Bền Vững**
- 💾 Sử dụng **Java Serialization** để lưu/tải dữ liệu từ file nhị phân
- Tự động lưu dữ liệu khi thoát ứng dụng
- Phục hồi dữ liệu khi khởi động lại

## 🛠️ Công Nghệ & Công Cụ

### Công Nghệ
- **Ngôn ngữ**: Java 17+
- **Framework**: Spring Boot 3.2.1
- **Database**: H2 (Embedded SQL Database)
- **Build System**: Gradle
- **Frontend**: HTML5, CSS3, JavaScript

### Yêu Cầu Hệ Thống
- **JDK 17** hoặc cao hơn
- **Gradle** (đã có trong project)
- **RAM**: Tối thiểu 512MB
- **Port**: 8080 (có thể thay đổi)

## 📁 Cấu Trúc Dự Án

```
test/
└── springboot-web/
    ├── src/main/
    │   ├── java/com/quanlydiem/
    │   │   ├── QuanLyDiemApplication.java  # Main application
    │   │   ├── models/                     # JPA Entities
    │   │   │   ├── HocSinh.java
    │   │   │   ├── DiemMonHoc.java
    │   │   │   ├── IXepLoai.java
    │   │   │   └── XepLoaiQuyCheChuan.java
    │   │   ├── controllers/                # REST Controllers
    │   │   │   ├── HocSinhController.java
    │   │   │   ├── DiemMonHocController.java
    │   │   │   └── PageController.java
    │   │   ├── services/                   # Business Logic
    │   │   │   ├── HocSinhService.java
    │   │   │   └── DiemMonHocService.java
    │   │   └── repositories/               # Data Access
    │   │       ├── HocSinhRepository.java
    │   │       └── DiemMonHocRepository.java
    │   └── resources/
    │       ├── application.yml             # Configuration
    │       ├── templates/
    │       │   └── index.html              # Frontend
    │       └── static/
    │           ├── css/style.css
    │           └── js/app.js
    ├── build.gradle                        # Gradle build configuration
    ├── Procfile                            # Railway deployment
    ├── system.properties                   # Java version
    ├── run.sh                              # Script chạy
    ├── build.sh                            # Script build
    └── README.md                           # Documentation
```

## 🚀 Hướng Dẫn Cài Đặt & Chạy

### **Chạy Cục Bộ (Local Development)**

```bash
# Di chuyển vào folder Spring Boot
cd springboot-web

# Chạy ứng dụng
./run.sh

# Hoặc sử dụng Gradle trực tiếp
./gradlew bootRun
```

**Truy cập**: http://localhost:8080

### **Build JAR File (Production)**

```bash
cd springboot-web
./build.sh

# Chạy JAR
java -jar build/libs/quan-ly-diem-1.0.0.jar
```

### **Deploy lên Railway (Free - Link Cố Định)**

Xem hướng dẫn chi tiết trong `springboot-web/DEPLOYMENT_GUIDE.md`

**Tóm tắt:**
1. Push code lên GitHub
2. Vào https://railway.app
3. "New Project" → "Deploy from GitHub"
4. Chọn repository
5. Nhận link: `https://quan-ly-diem-xxx.railway.app`

**Trên Linux/Mac:**
```bash
chmod +x run.sh
./run.sh
```

**Trên Windows:**
```cmd
run.bat
```

### **Cách 3: Biên Dịch & Chạy Thủ Công**

```bash
# Tạo thư mục bin
mkdir bin

# Biên dịch
javac -d bin -sourcepath src/main/java $(find src/main/java -name "*.java")

# Chạy
cd bin
java -cp . ui.MainFrame
```

## 📖 Hướng Dẫn Sử Dụng

### **Thêm Học Sinh**
1. Chuyển đến tab **"👤 Quản Lý Học Sinh"**
2. Điền thông tin:
   - **Mã HS**: Mã định danh duy nhất (VD: HS001)
   - **Tên**: Họ tên học sinh
   - **Lớp**: Lớp học (VD: 9A1)
   - **Năm Sinh**: Chọn từ spinner
3. Nhấp nút **"Thêm"**

### **Quản Lý Điểm**
1. Chuyển đến tab **"📝 Quản Lý Điểm"**
2. Chọn học sinh từ dropdown
3. Nhập thông tin:
   - **Môn Học**: Tên môn (VD: Toán, Ngữ Văn)
   - **Điểm Thường Xuyên**: Nhập từng điểm, click "Thêm"
   - **Điểm Giữa Kỳ**: Nhập điểm GK
   - **Điểm Cuối Kỳ**: Nhập điểm CK
4. Nhấp **"Lưu Điểm"**
5. DTB Môn sẽ tính tự động trong bảng

### **Xem Báo Cáo**
1. Chuyển đến tab **"📈 Báo Cáo"**
2. Xem các thống kê:
   - **Thống Kê Xếp Loại**: Số học sinh theo từng loại
   - **Xếp Hạng Học Sinh**: Danh sách xếp hạng
   - **Thống Kê Môn Học**: DTB cao nhất/thấp nhất theo môn

### **Lưu Dữ Liệu**
- Ứng dụng tự động hỏi có lưu dữ liệu khi thoát
- Hoặc click nút **"💾 Lưu Dữ Liệu"** ở thanh status

## 📊 Công Thức Tính Điểm

### **Điểm Trung Bình Môn (DTB_Mon)**
```
DTB_Mon = (Σ(Điểm_TX × 1) + (Điểm_GK × 2) + (Điểm_CK × 3)) / (Số_lần_TX + 2 + 3)
```

### **Xếp Loại Học Lực**
- **Xuất Sắc**: DTBC ≥ 9.0
- **Giỏi**: DTBC ≥ 8.0
- **Khá**: DTBC ≥ 6.5
- **Trung Bình**: DTBC ≥ 5.0
- **Yếu**: DTBC < 5.0

## 🎓 Nguyên Lý OOP Áp Dụng

### **1. Đóng Gói (Encapsulation)**
```java
// DiemMonHoc.java - Kiểm soát ràng buộc dữ liệu
public boolean setDiemGiuaKy(double diem) {
    if (diem >= 0 && diem <= 10) {
        this.diemGiuaKy = diem;
        return true;
    }
    return false;
}
```

### **2. Kế Thừa (Inheritance)**
```java
// HocSinh kế thừa từ ConNguoi
public class HocSinh extends ConNguoi {
    // Reuse hoTen, namSinh từ ConNguoi
}
```

### **3. Đa Hình (Polymorphism)**
```java
// Interface IXepLoai cho phép thay đổi chiến lược xếp loại
public interface IXepLoai {
    String xepLoai(double dtbc);
}

// Triển khai cụ thể
public class XepLoaiQuyCheChuan implements IXepLoai { }
```

### **4. Trừu Tượng (Abstraction)**
```java
// Lớp cơ sở trừu tượng ConNguoi
public abstract class ConNguoi {
    public abstract String hienThiThongTinCoBan();
}
```

## 💾 Lưu Trữ Dữ Liệu

### **Serialization**
- Tất cả lớp Model implement `Serializable`
- Dữ liệu được lưu vào file `data.ser` (nhị phân)
- Bảo vệ tính toàn vẹn dữ liệu

### **Cơ Chế I/O**
```java
// Lưu dữ liệu
DataPersistence.luuDuLieu(quanLyDiem);

// Tải dữ liệu
QuanLyDiem quanLyDiem = DataPersistence.taiDuLieu();
```

## 🔧 Mở Rộng & Phát Triển

### **Thêm Chiến Lược Xếp Loại Mới**
```java
public class XepLoaiQuyChe2025 implements IXepLoai {
    @Override
    public String xepLoai(double dtbc) {
        // Implement logic mới
    }
}

// Sử dụng
quanLyDiem.setXepLoaiStrategy(new XepLoaiQuyChe2025());
```

### **Thêm Tính Năng Mới**
1. Tạo Panel mới trong package `ui`
2. Extend `JPanel` và implement giao diện
3. Thêm tab vào `MainFrame.java`

## ⚠️ Giới Hạn & Hạn Chế

- Không hỗ trợ đa người dùng cùng lúc
- Dữ liệu lưu cục bộ (không đồng bộ qua mạng)
- Không có xác thực/phân quyền người dùng
- GUI dùng Swing (không phải JavaFX hiện đại)

## 📚 Tài Liệu Tham Khảo

- [Java Official Documentation](https://docs.oracle.com/en/java/)
- [Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)
- [Java Serialization](https://docs.oracle.com/javase/tutorial/jndi/objects/serial.html)
- [Design Patterns - Gang of Four](https://en.wikipedia.org/wiki/Design_Patterns)

## 👥 Tác Giả

- **Phạm Công Vinh** (Mã SV: 24100297)
- **Hoàng Mạnh Quân** (Mã SV: 24107547)

**Giảng viên**: TS. Trần Đăng Hoan  
**Lớp**: CSE703029-2-1-25(N04)  
**Trường**: Đại Học Phenikaa  
**Môn học**: Lập Trình Hướng Đối Tượng (OOP)

## 📝 Ghi Chú

- Đây là phiên bản **hoàn chỉnh** của dự án OOP
- **Không sử dụng database bên ngoài** - chỉ dùng Java Serialization
- **Không cần dependencies ngoài** - chỉ dùng Java Standard Library
- **Tập trung vào nguyên lý OOP** thay vì giao diện đẹp

## 📞 Hỗ Trợ & Khắc Phục Sự Cố

### **Lỗi Compilation**
```bash
# Đảm bảo sử dụng JDK 17+
java -version

# Nếu javac không tìm thấy
export JAVA_HOME=/path/to/jdk17
```

### **Lỗi "No suitable constructor found"**
- Đảm bảo tất cả class có constructor đầy đủ
- Check serialVersionUID

### **File data.ser bị hỏng**
- Xóa file `data.ser`
- Khởi động lại ứng dụng (sẽ tạo file mới)

---

