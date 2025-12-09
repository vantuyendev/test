package models;

/**
 * Lớp XepLoaiQuyCheChuan - triển khai logic xếp loại chuẩn
 * 
 * Nguyên lý OOP: Đa hình - triển khai Interface IXepLoai
 */
public class XepLoaiQuyCheChuan implements IXepLoai {
    
    /**
     * Xếp loại học sinh theo quy chế chuẩn
     * 
     * Tiêu chí xếp loại:
     * - Xuất Sắc: DTBC >= 9.0
     * - Giỏi: DTBC >= 8.0
     * - Khá: DTBC >= 6.5
     * - Trung Bình: DTBC >= 5.0
     * - Yếu: DTBC < 5.0
     */
    @Override
    public String xepLoai(double dtbc) {
        if (dtbc >= 9.0) {
            return "Xuất Sắc";
        } else if (dtbc >= 8.0) {
            return "Giỏi";
        } else if (dtbc >= 6.5) {
            return "Khá";
        } else if (dtbc >= 5.0) {
            return "Trung Bình";
        } else {
            return "Yếu";
        }
    }
}
