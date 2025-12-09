package com.quanlydiem.controllers;

import com.quanlydiem.models.DiemMonHoc;
import com.quanlydiem.services.DiemMonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/diem")
@CrossOrigin(origins = "*")
public class DiemMonHocController {

    @Autowired
    private DiemMonHocService diemMonHocService;

    // Get all scores
    @GetMapping
    public ResponseEntity<List<DiemMonHoc>> getAllDiem() {
        List<DiemMonHoc> diem = diemMonHocService.getAllDiem();
        return ResponseEntity.ok(diem);
    }

    // Get scores by student ID
    @GetMapping("/hoc-sinh/{hocSinhId}")
    public ResponseEntity<List<DiemMonHoc>> getDiemByHocSinh(@PathVariable Long hocSinhId) {
        List<DiemMonHoc> diem = diemMonHocService.getDiemByHocSinh(hocSinhId);
        return ResponseEntity.ok(diem);
    }

    // Add score
    @PostMapping("/hoc-sinh/{hocSinhId}")
    public ResponseEntity<?> addDiem(@PathVariable Long hocSinhId, @RequestBody DiemMonHoc diem) {
        try {
            DiemMonHoc created = diemMonHocService.addDiem(hocSinhId, diem);
            return ResponseEntity.status(201).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    // Update score
    @PutMapping("/{diemId}")
    public ResponseEntity<?> updateDiem(@PathVariable Long diemId, @RequestBody DiemMonHoc diemDetails) {
        try {
            DiemMonHoc updated = diemMonHocService.updateDiem(diemId, diemDetails);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    // Delete score
    @DeleteMapping("/{diemId}")
    public ResponseEntity<?> deleteDiem(@PathVariable Long diemId) {
        try {
            diemMonHocService.deleteDiem(diemId);
            return ResponseEntity.ok(new MessageResponse("Xóa điểm thành công"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    // Get statistics by subject
    @GetMapping("/stats/subjects")
    public ResponseEntity<Map<String, Object>> getStatisticsBySubject() {
        Map<String, Object> stats = diemMonHocService.getThongKeMonHoc();
        return ResponseEntity.ok(stats);
    }

    // Helper classes
    public static class ErrorResponse {
        public String error;
        
        public ErrorResponse(String error) {
            this.error = error;
        }
        
        public String getError() { return error; }
    }

    public static class MessageResponse {
        public String message;
        
        public MessageResponse(String message) {
            this.message = message;
        }
        
        public String getMessage() { return message; }
    }
}
