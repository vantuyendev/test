package models;

import java.io.Serializable;

/**
 * Lớp DiemMonHoc đại diện cho điểm số của một môn học
 * Chứa các loại điểm: TX (Thường Xuyên), GK (Giữa Kỳ), CK (Cuối Kỳ)
 * 
 * Nguyên lý OOP: Đóng gói, Kiểm soát ràng buộc dữ liệu
 */
public class DiemMonHoc implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String tenMonHoc;
    private java.util.List<Double> diemThuongXuyen;
    private double diemGiuaKy;
    private double diemCuoiKy;
    
    // Hằng số kiểm soát
    private static final double DIEM_MIN = 0.0;
    private static final double DIEM_MAX = 10.0;
    
    /**
     * Constructor - Khởi tạo môn học mới
     */
    public DiemMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
        this.diemThuongXuyen = new java.util.ArrayList<>();
        this.diemGiuaKy = 0.0;
        this.diemCuoiKy = 0.0;
    }
    
    /**
     * Getter - Tên môn học
     */
    public String getTenMonHoc() {
        return tenMonHoc;
    }
    
    /**
     * Setter - Tên môn học
     */
    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }
    
    /**
     * Thêm điểm Thường Xuyên
     * Kiểm tra ràng buộc: 0 <= điểm <= 10
     */
    public boolean themDiemThuongXuyen(double diem) {
        if (diem >= DIEM_MIN && diem <= DIEM_MAX) {
            diemThuongXuyen.add(diem);
            return true;
        }
        System.err.println("Lỗi: Điểm Thường Xuyên phải nằm trong khoảng [0, 10]");
        return false;
    }
    
    /**
     * Getter - Danh sách điểm Thường Xuyên
     */
    public java.util.List<Double> getDiemThuongXuyen() {
        return new java.util.ArrayList<>(diemThuongXuyen);
    }
    
    /**
     * Setter - Điểm Giữa Kỳ
     * Kiểm tra ràng buộc: 0 <= điểm <= 10
     */
    public boolean setDiemGiuaKy(double diem) {
        if (diem >= DIEM_MIN && diem <= DIEM_MAX) {
            this.diemGiuaKy = diem;
            return true;
        }
        System.err.println("Lỗi: Điểm Giữa Kỳ phải nằm trong khoảng [0, 10]");
        return false;
    }
    
    /**
     * Getter - Điểm Giữa Kỳ
     */
    public double getDiemGiuaKy() {
        return diemGiuaKy;
    }
    
    /**
     * Setter - Điểm Cuối Kỳ
     * Kiểm tra ràng buộc: 0 <= điểm <= 10
     */
    public boolean setDiemCuoiKy(double diem) {
        if (diem >= DIEM_MIN && diem <= DIEM_MAX) {
            this.diemCuoiKy = diem;
            return true;
        }
        System.err.println("Lỗi: Điểm Cuối Kỳ phải nằm trong khoảng [0, 10]");
        return false;
    }
    
    /**
     * Getter - Điểm Cuối Kỳ
     */
    public double getDiemCuoiKy() {
        return diemCuoiKy;
    }
    
    /**
     * Tính DTB Môn (Điểm Trung Bình Môn)
     * Công thức: (Σ(DiemTX × 1) + (DiemGK × 2) + (DiemCK × 3)) / (Số_lần_TX + 2 + 3)
     * 
     * Trọng số: TX = 1, GK = 2, CK = 3
     * 
     * Nguyên lý OOP: Đóng gói - phương thức này nằm bên trong lớp và chỉ lớp này biết cách tính
     */
    public double tinhDTBMon() {
        // Nếu chưa có đủ thông tin, trả về 0
        if (diemThuongXuyen.isEmpty() && diemGiuaKy == 0 && diemCuoiKy == 0) {
            return 0.0;
        }
        
        // Tính tổng điểm Thường Xuyên
        double tongDiemTX = 0;
        for (Double diem : diemThuongXuyen) {
            tongDiemTX += diem;
        }
        
        // Tính tổng trọng số
        int soLanTX = diemThuongXuyen.isEmpty() ? 1 : diemThuongXuyen.size();
        double tongTrongSo = soLanTX * 1 + 2 + 3; // TX:1, GK:2, CK:3
        
        // Tính DTB Môn
        double dtbMon = (tongDiemTX * 1 + diemGiuaKy * 2 + diemCuoiKy * 3) / tongTrongSo;
        
        // Làm tròn đến 2 chữ số thập phân
        return Math.round(dtbMon * 100.0) / 100.0;
    }
    
    /**
     * Kiểm tra xem môn học có đủ điểm không
     */
    public boolean coDuDiem() {
        return !diemThuongXuyen.isEmpty() && diemGiuaKy > 0 && diemCuoiKy > 0;
    }
    
    /**
     * Hiển thị thông tin môn học
     */
    @Override
    public String toString() {
        return String.format("%s (DTB: %.2f)", tenMonHoc, tinhDTBMon());
    }
}
