package com.quanlydiem.services;

import com.quanlydiem.models.DiemMonHoc;
import com.quanlydiem.models.HocSinh;
import com.quanlydiem.repositories.DiemMonHocRepository;
import com.quanlydiem.repositories.HocSinhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@Service
public class DiemMonHocService {

    @Autowired
    private DiemMonHocRepository diemMonHocRepository;

    @Autowired
    private HocSinhRepository hocSinhRepository;

    @Autowired
    private HocSinhService hocSinhService;

    // Get all scores
    public List<DiemMonHoc> getAllDiem() {
        return diemMonHocRepository.findAll();
    }

    // Get scores by student ID
    public List<DiemMonHoc> getDiemByHocSinh(Long hocSinhId) {
        return diemMonHocRepository.findByHocSinhId(hocSinhId);
    }

    // Add score
    public DiemMonHoc addDiem(Long hocSinhId, DiemMonHoc diem) {
        Optional<HocSinh> optionalHocSinh = hocSinhRepository.findById(hocSinhId);
        if (!optionalHocSinh.isPresent()) {
            throw new IllegalArgumentException("Không tìm thấy học sinh với ID: " + hocSinhId);
        }

        HocSinh hocSinh = optionalHocSinh.get();
        
        // Check if score already exists for this subject
        Optional<DiemMonHoc> existingDiem = diemMonHocRepository.findByHocSinhIdAndMonHoc(hocSinhId, diem.getMonHoc());
        if (existingDiem.isPresent()) {
            throw new IllegalArgumentException("Điểm " + diem.getMonHoc() + " đã tồn tại cho học sinh này!");
        }

        diem.setHocSinh(hocSinh);
        diem.tinhDiemMonHoc();
        DiemMonHoc saved = diemMonHocRepository.save(diem);
        
        // Recalculate GPA
        hocSinhService.calculateGPA(hocSinhId);
        
        return saved;
    }

    // Update score
    public DiemMonHoc updateDiem(Long diemId, DiemMonHoc diemDetails) {
        Optional<DiemMonHoc> optionalDiem = diemMonHocRepository.findById(diemId);
        if (!optionalDiem.isPresent()) {
            throw new IllegalArgumentException("Không tìm thấy điểm với ID: " + diemId);
        }

        DiemMonHoc diem = optionalDiem.get();
        if (diemDetails.getDiemTX() != null) diem.setDiemTX(diemDetails.getDiemTX());
        if (diemDetails.getDiemGK() != null) diem.setDiemGK(diemDetails.getDiemGK());
        if (diemDetails.getDiemCK() != null) diem.setDiemCK(diemDetails.getDiemCK());
        
        diem.tinhDiemMonHoc();
        DiemMonHoc saved = diemMonHocRepository.save(diem);
        
        // Recalculate GPA
        hocSinhService.calculateGPA(diem.getHocSinh().getId());
        
        return saved;
    }

    // Delete score
    public void deleteDiem(Long diemId) {
        Optional<DiemMonHoc> optionalDiem = diemMonHocRepository.findById(diemId);
        if (optionalDiem.isPresent()) {
            DiemMonHoc diem = optionalDiem.get();
            Long hocSinhId = diem.getHocSinh().getId();
            diemMonHocRepository.deleteById(diemId);
            // Recalculate GPA
            hocSinhService.calculateGPA(hocSinhId);
        }
    }

    // Get statistics by subject
    public Map<String, Object> getThongKeMonHoc() {
        List<String> monHocs = diemMonHocRepository.findAllDistinctMonHoc();
        Map<String, Object> result = new HashMap<>();

        for (String monHoc : monHocs) {
            Double avg = diemMonHocRepository.findAvgByMonHoc(monHoc);
            Double max = diemMonHocRepository.findMaxByMonHoc(monHoc);
            Double min = diemMonHocRepository.findMinByMonHoc(monHoc);

            Map<String, Object> stats = new HashMap<>();
            stats.put("average", avg != null ? Math.round(avg * 100.0) / 100.0 : 0);
            stats.put("max", max != null ? max : 0);
            stats.put("min", min != null ? min : 0);

            result.put(monHoc, stats);
        }

        return result;
    }
}
