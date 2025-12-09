# 🚀 Hướng Dẫn Deploy Spring Boot lên Railway

## **Các Bước Deploy**

### **1. Chuẩn Bị**
```bash
# Clone hoặc vào folder project
cd /workspaces/test/springboot-web

# Đảm bảo có build.gradle và Procfile
ls -la Procfile
ls -la build.gradle
```

### **2. Tạo Tài Khoản Railway**
1. Truy cập: https://railway.app
2. Click "Start New Project"
3. Chọn "Login with GitHub"
4. Authorize Railway

### **3. Deploy Project**

#### **Cách A: Từ GitHub (Khuyên Dùng)**

**Bước 1: Push code lên GitHub**
```bash
cd /workspaces/test
git add .
git commit -m "Add Spring Boot web application"
git push origin main
```

**Bước 2: Trên Railway Dashboard**
1. Click "New Project"
2. Chọn "Deploy from GitHub repo"
3. Tìm và chọn repository `test` của bạn
4. Railway sẽ tự động:
   - Detect Java project
   - Build Gradle
   - Deploy to Heroku/Railway server

**Bước 3: Chờ Deploy (2-5 phút)**
```
Building... → Deploying... → Live ✅
```

#### **Cách B: Từ CLI (Nhanh Hơn)**

**Bước 1: Cài Railway CLI**
```bash
npm install -g @railway/cli
# hoặc
brew install railway  # macOS
```

**Bước 2: Login**
```bash
railway login
```

**Bước 3: Deploy**
```bash
cd springboot-web
railway up
```

### **4. Nhận URL Công Khai**

Sau khi deploy xong, bạn sẽ nhận được URL:
```
https://quan-ly-diem-xxx.railway.app
```

### **5. Test Ứng Dụng**

Truy cập: `https://quan-ly-diem-xxx.railway.app`

Hoặc test API:
```bash
curl https://quan-ly-diem-xxx.railway.app/api/hoc-sinh
```

---

## **Cấu Hình Railway (Tùy Chọn)**

### **Thêm Custom Domain**
1. Railway Dashboard → Project Settings
2. Domains → Add Domain
3. Thêm domain tùy chỉnh (nếu có)

### **Cấu Hình Environment Variables**
```
SPRING_PROFILES_ACTIVE=prod
```

### **Xem Logs**
```bash
railway logs

# hoặc từ Dashboard: View Logs
```

---

## **Troubleshooting**

### **Lỗi 1: Build Failed**
```
❌ Gradle build failed
```

**Giải pháp:**
1. Kiểm tra build.gradle có đúng không
2. Test build cục bộ:
   ```bash
   ./gradlew build
   ```
3. Xem logs chi tiết: Railway → Logs

### **Lỗi 2: Port Issues**
```
❌ Port 8080 not available
```

**Giải pháp:**
- Railway tự động gán port
- Application.yml sẽ sử dụng `$PORT` env variable

### **Lỗi 3: Database Connection**
```
❌ H2 database not found
```

**Giải pháp:**
- H2 là embedded database, tự động tạo
- Kiểm tra logs xem database đã init chưa

### **Lỗi 4: Timeout Deploy**
```
❌ Deployment timeout
```

**Giải pháp:**
1. Gradle build có thể lâu (lần đầu 5-10 phút)
2. Chờ đủ thời gian
3. Check logs xem quá trình đến đâu

---

## **Giám Sát Ứng Dụng**

### **Xem Health Check**
```bash
curl https://quan-ly-diem-xxx.railway.app/actuator/health
```

### **Xem Logs Real-time**
```bash
railway logs -f
```

### **Restart Ứng Dụng**
```bash
railway down
railway up
```

---

## **Chi Phí Railway**

| Plan | CPU | RAM | Storage | Giá |
|------|-----|-----|---------|-----|
| Free | 256MB | 256MB | Tạm thời | $0 |
| Hobby | 512MB | 512MB | 10GB | $5/mo |
| Pro | 2GB | 2GB | 100GB | $20/mo |

**Nhận xét:** Free tier đủ cho dự án học tập!

---

## **Cách Cập Nhật (Update Deploy)**

### **Cách 1: Push GitHub (Tự Động)**
```bash
git add .
git commit -m "Update features"
git push origin main
# Railway sẽ tự động rebuild & deploy
```

### **Cách 2: Manual Deploy**
```bash
cd springboot-web
railway up
```

---

## **Useful Railway Commands**

```bash
# Xem project info
railway status

# Cấu hình project
railway open

# Xem metrics
railway metrics

# Delete project
railway delete
```

---

## **Link Ứng Dụng Của Bạn**

Sau deploy, thay `xxx` bằng ID của bạn:

```
🌐 https://quan-ly-diem-xxx.railway.app
📚 Frontend: https://quan-ly-diem-xxx.railway.app
📊 API: https://quan-ly-diem-xxx.railway.app/api/hoc-sinh
```

---

## **Support & Docs**

- Railway Docs: https://docs.railway.app
- Spring Boot: https://spring.io/projects/spring-boot
- GitHub: https://github.com

---

**Chúc bạn deploy thành công!** 🎉
