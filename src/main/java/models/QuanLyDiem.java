package models;

import java.io.Serializable;
import java.util.*;

/**
 * Lớp QuanLyDiem - Lớp quản lý chính của hệ thống
 * Chịu trách nhiệm quản lý danh sách học sinh, tính toán điểm, thống kê
 * 
 * Nguyên lý OOP: Aggregation, Delegation, Single Responsibility Principle
 */
public class QuanLyDiem implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<HocSinh> danhSachHocSinh;
    private IXepLoai xepLoaiStrategy;
    
    /**
     * Constructor - Khởi tạo Quản Lý Điểm
     */
    public QuanLyDiem() {
        this.danhSachHocSinh = new ArrayList<>();
        this.xepLoaiStrategy = new XepLoaiQuyCheChuan();
    }
    
    /**
     * Thêm học sinh vào danh sách
     * Kiểm tra mã HS không trùng lặp
     */
    public boolean themHocSinh(HocSinh hs) {
        // Kiểm tra mã HS trùng
        for (HocSinh h : danhSachHocSinh) {
            if (h.getMaHocSinh().equals(hs.getMaHocSinh())) {
                System.err.println("Lỗi: Mã học sinh '" + hs.getMaHocSinh() + "' đã tồn tại!");
                return false;
            }
        }
        
        danhSachHocSinh.add(hs);
        return true;
    }
    
    /**
     * Xóa học sinh theo mã
     */
    public boolean xoaHocSinh(String maHS) {
        for (HocSinh hs : danhSachHocSinh) {
            if (hs.getMaHocSinh().equals(maHS)) {
                danhSachHocSinh.remove(hs);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Tìm kiếm học sinh theo mã
     */
    public HocSinh timHocSinh(String maHS) {
        for (HocSinh hs : danhSachHocSinh) {
            if (hs.getMaHocSinh().equals(maHS)) {
                return hs;
            }
        }
        return null;
    }
    
    /**
     * Getter - Danh sách học sinh
     */
    public List<HocSinh> getDanhSachHocSinh() {
        return new ArrayList<>(danhSachHocSinh);
    }
    
    /**
     * Lấy số lượng học sinh
     */
    public int getSoHocSinh() {
        return danhSachHocSinh.size();
    }
    
    /**
     * Tính Điểm Trung Bình Chung (DTBC) cho một học sinh
     * 
     * DTBC = (Σ(DTB Môn)) / Số môn
     * 
     * Nguyên lý OOP: Delegation - uỷ thác trách nhiệm tính DTB Môn cho DiemMonHoc
     */
    public double tinhDTBChung(String maHS) {
        HocSinh hs = timHocSinh(maHS);
        if (hs == null || hs.getDanhSachDiem().isEmpty()) {
            return 0.0;
        }
        
        double tongDTB = 0;
        for (DiemMonHoc diem : hs.getDanhSachDiem()) {
            tongDTB += diem.tinhDTBMon();
        }
        
        double dtbc = tongDTB / hs.getDanhSachDiem().size();
        return Math.round(dtbc * 100.0) / 100.0;
    }
    
    /**
     * Xếp loại học sinh
     */
    public String xepLoaiHocSinh(String maHS) {
        double dtbc = tinhDTBChung(maHS);
        return xepLoaiStrategy.xepLoai(dtbc);
    }
    
    /**
     * Thay đổi chiến lược xếp loại
     */
    public void setXepLoaiStrategy(IXepLoai strategy) {
        this.xepLoaiStrategy = strategy;
    }
    
    /**
     * Thống kê theo xếp loại
     */
    public Map<String, Integer> thongKeXepLoai() {
        Map<String, Integer> thongKe = new LinkedHashMap<>();
        thongKe.put("Xuất Sắc", 0);
        thongKe.put("Giỏi", 0);
        thongKe.put("Khá", 0);
        thongKe.put("Trung Bình", 0);
        thongKe.put("Yếu", 0);
        
        for (HocSinh hs : danhSachHocSinh) {
            if (hs.getDanhSachDiem().isEmpty()) continue;
            
            String xepLoai = xepLoaiHocSinh(hs.getMaHocSinh());
            thongKe.put(xepLoai, thongKe.get(xepLoai) + 1);
        }
        
        return thongKe;
    }
    
    /**
     * Lấy Top N học sinh có DTBC cao nhất
     */
    public List<HocSinh> getTopHocSinh(int n) {
        List<HocSinh> danhSachSapXep = new ArrayList<>(danhSachHocSinh);
        
        // Sắp xếp theo DTBC giảm dần
        danhSachSapXep.sort((a, b) -> {
            double dtbcA = tinhDTBChung(a.getMaHocSinh());
            double dtbcB = tinhDTBChung(b.getMaHocSinh());
            return Double.compare(dtbcB, dtbcA);
        });
        
        // Lấy top N
        return danhSachSapXep.size() > n ? 
               danhSachSapXep.subList(0, n) : 
               danhSachSapXep;
    }
    
    /**
     * Thống kê theo môn học
     */
    public Map<String, Map<String, Double>> thongKeTheoMon() {
        Map<String, Map<String, Double>> result = new HashMap<>();
        Map<String, List<Double>> monHocDiem = new HashMap<>();
        
        // Thu thập tất cả điểm theo môn
        for (HocSinh hs : danhSachHocSinh) {
            for (DiemMonHoc diem : hs.getDanhSachDiem()) {
                String tenMon = diem.getTenMonHoc();
                double dtbMon = diem.tinhDTBMon();
                
                monHocDiem.putIfAbsent(tenMon, new ArrayList<>());
                monHocDiem.get(tenMon).add(dtbMon);
            }
        }
        
        // Tính thống kê
        for (String tenMon : monHocDiem.keySet()) {
            List<Double> diem = monHocDiem.get(tenMon);
            Map<String, Double> stats = new HashMap<>();
            
            stats.put("count", (double) diem.size());
            stats.put("max", Collections.max(diem));
            stats.put("min", Collections.min(diem));
            stats.put("average", diem.stream().mapToDouble(Double::doubleValue).average().orElse(0));
            
            result.put(tenMon, stats);
        }
        
        return result;
    }
    
    /**
     * Hiển thị bảng điểm của một học sinh
     */
    public String hienThiBangDiem(String maHS) {
        HocSinh hs = timHocSinh(maHS);
        if (hs == null) {
            return "Không tìm thấy học sinh!";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== BẢNG ĐIỂM CHI TIẾT ===\n");
        sb.append(hs.hienThiThongTinCoBan()).append("\n\n");
        
        if (hs.getDanhSachDiem().isEmpty()) {
            sb.append("Chưa có điểm môn nào.\n");
        } else {
            sb.append("Môn Học | DTB Môn\n");
            sb.append("-".repeat(30)).append("\n");
            
            for (DiemMonHoc diem : hs.getDanhSachDiem()) {
                sb.append(String.format("%-20s | %6.2f\n", 
                         diem.getTenMonHoc(), 
                         diem.tinhDTBMon()));
            }
            
            sb.append("-".repeat(30)).append("\n");
            double dtbc = tinhDTBChung(maHS);
            String xepLoai = xepLoaiHocSinh(maHS);
            sb.append(String.format("DTBC: %.2f | Xếp loại: %s\n", dtbc, xepLoai));
        }
        
        return sb.toString();
    }
    
    /**
     * Hiển thị danh sách tất cả học sinh
     */
    public String hienThiDanhSach() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== DANH SÁCH HỌC SINH ===\n");
        sb.append("Tổng số: ").append(danhSachHocSinh.size()).append(" học sinh\n\n");
        
        if (danhSachHocSinh.isEmpty()) {
            sb.append("Chưa có học sinh.\n");
        } else {
            sb.append("Mã HS | Tên | Lớp | Số Môn | DTBC | Xếp Loại\n");
            sb.append("-".repeat(70)).append("\n");
            
            for (HocSinh hs : danhSachHocSinh) {
                double dtbc = tinhDTBChung(hs.getMaHocSinh());
                String xepLoai = hs.getDanhSachDiem().isEmpty() ? "N/A" : xepLoaiHocSinh(hs.getMaHocSinh());
                
                sb.append(String.format("%s | %s | %s | %d | %.2f | %s\n",
                         hs.getMaHocSinh(),
                         hs.getHoTen(),
                         hs.getLopHoc(),
                         hs.getDanhSachDiem().size(),
                         dtbc,
                         xepLoai));
            }
        }
        
        return sb.toString();
    }
}
