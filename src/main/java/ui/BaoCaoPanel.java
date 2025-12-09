package ui;

import models.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

/**
 * BaoCaoPanel - Panel báo cáo và thống kê
 */
public class BaoCaoPanel extends JPanel {
    private QuanLyDiem quanLyDiem;
    private MainFrame mainFrame;
    
    private JTable statsTable;
    private JTable rankingTable;
    private JTable subjectTable;
    private DefaultTableModel statsModel;
    private DefaultTableModel rankingModel;
    private DefaultTableModel subjectModel;
    
    public BaoCaoPanel(QuanLyDiem quanLyDiem, MainFrame mainFrame) {
        this.quanLyDiem = quanLyDiem;
        this.mainFrame = mainFrame;
        
        setLayout(new GridLayout(3, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(245, 245, 245));
        
        // Stats
        add(createStatsPanel());
        
        // Rankings
        add(createRankingPanel());
        
        // Subject stats
        add(createSubjectPanel());
        
        refresh();
    }
    
    private JPanel createStatsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        
        String[] columns = {"Xếp Loại", "Số Học Sinh", "Tỷ Lệ (%)"};
        statsModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        statsTable = new JTable(statsModel);
        statsTable.setFont(new Font("Arial", Font.PLAIN, 11));
        statsTable.setRowHeight(25);
        
        JScrollPane scrollPane = new JScrollPane(statsTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Thống Kê Xếp Loại"));
        
        JButton refreshBtn = new JButton("Làm Mới");
        refreshBtn.addActionListener(e -> refresh());
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(refreshBtn);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createRankingPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        
        String[] columns = {"Xếp Hạng", "Mã HS", "Họ Tên", "DTBC", "Xếp Loại"};
        rankingModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        rankingTable = new JTable(rankingModel);
        rankingTable.setFont(new Font("Arial", Font.PLAIN, 11));
        rankingTable.setRowHeight(25);
        
        JScrollPane scrollPane = new JScrollPane(rankingTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Xếp Hạng Học Sinh"));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createSubjectPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        
        String[] columns = {"Môn Học", "Số HS", "Max", "Min", "Trung Bình"};
        subjectModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        subjectTable = new JTable(subjectModel);
        subjectTable.setFont(new Font("Arial", Font.PLAIN, 11));
        subjectTable.setRowHeight(25);
        
        JScrollPane scrollPane = new JScrollPane(subjectTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Thống Kê Theo Môn Học"));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    public void refresh() {
        // Thống kê xếp loại
        updateStatsTable();
        
        // Xếp hạng
        updateRankingTable();
        
        // Thống kê môn
        updateSubjectTable();
    }
    
    private void updateStatsTable() {
        statsModel.setRowCount(0);
        
        Map<String, Integer> stats = quanLyDiem.thongKeXepLoai();
        int total = quanLyDiem.getSoHocSinh();
        
        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            double percent = total > 0 ? (double) entry.getValue() / total * 100 : 0;
            statsModel.addRow(new Object[]{
                entry.getKey(),
                entry.getValue(),
                String.format("%.2f%%", percent)
            });
        }
    }
    
    private void updateRankingTable() {
        rankingModel.setRowCount(0);
        
        var allStudents = quanLyDiem.getDanhSachHocSinh();
        var sorted = allStudents.stream()
            .sorted((a, b) -> Double.compare(
                quanLyDiem.tinhDTBChung(b.getMaHocSinh()),
                quanLyDiem.tinhDTBChung(a.getMaHocSinh())
            ))
            .toList();
        
        for (int i = 0; i < sorted.size(); i++) {
            HocSinh hs = sorted.get(i);
            double dtbc = quanLyDiem.tinhDTBChung(hs.getMaHocSinh());
            String xepLoai = hs.getDanhSachDiem().isEmpty() ? "N/A" : quanLyDiem.xepLoaiHocSinh(hs.getMaHocSinh());
            
            rankingModel.addRow(new Object[]{
                i + 1,
                hs.getMaHocSinh(),
                hs.getHoTen(),
                String.format("%.2f", dtbc),
                xepLoai
            });
        }
    }
    
    private void updateSubjectTable() {
        subjectModel.setRowCount(0);
        
        Map<String, Map<String, Double>> stats = quanLyDiem.thongKeTheoMon();
        
        for (Map.Entry<String, Map<String, Double>> entry : stats.entrySet()) {
            Map<String, Double> data = entry.getValue();
            subjectModel.addRow(new Object[]{
                entry.getKey(),
                data.get("count").intValue(),
                String.format("%.2f", data.get("max")),
                String.format("%.2f", data.get("min")),
                String.format("%.2f", data.get("average"))
            });
        }
    }
}
