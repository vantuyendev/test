package com.quanlydiem.services;

import com.quanlydiem.models.HocSinh;
import com.quanlydiem.models.DiemMonHoc;
import com.quanlydiem.models.XepLoaiQuyCheChuan;
import com.quanlydiem.repositories.HocSinhRepository;
import com.quanlydiem.repositories.DiemMonHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HocSinhService {

    @Autowired
    private HocSinhRepository hocSinhRepository;

    @Autowired
    private DiemMonHocRepository diemMonHocRepository;

    @Autowired
    private XepLoaiQuyCheChuan xepLoaiQuyCheChuan;

    // Get all students
    public List<HocSinh> getAllHocSinh() {
        return hocSinhRepository.findAll();
    }

    // Get student by ID
    public Optional<HocSinh> getHocSinhById(Long id) {
        return hocSinhRepository.findById(id);
    }

    // Get student by MaHS
    public Optional<HocSinh> getHocSinhByMaHS(String maHS) {
        return hocSinhRepository.findByMaHS(maHS);
    }

    // Create new student
    public HocSinh createHocSinh(HocSinh hocSinh) {
        // Check if MaHS already exists
        if (hocSinhRepository.findByMaHS(hocSinh.getMaHS()).isPresent()) {
            throw new IllegalArgumentException("Mã học sinh " + hocSinh.getMaHS() + " đã tồn tại!");
        }
        return hocSinhRepository.save(hocSinh);
    }

    // Update student
    public HocSinh updateHocSinh(Long id, HocSinh hocSinhDetails) {
        Optional<HocSinh> optionalHocSinh = hocSinhRepository.findById(id);
        if (optionalHocSinh.isPresent()) {
            HocSinh hocSinh = optionalHocSinh.get();
            if (hocSinhDetails.getTenHS() != null) hocSinh.setTenHS(hocSinhDetails.getTenHS());
            if (hocSinhDetails.getLop() != null) hocSinh.setLop(hocSinhDetails.getLop());
            if (hocSinhDetails.getNamSinh() != null) hocSinh.setNamSinh(hocSinhDetails.getNamSinh());
            return hocSinhRepository.save(hocSinh);
        }
        throw new IllegalArgumentException("Không tìm thấy học sinh với ID: " + id);
    }

    // Delete student
    public void deleteHocSinh(Long id) {
        // Delete all scores first
        List<DiemMonHoc> diem = diemMonHocRepository.findByHocSinhId(id);
        diemMonHocRepository.deleteAll(diem);
        
        // Delete student
        hocSinhRepository.deleteById(id);
    }

    // Calculate GPA and update classification
    public HocSinh calculateGPA(Long hocSinhId) {
        Optional<HocSinh> optionalHocSinh = hocSinhRepository.findById(hocSinhId);
        if (!optionalHocSinh.isPresent()) {
            throw new IllegalArgumentException("Không tìm thấy học sinh với ID: " + hocSinhId);
        }

        HocSinh hocSinh = optionalHocSinh.get();
        List<DiemMonHoc> diem = diemMonHocRepository.findByHocSinhId(hocSinhId);

        if (diem.isEmpty()) {
            hocSinh.setDiemTrungBinh(0.0);
            hocSinh.setXepLoai("Chưa xếp loại");
        } else {
            double avgGPA = diem.stream()
                    .mapToDouble(d -> d.getDiemMonHoc() != null ? d.getDiemMonHoc() : 0)
                    .average()
                    .orElse(0.0);
            
            hocSinh.setDiemTrungBinh(Math.round(avgGPA * 100.0) / 100.0);
            hocSinh.setXepLoai(xepLoaiQuyCheChuan.xepLoai(hocSinh.getDiemTrungBinh()));
        }

        return hocSinhRepository.save(hocSinh);
    }

    // Get top 5 students
    public List<HocSinh> getTop5Students() {
        return hocSinhRepository.findAll()
                .stream()
                .sorted((a, b) -> Double.compare(
                        b.getDiemTrungBinh() != null ? b.getDiemTrungBinh() : 0,
                        a.getDiemTrungBinh() != null ? a.getDiemTrungBinh() : 0
                ))
                .limit(5)
                .collect(Collectors.toList());
    }

    // Get students by class
    public List<HocSinh> getHocSinhByLop(String lop) {
        return hocSinhRepository.findByLop(lop);
    }

    // Get statistics
    public Object getThongKe() {
        List<HocSinh> allStudents = hocSinhRepository.findAll();
        
        long xatSac = allStudents.stream().filter(h -> "Xuất Sắc".equals(h.getXepLoai())).count();
        long gioi = allStudents.stream().filter(h -> "Giỏi".equals(h.getXepLoai())).count();
        long kha = allStudents.stream().filter(h -> "Khá".equals(h.getXepLoai())).count();
        long trungBinh = allStudents.stream().filter(h -> "Trung Bình".equals(h.getXepLoai())).count();
        long yeu = allStudents.stream().filter(h -> "Yếu".equals(h.getXepLoai())).count();
        
        return new Object() {
            public long getTotalStudents() { return allStudents.size(); }
            public long getXatSac() { return xatSac; }
            public long getGioi() { return gioi; }
            public long getKha() { return kha; }
            public long getTrungBinh() { return trungBinh; }
            public long getYeu() { return yeu; }
        };
    }
}
