package com.quanlydiem.repositories;

import com.quanlydiem.models.HocSinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface HocSinhRepository extends JpaRepository<HocSinh, Long> {
    Optional<HocSinh> findByMaHS(String maHS);
    
    List<HocSinh> findByLop(String lop);
    
    @Query("SELECT h FROM HocSinh h ORDER BY h.diemTrungBinh DESC LIMIT 5")
    List<HocSinh> findTop5ByDiemTrungBinh();
    
    @Query("SELECT COUNT(h) FROM HocSinh h WHERE h.xepLoai = ?1")
    Long countByXepLoai(String xepLoai);
}
