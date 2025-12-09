package models;

/**
 * Interface IXepLoai - định nghĩa hành vi xếp loại
 * 
 * Nguyên lý OOP: Đa hình (Polymorphism)
 * Cho phép thay đổi cách xếp loại mà không ảnh hưởng đến các lớp khác
 */
public interface IXepLoai {
    /**
     * Xếp loại học sinh dựa trên DTBC
     * @param dtbc Điểm Trung Bình Chung
     * @return Xếp loại học lực
     */
    String xepLoai(double dtbc);
}
