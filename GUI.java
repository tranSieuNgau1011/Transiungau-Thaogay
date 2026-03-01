import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import java.awt.*;

public class GUI extends JFrame {
    private JButton btnTrangChu, btnSanPham, btnKhachHang, btnNhanVien, btnDonHang, btnKho, btnKhuyenMai;
    private JPanel Main;
    private CardLayout cardLayout;

    private static final String TRANG_CHU  = "TRANG_CHU";
    private static final String SAN_PHAM   = "SAN_PHAM";
    private static final String KHACH_HANG = "KHACH_HANG";
    private static final String NHAN_VIEN  = "NHAN_VIEN";
    private static final String DON_HANG   = "DON_HANG";
    private static final String KHO        = "KHO";
    private static final String KHUYEN_MAI = "KHUYEN_MAI";


    public GUI() {
        setTitle("Siu Thị 36");
        setSize(1440, 1024);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //cái thánh đen ở trên đầu
        JPanel Header = new JPanel(new BorderLayout());
        Header.setBackground(new Color(0x2F2C35));
        Header.setPreferredSize(new Dimension(1440, 60));

        //Tạo panel cho ava và tên sốp
        JPanel HeaderLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        HeaderLeft.setBackground(new Color(0x2F2C35));

        JLabel ava = new JLabel("~Khu vực để avata~");

        JLabel tenShop = new JLabel("Tên Sốp");
        tenShop.setForeground(Color.WHITE);
        //có chơi font chữ thì để đây, nhưng mà chưa biết font nào đẹp nên tạm thời để mặc định

        HeaderLeft.add(ava);
        HeaderLeft.add(tenShop);

        //Tạo panel cho sddt
        JLabel sdt = new JLabel("SDT liên hệ: 0345435108");
        sdt.setForeground(Color.WHITE);
        sdt.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        //font chữ luông

        Header.add(HeaderLeft, BorderLayout.WEST);
        Header.add(sdt, BorderLayout.EAST);

        add(Header, BorderLayout.NORTH);

        //thanh bên trái
        JPanel Left = new JPanel();
        Left.setLayout(new BoxLayout(Left, BoxLayout.Y_AXIS));
        Left.setPreferredSize(new Dimension(180, 700));
        Left.setBackground(new Color(0xD1C4E9));
        Left.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK,1),
            BorderFactory.createEmptyBorder(20,10,20,10)
        ));
        
        btnTrangChu = createStyledButton("Trang chủ");
        btnSanPham = createStyledButton("Sản phẩm");       
        btnKhachHang = createStyledButton("Khách hàng");
        btnNhanVien = createStyledButton("Nhân viên");
        btnDonHang = createStyledButton("Đơn hàng");
        btnKho = createStyledButton("Kho");    
        btnKhuyenMai = createStyledButton("Khuyến mãi");

        
        Left.add(btnTrangChu);
        Left.add(Box.createVerticalStrut(12));
        Left.add(btnSanPham);
        Left.add(Box.createVerticalStrut(12));
        Left.add(btnKhachHang);
        Left.add(Box.createVerticalStrut(12));
        Left.add(btnNhanVien);
        Left.add(Box.createVerticalStrut(12));
        Left.add(btnDonHang);
        Left.add(Box.createVerticalStrut(12));
        Left.add(btnKho);
        Left.add(Box.createVerticalStrut(12));
        Left.add(btnKhuyenMai);
        Left.add(Box.createVerticalGlue());

        add(Left, BorderLayout.WEST);

        //bảng nd chính
        cardLayout   = new CardLayout();
        Main = new JPanel(cardLayout);

        Main.add(createPanelTrangChu(),  TRANG_CHU);
        Main.add(createPanelSanPham(),   SAN_PHAM);
        Main.add(createPanelKhachHang(), KHACH_HANG);
        Main.add(createPanelNhanVien(),  NHAN_VIEN);
        Main.add(createPanelDonHang(),   DON_HANG);
        Main.add(createPanelKho(),       KHO);
        Main.add(createPanelKhuyenMai(), KHUYEN_MAI);

        add(Main, BorderLayout.CENTER);
    
        // Sau khi tạo các button
        btnTrangChu.addActionListener(e -> cardLayout.show(Main, TRANG_CHU));
        btnSanPham.addActionListener(e -> cardLayout.show(Main, SAN_PHAM));
        btnKhachHang.addActionListener(e -> cardLayout.show(Main, KHACH_HANG));
        btnNhanVien.addActionListener(e -> cardLayout.show(Main, NHAN_VIEN));
        btnDonHang.addActionListener(e -> cardLayout.show(Main, DON_HANG));
        btnKho.addActionListener(e -> cardLayout.show(Main, KHO));
        btnKhuyenMai.addActionListener(e -> cardLayout.show(Main, KHUYEN_MAI));
    }

     private JPanel createPanelTrangChu() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0xF8F7FF));

        JLabel title = new JLabel("Trang Chủ", SwingConstants.CENTER);
        title.setFont(new Font("Playfair Display", Font.BOLD, 32));

        panel.add(title, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPanelSanPham() {
        JPanel panel = new JPanel(new BorderLayout());
       

        JPanel top= new JPanel();
        top.setPreferredSize(new Dimension(1174,94));
        top.setBackground(new Color(0xF8F7FF));
        top.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        top.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK,1),
            BorderFactory.createEmptyBorder(20,10,20,10)
        ));

         String[] boLoc = {
                "Tất cả",
                "Còn hàng",
                "Hết hàng",
                "Có khuyến mãi",
                "Cận date"
        };

        JComboBox cbLoc = new JComboBox<>(boLoc);
        cbLoc.setPreferredSize(new Dimension(254,42));
        cbLoc.setFont(new Font("Arial", Font.PLAIN, 24));
        cbLoc.setBackground(new Color(0xD9D9D9));
       
        top.add(cbLoc);


        JPanel timkiem= new JPanel();
        timkiem.setLayout(new BorderLayout());
        timkiem.setPreferredSize(new Dimension(229,42));
        timkiem.setBackground(new Color(0xD9D9D9));

        JTextField tim= new JTextField();
        tim.setToolTipText("Nhập tên sản phẩm...");
        tim.setFont(new Font("Arial", Font.PLAIN,24));
        timkiem.add(tim,BorderLayout.CENTER);

        JButton nuttim= new JButton("🔍");
        nuttim.setBorderPainted(false);
        nuttim.setContentAreaFilled(false);
        nuttim.setFocusPainted(false);

        timkiem.add(nuttim,BorderLayout.EAST);
        top.add(timkiem);

        JButton them= new JButton("+ Thêm sản phẩm");
        them.setFocusPainted(false);
        them.setBackground(new Color(0xD9D9D9));
        them.setPreferredSize(new Dimension(254,42));
        them.setFont(new Font("Arial",Font.BOLD,24));

        top.add(them);

        JPanel content= new JPanel();
        content.setBackground(new Color(0xF8F7FF));
        content.setLayout(new BorderLayout());

        String[] tencot = {"Mã SP","Ảnh", "Tên sản phẩm", "Giá", "Số lượng", "Kho", "Date", "Khuyến mãi","Thao tác"};
        Object[][] dulieu = {
            {"MD01","", "Nước F trái K",  "25.000đ",  7,  "Còn hàng",  "26/10/2026",  "-2.000đ",""},
            {"MD02","", "Thịt mèo cháy", "200.000đ",  1,  "Hết hàng",  "01/03/2026", "-100.000đ",""},
            {"MD03","", "Sản phẩm C",     "50.000đ",  5,  "Còn hàng",  "15/05/2026",  "-5.000đ",""},
        };

        JTable bang= new JTable(dulieu,tencot);
        bang.setRowHeight(76);
        bang.setFont(new Font("Arial", Font.PLAIN, 20));
        bang.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        bang.getColumnModel().getColumn(0).setPreferredWidth(50);
        bang.getColumnModel().getColumn(4).setPreferredWidth(50);
        bang.getColumnModel().getColumn(2).setPreferredWidth(150);
        bang.getTableHeader().setPreferredSize(new Dimension(1166,76 ));
        bang.getTableHeader().setBackground(new Color(0xAF9FCB));
       bang.getColumnModel().getColumn(8)
    .setCellRenderer(new TableCellRenderer() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        JButton sua = new JButton("Sửa");
        JButton xoa = new JButton("Xóa");

        {
            panel.add(sua);
            panel.add(xoa);
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value,
                boolean isSelected, boolean hasFocus,
                int row, int column) {

            return panel;
        }
    });
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
                public Component getTableCellRendererComponent(
                        JTable table, Object value, boolean isSelected,
                        boolean hasFocus, int row, int column) {

                    Component c = super.getTableCellRendererComponent(
                            table, value, isSelected, hasFocus, row, column);

                    if (!isSelected) {
                        if (row % 2 == 0)
                            c.setBackground(Color.WHITE);
                        else
                            c.setBackground(new Color(0xD3E8F3));
                    }

                    setHorizontalAlignment(SwingConstants.CENTER);
                    return c;
                }
            };

            for (int i = 0; i < bang.getColumnCount(); i++) {
                bang.getColumnModel().getColumn(i).setCellRenderer(renderer);
            }



        JScrollPane cuon = new JScrollPane(bang);
        content.add(cuon, BorderLayout.CENTER);



        panel.add(top,BorderLayout.NORTH);
        panel.add(content, BorderLayout.CENTER);
        return panel;
       
    }

    private JPanel createPanelKhachHang() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0xF8F7FF));

        JLabel title = new JLabel("Quản Lý Khách Hàng", SwingConstants.CENTER);
        title.setFont(new Font("Playfair Display", Font.BOLD, 32));

        panel.add(title, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPanelNhanVien() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0xF8F7FF));

        JLabel title = new JLabel("Quản Lý Nhân Viên", SwingConstants.CENTER);
        title.setFont(new Font("Playfair Display", Font.BOLD, 32));

        panel.add(title, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPanelDonHang() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0xF8F7FF));

        JLabel title = new JLabel("Quản Lý Đơn Hàng", SwingConstants.CENTER);
        title.setFont(new Font("Playfair Display", Font.BOLD, 32));

        panel.add(title, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPanelKho() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0xF8F7FF));

        JLabel title = new JLabel("Quản Lý Kho", SwingConstants.CENTER);
        title.setFont(new Font("Playfair Display", Font.BOLD, 32));

        panel.add(title, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPanelKhuyenMai() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0xF8F7FF));

        JLabel title = new JLabel("Quản Lý Khuyến Mãi", SwingConstants.CENTER);
        title.setFont(new Font("Playfair Display", Font.BOLD, 32));

        panel.add(title, BorderLayout.CENTER);
        return panel;
    }


    // Hàm tạo style cho mấy cái nút 
    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                //bóng button
                int shadowOffset = 3;
                for (int i = shadowOffset; i > 0; i--) {
                    int alpha = (int)(50.0 * (shadowOffset - i) / shadowOffset); 
                    g2.setColor(new Color(0, 0, 0, alpha));
                    g2.fillRoundRect(0, i, getWidth(), getHeight()-i, 40, 40);
                }


            
                //nền button
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth()-shadowOffset, getHeight()-shadowOffset, 40, 40);
            
                g2.dispose();
                super.paintComponent(g);
            }
        };

        btn.setBackground(new Color(0xF8F7FF));
        btn.setFont(new Font("Playfair Display", Font.BOLD, 20));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(204, 45));
        btn.setMaximumSize(new Dimension(204, 45));
        btn.setMinimumSize(new Dimension(204, 45));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //hover
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(0x88729B));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(0xF8F7FF));
            }
        });

        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI GUI = new GUI();
            GUI.setVisible(true);
        });
    }
}
