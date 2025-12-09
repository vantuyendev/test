package com.quanlydiem.controllers;

import com.quanlydiem.models.HocSinh;
import com.quanlydiem.services.HocSinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hoc-sinh")
@CrossOrigin(origins = "*")
public class HocSinhController {

    @Autowired
    private HocSinhService hocSinhService;

    // Get all students
    @GetMapping
    public ResponseEntity<List<HocSinh>> getAllHocSinh() {
        List<HocSinh> hocSinhs = hocSinhService.getAllHocSinh();
        return ResponseEntity.ok(hocSinhs);
    }

    // Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<HocSinh> getHocSinhById(@PathVariable Long id) {
        Optional<HocSinh> hocSinh = hocSinhService.getHocSinhById(id);
        return hocSinh.isPresent() ? ResponseEntity.ok(hocSinh.get()) : ResponseEntity.notFound().build();
    }

    // Create new student
    @PostMapping
    public ResponseEntity<?> createHocSinh(@RequestBody HocSinh hocSinh) {
        try {
            HocSinh created = hocSinhService.createHocSinh(hocSinh);
            return ResponseEntity.status(201).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    // Update student
    @PutMapping("/{id}")
    public ResponseEntity<?> updateHocSinh(@PathVariable Long id, @RequestBody HocSinh hocSinhDetails) {
        try {
            HocSinh updated = hocSinhService.updateHocSinh(id, hocSinhDetails);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    // Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHocSinh(@PathVariable Long id) {
        try {
            hocSinhService.deleteHocSinh(id);
            return ResponseEntity.ok(new MessageResponse("Xóa học sinh thành công"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    // Get top 5 students
    @GetMapping("/top-5/students")
    public ResponseEntity<List<HocSinh>> getTop5Students() {
        List<HocSinh> top5 = hocSinhService.getTop5Students();
        return ResponseEntity.ok(top5);
    }

    // Get statistics
    @GetMapping("/stats/summary")
    public ResponseEntity<?> getStatistics() {
        try {
            Object stats = hocSinhService.getThongKe();
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
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
