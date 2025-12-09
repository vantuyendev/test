package com.quanlydiem.models;

import org.springframework.stereotype.Component;

@Component
public class XepLoaiQuyCheChuan implements IXepLoai {

    @Override
    public String xepLoai(Double diemTrungBinh) {
        if (diemTrungBinh == null || diemTrungBinh < 0) {
            return "Chưa xếp loại";
        }
        
        if (diemTrungBinh >= 9.0) {
            return "Xuất Sắc";
        } else if (diemTrungBinh >= 8.0) {
            return "Giỏi";
        } else if (diemTrungBinh >= 6.5) {
            return "Khá";
        } else if (diemTrungBinh >= 5.0) {
            return "Trung Bình";
        } else {
            return "Yếu";
        }
    }
}
