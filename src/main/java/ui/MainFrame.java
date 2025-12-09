package ui;

import models.*;
import utils.DataPersistence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * MainFrame - Cửa sổ chính của ứng dụng
 * 
 * Quản lý giao diện người dùng (GUI) và điều hướng giữa các panel khác nhau
 */
public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private QuanLyDiem quanLyDiem;
    
    // Các panel chức năng
    private DashboardPanel dashboardPanel;
    private QuanLyHocSinhPanel quanLyHocSinhPanel;
    private QuanLyDiemPanel quanLyDiemPanel;
    private BaoCaoPanel baoCaoPanel;
    
    /**
     * Constructor - Khởi tạo cửa sổ chính
     */
    public MainFrame() {
        setTitle("Hệ Thống Quản Lý Điểm Học Tập THCS - Trường Đại Học Phenikaa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Load dữ liệu
        quanLyDiem = DataPersistence.taiDuLieu();
        
        // Tạo TabbedPane
        tabbedPane = new JTabbedPane();
        
        // Khởi tạo các panel
        dashboardPanel = new DashboardPanel(quanLyDiem, this);
        quanLyHocSinhPanel = new QuanLyHocSinhPanel(quanLyDiem, this);
        quanLyDiemPanel = new QuanLyDiemPanel(quanLyDiem, this);
        baoCaoPanel = new BaoCaoPanel(quanLyDiem, this);
        
        // Thêm các tab
        tabbedPane.addTab("📊 Bảng Điều Khiển", dashboardPanel);
        tabbedPane.addTab("👤 Quản Lý Học Sinh", quanLyHocSinhPanel);
        tabbedPane.addTab("📝 Quản Lý Điểm", quanLyDiemPanel);
        tabbedPane.addTab("📈 Báo Cáo", baoCaoPanel);
        
        add(tabbedPane, BorderLayout.CENTER);
        
        // Thêm StatusBar
        JPanel statusPanel = createStatusPanel();
        add(statusPanel, BorderLayout.SOUTH);
        
        // Thêm Exit Listener
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveAndExit();
            }
        });
        
        setVisible(true);
    }
    
    /**
     * Tạo panel trạng thái
     */
    private JPanel createStatusPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setBackground(new Color(240, 240, 240));
        
        JLabel statusLabel = new JLabel("Tổng học sinh: " + quanLyDiem.getSoHocSinh());
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        JButton saveBtn = new JButton("💾 Lưu Dữ Liệu");
        saveBtn.addActionListener(e -> {
            if (DataPersistence.luuDuLieu(quanLyDiem)) {
                JOptionPane.showMessageDialog(this, "Lưu dữ liệu thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi khi lưu dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        panel.add(statusLabel, BorderLayout.WEST);
        panel.add(saveBtn, BorderLayout.EAST);
        
        return panel;
    }
    
    /**
     * Refresh dữ liệu trên tất cả panel
     */
    public void refreshAllPanels() {
        dashboardPanel.refresh();
        quanLyHocSinhPanel.refresh();
        quanLyDiemPanel.refresh();
        baoCaoPanel.refresh();
    }
    
    /**
     * Lưu dữ liệu và thoát
     */
    private void saveAndExit() {
        int result = JOptionPane.showConfirmDialog(this, 
            "Bạn có muốn lưu dữ liệu trước khi thoát?", 
            "Xác nhận", 
            JOptionPane.YES_NO_CANCEL_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            DataPersistence.luuDuLieu(quanLyDiem);
            System.exit(0);
        } else if (result == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }
    
    /**
     * Getter - QuanLyDiem
     */
    public QuanLyDiem getQuanLyDiem() {
        return quanLyDiem;
    }
    
    /**
     * Main method - Điểm vào chương trình
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
