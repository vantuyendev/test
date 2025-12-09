package com.quanlydiem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Entity
@Table(name = "hoc_sinh")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HocSinh implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String maHS;

    @Column(nullable = false)
    private String tenHS;

    @Column(nullable = false)
    private String lop;

    @Column(nullable = false)
    private Integer namSinh;

    @Column
    private Double diemTrungBinh;

    @Column
    private String xepLoai;

    // Constructor without ID (for creation)
    public HocSinh(String maHS, String tenHS, String lop, Integer namSinh) {
        this.maHS = maHS;
        this.tenHS = tenHS;
        this.lop = lop;
        this.namSinh = namSinh;
        this.diemTrungBinh = 0.0;
        this.xepLoai = "Chưa xếp loại";
    }

    @Override
    public String toString() {
        return String.format("HocSinh [maHS=%s, tenHS=%s, lop=%s, namSinh=%d, DTBC=%.2f, xepLoai=%s]",
                maHS, tenHS, lop, namSinh, diemTrungBinh != null ? diemTrungBinh : 0, xepLoai);
    }
}
