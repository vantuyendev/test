package ui;

import models.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * DashboardPanel - Panel bảng điều khiển hiển thị thông tin tổng quan
 */
public class DashboardPanel extends JPanel {
    private QuanLyDiem quanLyDiem;
    private MainFrame mainFrame;
    
    private JLabel totalStudentsLabel;
    private JLabel excellentLabel;
    private JLabel goodLabel;
    private JLabel averageLabel;
    private JTable topStudentsTable;
    
    public DashboardPanel(QuanLyDiem quanLyDiem, MainFrame mainFrame) {
        this.quanLyDiem = quanLyDiem;
        this.mainFrame = mainFrame;
        
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(245, 245, 245));
        
        // Header
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);
        
        // Statistics
        JPanel statsPanel = createStatsPanel();
        add(statsPanel, BorderLayout.CENTER);
        
        // Top students
        JPanel topPanel = createTopStudentsPanel();
        add(topPanel, BorderLayout.SOUTH);
        
        refresh();
    }
    
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);
        
        JLabel titleLabel = new JLabel("BẢNG ĐIỀU KHIỂN HỆ THỐNG");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        panel.add(titleLabel);
        return panel;
    }
    
    private JPanel createStatsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 15, 15));
        panel.setOpaque(false);
        
        // Stats cards
        panel.add(createStatCard("Tổng Học Sinh", totalStudentsLabel = new JLabel("0"), new Color(52, 152, 219)));
        panel.add(createStatCard("Xuất Sắc", excellentLabel = new JLabel("0"), new Color(231, 76, 60)));
        panel.add(createStatCard("Giỏi", goodLabel = new JLabel("0"), new Color(243, 156, 18)));
        panel.add(createStatCard("Khá", averageLabel = new JLabel("0"), new Color(39, 174, 96)));
        
        return panel;
    }
    
    private JPanel createStatCard(String title, JLabel valueLabel, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(color, 2));
        card.setOpaque(true);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        
        valueLabel.setFont(new Font("Arial", Font.BOLD, 28));
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        valueLabel.setForeground(color);
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createTopStudentsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false);
        
        JLabel titleLabel = new JLabel("Top 5 Học Sinh Có DTBC Cao Nhất");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Table
        String[] columns = {"STT", "Mã HS", "Họ Tên", "Lớp", "DTBC", "Xếp Loại"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        topStudentsTable = new JTable(model);
        topStudentsTable.setFont(new Font("Arial", Font.PLAIN, 11));
        topStudentsTable.setRowHeight(25);
        
        JScrollPane scrollPane = new JScrollPane(topStudentsTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    public void refresh() {
        // Update stats
        totalStudentsLabel.setText(String.valueOf(quanLyDiem.getSoHocSinh()));
        
        var stats = quanLyDiem.thongKeXepLoai();
        excellentLabel.setText(String.valueOf(stats.getOrDefault("Xuất Sắc", 0)));
        goodLabel.setText(String.valueOf(stats.getOrDefault("Giỏi", 0)));
        averageLabel.setText(String.valueOf(stats.getOrDefault("Khá", 0)));
        
        // Update top students table
        DefaultTableModel model = (DefaultTableModel) topStudentsTable.getModel();
        model.setRowCount(0);
        
        List<HocSinh> topStudents = quanLyDiem.getTopHocSinh(5);
        for (int i = 0; i < topStudents.size(); i++) {
            HocSinh hs = topStudents.get(i);
            double dtbc = quanLyDiem.tinhDTBChung(hs.getMaHocSinh());
            String xepLoai = quanLyDiem.xepLoaiHocSinh(hs.getMaHocSinh());
            
            model.addRow(new Object[]{
                i + 1,
                hs.getMaHocSinh(),
                hs.getHoTen(),
                hs.getLopHoc(),
                String.format("%.2f", dtbc),
                xepLoai
            });
        }
    }
}
