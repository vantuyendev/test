# 🚀 Khởi Động Ứng Dụng Web

## Lệnh Chạy (1 dòng)

```bash
cd springboot-web && gradle bootRun
```

## Truy Cập Ứng Dụng

- **Ứng dụng chính:** http://localhost:8081
- **Database Console:** http://localhost:8081/h2-console

## Các Bước Chi Tiết

### 1️⃣ Mở Terminal

Mở terminal/command prompt trong thư mục dự án.

### 2️⃣ Chạy Lệnh

```bash
cd springboot-web && gradle bootRun
```

### 3️⃣ Đợi Khởi Động

Chờ khoảng 5-10 giây để ứng dụng khởi động hoàn toàn.
Khi thấy dòng này → Ứng dụng sẵn sàng:

```
Started QuanLyDiemApplication in X.XXX seconds
```

### 4️⃣ Mở Trình Duyệt

Truy cập: **http://localhost:8081**

### 5️⃣ Sử Dụng

- Quản lý học sinh (thêm, sửa, xóa)
- Nhập điểm
- Xem báo cáo

## ❌ Lỗi Thường Gặp

### Port 8081 Đã Được Sử Dụng
```
Port 8081 was already in use
```

**Giải pháp:** Dừng ứng dụng đang chạy hoặc đổi port trong `springboot-web/src/main/resources/application.yml`:
```yaml
server:
  port: 8082  # Thay 8081 bằng 8082
```

### Gradle Không Tìm Thấy

**Giải pháp:** Cài đặt Gradle:
```bash
# Linux/Mac
brew install gradle

# Hoặc dùng wrapper
cd springboot-web
./gradlew bootRun
```

### Java Không Được Cài Đặt

**Giải pháp:** Cài đặt Java 17 trở lên:
```bash
java -version  # Kiểm tra phiên bản
```

## ⏹️ Dừng Ứng Dụng

Nhấn `Ctrl + C` trong terminal để dừng.

---

**Thêm Tài Liệu:** Xem `README.md` để hiểu thêm về dự án.
