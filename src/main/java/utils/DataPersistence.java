package utils;

import models.QuanLyDiem;
import java.io.*;

/**
 * Lớp DataPersistence - Quản lý lưu trữ và tải dữ liệu từ file
 * 
 * Sử dụng Java Object Serialization để lưu trữ bền vững
 * 
 * Nguyên lý: Encapsulation - tất cả I/O được quản lý ở một nơi
 */
public class DataPersistence {
    
    private static final String DATA_FILE = "data.ser";
    
    /**
     * Lưu dữ liệu vào file nhị phân
     */
    public static boolean luuDuLieu(QuanLyDiem quanLyDiem) {
        try (FileOutputStream fos = new FileOutputStream(DATA_FILE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            
            oos.writeObject(quanLyDiem);
            System.out.println("✓ Đã lưu dữ liệu vào file: " + DATA_FILE);
            return true;
            
        } catch (IOException e) {
            System.err.println("✗ Lỗi khi lưu dữ liệu: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Tải dữ liệu từ file nhị phân
     */
    public static QuanLyDiem taiDuLieu() {
        File file = new File(DATA_FILE);
        
        // Nếu file không tồn tại, trả về QuanLyDiem trống
        if (!file.exists()) {
            System.out.println("ℹ Không tìm thấy file dữ liệu. Khởi tạo mới.");
            return new QuanLyDiem();
        }
        
        try (FileInputStream fis = new FileInputStream(DATA_FILE);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            QuanLyDiem quanLyDiem = (QuanLyDiem) ois.readObject();
            System.out.println("✓ Đã tải dữ liệu từ file: " + DATA_FILE);
            return quanLyDiem;
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("✗ Lỗi khi tải dữ liệu: " + e.getMessage());
            return new QuanLyDiem();
        }
    }
    
    /**
     * Xóa file dữ liệu
     */
    public static boolean xoaFile() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }
    
    /**
     * Kiểm tra xem file dữ liệu có tồn tại không
     */
    public static boolean fileTonTai() {
        return new File(DATA_FILE).exists();
    }
}
