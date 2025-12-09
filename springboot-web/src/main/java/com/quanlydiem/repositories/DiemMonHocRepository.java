package com.quanlydiem.repositories;

import com.quanlydiem.models.DiemMonHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiemMonHocRepository extends JpaRepository<DiemMonHoc, Long> {
    List<DiemMonHoc> findByHocSinhId(Long hocSinhId);
    
    Optional<DiemMonHoc> findByHocSinhIdAndMonHoc(Long hocSinhId, String monHoc);
    
    @Query("SELECT DISTINCT d.monHoc FROM DiemMonHoc d ORDER BY d.monHoc")
    List<String> findAllDistinctMonHoc();
    
    @Query("SELECT AVG(d.diemMonHoc) FROM DiemMonHoc d WHERE d.monHoc = ?1")
    Double findAvgByMonHoc(String monHoc);
    
    @Query("SELECT MAX(d.diemMonHoc) FROM DiemMonHoc d WHERE d.monHoc = ?1")
    Double findMaxByMonHoc(String monHoc);
    
    @Query("SELECT MIN(d.diemMonHoc) FROM DiemMonHoc d WHERE d.monHoc = ?1")
    Double findMinByMonHoc(String monHoc);
}
