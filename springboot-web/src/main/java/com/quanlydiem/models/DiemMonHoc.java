package com.quanlydiem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;

@Entity
@Table(name = "diem_mon_hoc", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"hoc_sinh_id", "mon_hoc"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiemMonHoc implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoc_sinh_id", nullable = false)
    @JsonBackReference
    private HocSinh hocSinh;

    @Column(nullable = false)
    private String monHoc;

    @Column(nullable = false, columnDefinition = "VARCHAR(500)")
    private String diemTX; // Comma-separated scores

    @Column(nullable = false)
    private Double diemGK;

    @Column(nullable = false)
    private Double diemCK;

    @Column
    private Double diemMonHoc; // Calculated average

    // Constructor
    public DiemMonHoc(String monHoc, String diemTX, Double diemGK, Double diemCK) {
        this.monHoc = monHoc;
        this.diemTX = diemTX;
        this.diemGK = diemGK;
        this.diemCK = diemCK;
        this.diemMonHoc = tinhDiemMonHoc();
    }

    // Calculate subject grade
    public Double tinhDiemMonHoc() {
        try {
            String[] scores = diemTX.split(",");
            double sumTX = 0;
            for (String score : scores) {
                sumTX += Double.parseDouble(score.trim());
            }
            double avgTX = sumTX / scores.length;
            
            double result = (avgTX * 1 + diemGK * 2 + diemCK * 3) / (scores.length + 5);
            this.diemMonHoc = Math.round(result * 100.0) / 100.0;
            return this.diemMonHoc;
        } catch (Exception e) {
            return 0.0;
        }
    }

    @Override
    public String toString() {
        return String.format("DiemMonHoc [monHoc=%s, TX=%s, GK=%.1f, CK=%.1f, DTB_Mon=%.2f]",
                monHoc, diemTX, diemGK, diemCK, diemMonHoc != null ? diemMonHoc : 0);
    }
}
