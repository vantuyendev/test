package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp HocSinh đại diện cho học sinh trong hệ thống
 * Kế thừa từ ConNguoi để sử dụng lại code hoTen, namSinh
 * 
 * Nguyên lý OOP: Kế thừa (Inheritance), Composition
 */
public class HocSinh extends ConNguoi implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String maHocSinh;
    private String lopHoc;
    private List<DiemMonHoc> danhSachDiem;
    
    /**
     * Constructor - Khởi tạo học sinh mới
     */
    public HocSinh(String maHocSinh, String hoTen, String lopHoc, int namSinh) {
        super(hoTen, namSinh);
        this.maHocSinh = maHocSinh;
        this.lopHoc = lopHoc;
        this.danhSachDiem = new ArrayList<>();
    }
    
    /**
     * Getter - Mã học sinh
     */
    public String getMaHocSinh() {
        return maHocSinh;
    }
    
    /**
     * Setter - Mã học sinh
     */
    public void setMaHocSinh(String maHocSinh) {
        this.maHocSinh = maHocSinh;
    }
    
    /**
     * Getter - Lớp học
     */
    public String getLopHoc() {
        return lopHoc;
    }
    
    /**
     * Setter - Lớp học
     */
    public void setLopHoc(String lopHoc) {
        this.lopHoc = lopHoc;
    }
    
    /**
     * Getter - Danh sách điểm (Composition)
     * Nguyên lý: Trả về bản sao để bảo vệ dữ liệu nội bộ
     */
    public List<DiemMonHoc> getDanhSachDiem() {
        return new ArrayList<>(danhSachDiem);
    }
    
    /**
     * Thêm môn học với điểm
     */
    public boolean themDiem(DiemMonHoc diem) {
        if (diem != null) {
            // Kiểm tra nếu môn học đã tồn tại
            for (DiemMonHoc d : danhSachDiem) {
                if (d.getTenMonHoc().equals(diem.getTenMonHoc())) {
                    System.err.println("Môn học '" + diem.getTenMonHoc() + "' đã tồn tại");
                    return false;
                }
            }
            danhSachDiem.add(diem);
            return true;
        }
        return false;
    }
    
    /**
     * Xóa một môn học
     */
    public boolean xoaDiem(String tenMonHoc) {
        for (DiemMonHoc diem : danhSachDiem) {
            if (diem.getTenMonHoc().equals(tenMonHoc)) {
                danhSachDiem.remove(diem);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Tìm kiếm môn học theo tên
     */
    public DiemMonHoc timMonHoc(String tenMonHoc) {
        for (DiemMonHoc diem : danhSachDiem) {
            if (diem.getTenMonHoc().equals(tenMonHoc)) {
                return diem;
            }
        }
        return null;
    }
    
    /**
     * Lấy số lượng môn học
     */
    public int getSoMon() {
        return danhSachDiem.size();
    }
    
    /**
     * Implementation phương thức trừu tượng từ ConNguoi
     */
    @Override
    public String hienThiThongTinCoBan() {
        return String.format("Mã HS: %s | Tên: %s | Lớp: %s | Tuổi: %d",
                maHocSinh, hoTen, lopHoc, tinhTuoi());
    }
    
    /**
     * Hiển thị thông tin chi tiết học sinh
     */
    public String hienThiThongTinChiTiet() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== THÔNG TIN HỌC SINH ===\n");
        sb.append(hienThiThongTinCoBan()).append("\n");
        sb.append("Năm sinh: ").append(namSinh).append("\n");
        sb.append("Số môn học: ").append(getSoMon()).append("\n");
        
        if (!danhSachDiem.isEmpty()) {
            sb.append("\n--- DANH SÁCH ĐIỂM ---\n");
            for (DiemMonHoc diem : danhSachDiem) {
                sb.append("• ").append(diem.toString()).append("\n");
            }
        }
        
        return sb.toString();
    }
    
    /**
     * Hiển thị thông tin dạng String
     */
    @Override
    public String toString() {
        return String.format("%s - %s (%s)", maHocSinh, hoTen, lopHoc);
    }
}
