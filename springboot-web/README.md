# Spring Boot Web Application - Quản Lý Điểm Học Tập THCS

## 📱 Ứng Dụng Web (Spring Boot)

Đây là phiên bản **Web Application** của hệ thống quản lý điểm học tập THCS, xây dựng bằng **Spring Boot** với giao diện web hiện đại.

### ✨ Tính Năng

- ✅ Quản lý học sinh (Thêm, sửa, xóa)
- ✅ Quản lý điểm (Tính toán DTB tự động)
- ✅ Xếp loại học lực tự động
- ✅ Báo cáo & thống kê
- ✅ Giao diện web responsive
- ✅ Lưu trữ dữ liệu với H2 Database

### 🚀 Cách Chạy

#### **1. Yêu Cầu Hệ Thống**
- JDK 17 trở lên
- Gradle (nằm trong project)

#### **2. Chạy Cục Bộ**

```bash
# Linux/Mac
./run.sh

# Windows
gradlew bootRun

# Hoặc
gradle bootRun
```

Ứng dụng sẽ chạy tại: **http://localhost:8080**

#### **3. Build JAR**

```bash
./gradlew build

# Chạy JAR
java -jar build/libs/quan-ly-diem-1.0.0.jar
```

### 📦 Deploy lên Railway (Free)

#### **Bước 1: Tạo tài khoản Railway**
1. Truy cập: https://railway.app
2. Đăng nhập bằng GitHub
3. Tạo project mới

#### **Bước 2: Connect GitHub Repository**
1. Click "New Project"
2. Chọn "Deploy from GitHub repo"
3. Chọn repository của bạn
4. Railway sẽ tự động deploy

#### **Bước 3: Cấu Hình Variables (nếu cần)**
- PORT: 8080 (tự động)

#### **Bước 4: Chờ Deploy Hoàn Tất**
- Railway sẽ build và deploy tự động
- Bạn sẽ có link cố định: `https://quan-ly-diem-xxx.railway.app`

### 🌐 API Endpoints

#### **Students API**
```
GET    /api/hoc-sinh              # Lấy tất cả học sinh
GET    /api/hoc-sinh/{id}         # Lấy 1 học sinh
POST   /api/hoc-sinh              # Thêm học sinh
PUT    /api/hoc-sinh/{id}         # Cập nhật học sinh
DELETE /api/hoc-sinh/{id}         # Xóa học sinh
GET    /api/hoc-sinh/top-5/students  # Top 5 học sinh
GET    /api/hoc-sinh/stats/summary   # Thống kê tóm tắt
```

#### **Scores API**
```
GET    /api/diem                           # Lấy tất cả điểm
GET    /api/diem/hoc-sinh/{hocSinhId}     # Lấy điểm của 1 HS
POST   /api/diem/hoc-sinh/{hocSinhId}     # Thêm điểm
PUT    /api/diem/{diemId}                 # Cập nhật điểm
DELETE /api/diem/{diemId}                 # Xóa điểm
GET    /api/diem/stats/subjects           # Thống kê theo môn
```

### 📊 Cấu Trúc Project

```
springboot-web/
├── src/main/java/com/quanlydiem/
│   ├── QuanLyDiemApplication.java      # Main application
│   ├── models/                         # JPA Entities
│   │   ├── HocSinh.java
│   │   ├── DiemMonHoc.java
│   │   ├── IXepLoai.java
│   │   └── XepLoaiQuyCheChuan.java
│   ├── controllers/                    # REST Controllers
│   │   ├── HocSinhController.java
│   │   ├── DiemMonHocController.java
│   │   └── PageController.java
│   ├── services/                       # Business Logic
│   │   ├── HocSinhService.java
│   │   └── DiemMonHocService.java
│   └── repositories/                   # Data Access
│       ├── HocSinhRepository.java
│       └── DiemMonHocRepository.java
├── src/main/resources/
│   ├── application.yml                 # Configuration
│   ├── templates/
│   │   └── index.html                  # Frontend
│   └── static/
│       ├── css/style.css
│       └── js/app.js
├── build.gradle                        # Gradle Config
├── Procfile                            # Heroku Deploy
└── README.md
```

### 🛠️ Công Nghệ

- **Spring Boot 3.2.1** - Framework web
- **Spring Data JPA** - Database access
- **H2 Database** - Embedded database
- **Thymeleaf** - Template engine
- **Gradle** - Build tool
- **Modern HTML/CSS/JavaScript** - Frontend

### 📝 Ví Dụ API

#### **Thêm Học Sinh**
```bash
curl -X POST http://localhost:8080/api/hoc-sinh \
  -H "Content-Type: application/json" \
  -d '{
    "maHS": "HS001",
    "tenHS": "Nguyễn Văn A",
    "lop": "7A",
    "namSinh": 2010
  }'
```

#### **Thêm Điểm**
```bash
curl -X POST http://localhost:8080/api/diem/hoc-sinh/1 \
  -H "Content-Type: application/json" \
  -d '{
    "monHoc": "Toán",
    "diemTX": "8,8.5,9",
    "diemGK": 8.5,
    "diemCK": 9.0
  }'
```

### 🐛 Troubleshooting

**Lỗi: Port 8080 đã sử dụng**
```bash
# Thay đổi port trong application.yml
server:
  port: 8081
```

**Lỗi: Gradle command not found**
```bash
# Sử dụng gradlew thay vì gradle
./gradlew bootRun
```

### 📚 Tài Liệu Thêm

- [Spring Boot Official Docs](https://spring.io/projects/spring-boot)
- [Spring Data JPA Docs](https://spring.io/projects/spring-data-jpa)
- [Railway Deployment Guide](https://docs.railway.app)

### 🎯 Tiếp Theo

- [ ] Thêm authentication (Login/Signup)
- [ ] Export PDF reports
- [ ] Cải thiện UI/UX
- [ ] Thêm caching
- [ ] Viết unit tests
- [ ] Setup CI/CD

---

**Phát triển bởi**: Hoàng Mạnh Quân & Phạm Công Vinh  
**Ngày cập nhật**: December 9, 2024  
**Phiên bản**: 1.0.0
