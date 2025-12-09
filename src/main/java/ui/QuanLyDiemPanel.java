package ui;

import models.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * QuanLyDiemPanel - Panel quản lý điểm số của học sinh
 */
public class QuanLyDiemPanel extends JPanel {
    private QuanLyDiem quanLyDiem;
    private MainFrame mainFrame;
    
    private JComboBox<String> hsCombo;
    private JTextField monField;
    private JList<String> diemTXList;
    private DefaultListModel<String> diemTXModel;
    private JSpinner diemTXSpinner;
    private JSpinner diemGKSpinner;
    private JSpinner diemCKSpinner;
    private JTable diemTable;
    private DefaultTableModel tableModel;
    private List<Double> currentDiemTX = new ArrayList<>();
    
    public QuanLyDiemPanel(QuanLyDiem quanLyDiem, MainFrame mainFrame) {
        this.quanLyDiem = quanLyDiem;
        this.mainFrame = mainFrame;
        
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(245, 245, 245));
        
        // Select HS panel
        JPanel selectPanel = createSelectPanel();
        add(selectPanel, BorderLayout.NORTH);
        
        // Main content
        JPanel contentPanel = createContentPanel();
        add(contentPanel, BorderLayout.CENTER);
        
        refresh();
    }
    
    private JPanel createSelectPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Chọn Học Sinh"));
        
        panel.add(new JLabel("Học Sinh:"));
        hsCombo = new JComboBox<>();
        hsCombo.addActionListener(e -> updateScoresTable());
        panel.add(hsCombo);
        
        JButton refreshBtn = new JButton("Làm Mới");
        refreshBtn.addActionListener(e -> refresh());
        panel.add(refreshBtn);
        
        return panel;
    }
    
    private JPanel createContentPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setOpaque(false);
        
        // Left panel - Form
        panel.add(createFormPanel());
        
        // Right panel - Table
        panel.add(createTablePanel());
        
        return panel;
    }
    
    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Thêm/Sửa Điểm Môn"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Môn học
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        panel.add(new JLabel("Môn Học:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        monField = new JTextField();
        panel.add(monField, gbc);
        
        // Điểm Thường Xuyên
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        panel.add(new JLabel("Điểm TX:"), gbc);
        
        JPanel txPanel = new JPanel(new BorderLayout());
        diemTXSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 10.0, 0.5));
        JButton addTXBtn = new JButton("Thêm");
        addTXBtn.addActionListener(e -> themDiemTX());
        txPanel.add(diemTXSpinner, BorderLayout.CENTER);
        txPanel.add(addTXBtn, BorderLayout.EAST);
        
        gbc.gridx = 1; gbc.weightx = 0.7;
        panel.add(txPanel, gbc);
        
        // Danh sách TX
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.weighty = 0.3; gbc.fill = GridBagConstraints.BOTH;
        diemTXModel = new DefaultListModel<>();
        diemTXList = new JList<>(diemTXModel);
        JScrollPane txScroll = new JScrollPane(diemTXList);
        panel.add(txScroll, gbc);
        
        // Điểm GK
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        gbc.weighty = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(new JLabel("Điểm GK:"), gbc);
        gbc.gridx = 1;
        diemGKSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 10.0, 0.5));
        panel.add(diemGKSpinner, gbc);
        
        // Điểm CK
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Điểm CK:"), gbc);
        gbc.gridx = 1;
        diemCKSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 10.0, 0.5));
        panel.add(diemCKSpinner, gbc);
        
        // Buttons
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        JButton saveBtn = new JButton("Lưu Điểm");
        saveBtn.addActionListener(e -> luuDiem());
        
        JButton clearBtn = new JButton("Xóa Form");
        clearBtn.addActionListener(e -> clearForm());
        
        buttonPanel.add(saveBtn);
        buttonPanel.add(clearBtn);
        panel.add(buttonPanel, gbc);
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        
        String[] columns = {"Môn Học", "Điểm TX", "Điểm GK", "Điểm CK", "DTB Môn"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        diemTable = new JTable(tableModel);
        diemTable.setFont(new Font("Arial", Font.PLAIN, 11));
        diemTable.setRowHeight(25);
        
        JScrollPane scrollPane = new JScrollPane(diemTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Bảng Điểm Chi Tiết"));
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        
        JButton deleteBtn = new JButton("Xóa Môn");
        deleteBtn.addActionListener(e -> xoaMon());
        
        buttonPanel.add(deleteBtn);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void themDiemTX() {
        double diem = (double) diemTXSpinner.getValue();
        if (diem < 0 || diem > 10) {
            JOptionPane.showMessageDialog(this, "Điểm phải từ 0 đến 10!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        currentDiemTX.add(diem);
        diemTXModel.addElement(String.format("%.1f", diem));
        diemTXSpinner.setValue(0.0);
    }
    
    private void luuDiem() {
        String maHS = (String) hsCombo.getSelectedItem();
        if (maHS == null || maHS.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn học sinh!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String mon = monField.getText().trim();
        if (mon.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên môn học!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (currentDiemTX.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng thêm ít nhất một điểm thường xuyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        double gk = (double) diemGKSpinner.getValue();
        double ck = (double) diemCKSpinner.getValue();
        
        HocSinh hs = quanLyDiem.timHocSinh(maHS);
        if (hs != null) {
            DiemMonHoc diem = new DiemMonHoc(mon);
            for (double d : currentDiemTX) {
                diem.themDiemThuongXuyen(d);
            }
            diem.setDiemGiuaKy(gk);
            diem.setDiemCuoiKy(ck);
            
            // Xóa môn cũ nếu tồn tại
            hs.xoaDiem(mon);
            hs.themDiem(diem);
            
            JOptionPane.showMessageDialog(this, "Lưu điểm thành công!");
            clearForm();
            updateScoresTable();
            mainFrame.refreshAllPanels();
        }
    }
    
    private void xoaMon() {
        int row = diemTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn môn để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String maHS = (String) hsCombo.getSelectedItem();
        String mon = (String) tableModel.getValueAt(row, 0);
        
        HocSinh hs = quanLyDiem.timHocSinh(maHS);
        if (hs != null) {
            hs.xoaDiem(mon);
            updateScoresTable();
            mainFrame.refreshAllPanels();
            JOptionPane.showMessageDialog(this, "Xóa môn thành công!");
        }
    }
    
    private void clearForm() {
        monField.setText("");
        diemTXSpinner.setValue(0.0);
        diemGKSpinner.setValue(0.0);
        diemCKSpinner.setValue(0.0);
        currentDiemTX.clear();
        diemTXModel.clear();
    }
    
    private void updateScoresTable() {
        tableModel.setRowCount(0);
        currentDiemTX.clear();
        diemTXModel.clear();
        clearForm();
        
        String maHS = (String) hsCombo.getSelectedItem();
        if (maHS == null || maHS.isEmpty()) {
            return;
        }
        
        HocSinh hs = quanLyDiem.timHocSinh(maHS);
        if (hs != null) {
            for (DiemMonHoc diem : hs.getDanhSachDiem()) {
                String txStr = diem.getDiemThuongXuyen().stream()
                    .map(d -> String.format("%.1f", d))
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("");
                
                tableModel.addRow(new Object[]{
                    diem.getTenMonHoc(),
                    txStr,
                    String.format("%.1f", diem.getDiemGiuaKy()),
                    String.format("%.1f", diem.getDiemCuoiKy()),
                    String.format("%.2f", diem.tinhDTBMon())
                });
            }
        }
    }
    
    public void refresh() {
        hsCombo.removeAllItems();
        for (HocSinh hs : quanLyDiem.getDanhSachHocSinh()) {
            hsCombo.addItem(hs.getMaHocSinh() + " - " + hs.getHoTen());
        }
        clearForm();
        updateScoresTable();
    }
}
