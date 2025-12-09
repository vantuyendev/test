package models;

import java.io.Serializable;

/**
 * Lớp cơ sở (Abstract) đại diện cho một người trong hệ thống
 * Chứa các thuộc tính chung: hoTen, namSinh
 * 
 * Nguyên lý OOP: Trừu tượng hóa, Kế thừa
 */
public abstract class ConNguoi implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected String hoTen;
    protected int namSinh;
    
    /**
     * Constructor với tham số
     */
    public ConNguoi(String hoTen, int namSinh) {
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }
    
    /**
     * Getter - Họ tên
     */
    public String getHoTen() {
        return hoTen;
    }
    
    /**
     * Setter - Họ tên
     */
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    
    /**
     * Getter - Năm sinh
     */
    public int getNamSinh() {
        return namSinh;
    }
    
    /**
     * Setter - Năm sinh
     */
    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }
    
    /**
     * Phương thức trừu tượng - mỗi lớp con phải implement
     */
    public abstract String hienThiThongTinCoBan();
    
    /**
     * Tính tuổi dựa trên năm sinh
     */
    public int tinhTuoi() {
        return 2024 - namSinh;
    }
}
