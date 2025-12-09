package ui;

import models.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

/**
 * QuanLyHocSinhPanel - Panel quản lý học sinh (CRUD operations)
 */
public class QuanLyHocSinhPanel extends JPanel {
    private QuanLyDiem quanLyDiem;
    private MainFrame mainFrame;
    
    private JTextField maHSField;
    private JTextField tenField;
    private JTextField lopField;
    private JSpinner namSinhSpinner;
    private JTable hsTable;
    private DefaultTableModel tableModel;
    
    public QuanLyHocSinhPanel(QuanLyDiem quanLyDiem, MainFrame mainFrame) {
        this.quanLyDiem = quanLyDiem;
        this.mainFrame = mainFrame;
        
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(245, 245, 245));
        
        // Form panel
        JPanel formPanel = createFormPanel();
        add(formPanel, BorderLayout.NORTH);
        
        // Table panel
        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);
        
        refresh();
    }
    
    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Thêm/Sửa Học Sinh"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Mã HS
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Mã HS:"), gbc);
        gbc.gridx = 1;
        maHSField = new JTextField(15);
        panel.add(maHSField, gbc);
        
        // Tên
        gbc.gridx = 2;
        panel.add(new JLabel("Tên:"), gbc);
        gbc.gridx = 3;
        tenField = new JTextField(15);
        panel.add(tenField, gbc);
        
        // Lớp
        gbc.gridx = 4;
        panel.add(new JLabel("Lớp:"), gbc);
        gbc.gridx = 5;
        lopField = new JTextField(10);
        panel.add(lopField, gbc);
        
        // Năm sinh
        gbc.gridx = 6;
        panel.add(new JLabel("Năm Sinh:"), gbc);
        gbc.gridx = 7;
        namSinhSpinner = new JSpinner(new SpinnerNumberModel(2009, 1990, 2015, 1));
        panel.add(namSinhSpinner, gbc);
        
        // Buttons
        gbc.gridx = 8;
        JButton addBtn = new JButton("Thêm");
        addBtn.addActionListener(e -> themHocSinh());
        panel.add(addBtn, gbc);
        
        gbc.gridx = 9;
        JButton clearBtn = new JButton("Xóa Form");
        clearBtn.addActionListener(e -> clearForm());
        panel.add(clearBtn, gbc);
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        
        // Table
        String[] columns = {"Mã HS", "Họ Tên", "Lớp", "Năm Sinh", "Số Môn", "Hành Động"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        hsTable = new JTable(tableModel);
        hsTable.setFont(new Font("Arial", Font.PLAIN, 11));
        hsTable.setRowHeight(25);
        hsTable.getColumnModel().getColumn(5).setMaxWidth(100);
        
        JScrollPane scrollPane = new JScrollPane(hsTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh Sách Học Sinh"));
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        
        JButton deleteBtn = new JButton("Xóa Học Sinh");
        deleteBtn.addActionListener(e -> xoaHocSinh());
        
        JButton refreshBtn = new JButton("Làm Mới");
        refreshBtn.addActionListener(e -> refresh());
        
        buttonPanel.add(deleteBtn);
        buttonPanel.add(refreshBtn);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void themHocSinh() {
        String maHS = maHSField.getText().trim();
        String ten = tenField.getText().trim();
        String lop = lopField.getText().trim();
        int namSinh = (int) namSinhSpinner.getValue();
        
        if (maHS.isEmpty() || ten.isEmpty() || lop.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        HocSinh hs = new HocSinh(maHS, ten, lop, namSinh);
        if (quanLyDiem.themHocSinh(hs)) {
            JOptionPane.showMessageDialog(this, "Thêm học sinh thành công!");
            clearForm();
            refresh();
            mainFrame.refreshAllPanels();
        } else {
            JOptionPane.showMessageDialog(this, "Mã học sinh đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void xoaHocSinh() {
        int row = hsTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn học sinh để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String maHS = (String) tableModel.getValueAt(row, 0);
        int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa học sinh này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            quanLyDiem.xoaHocSinh(maHS);
            refresh();
            mainFrame.refreshAllPanels();
            JOptionPane.showMessageDialog(this, "Xóa học sinh thành công!");
        }
    }
    
    private void clearForm() {
        maHSField.setText("");
        tenField.setText("");
        lopField.setText("");
        namSinhSpinner.setValue(2009);
    }
    
    public void refresh() {
        tableModel.setRowCount(0);
        
        for (HocSinh hs : quanLyDiem.getDanhSachHocSinh()) {
            tableModel.addRow(new Object[]{
                hs.getMaHocSinh(),
                hs.getHoTen(),
                hs.getLopHoc(),
                hs.getNamSinh(),
                hs.getSoMon(),
                "Xóa"
            });
        }
    }
}
