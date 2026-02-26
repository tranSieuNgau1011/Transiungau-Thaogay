import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private JButton btnTrangChu, btnSanPham, btnKhachHang, btnNhanVien, btnDonHang, btnKho, btnKhuyenMai;
    private JPanel Main;


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
        Main = new JPanel();
        Main.setBackground(new Color(0xF8F7FF));
        add(Main, BorderLayout.CENTER);

        // Sau khi tạo các button
        btnTrangChu.addActionListener(e -> showTrangChu());
        btnSanPham.addActionListener(e -> showSanPham());
        btnKhachHang.addActionListener(e -> showKhachHang());
        btnNhanVien.addActionListener(e -> showNhanVien());
        btnDonHang.addActionListener(e -> showDonHang());
        btnKho.addActionListener(e -> showKho());
        btnKhuyenMai.addActionListener(e -> showKhuyenMai());
    }

    // Hàm tạo style cho mấy cái nút 
    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                //bóng button
                float shadowOffset = 3.75f;
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

    private void showTrangChu() {
        Main.removeAll();
        Main.setLayout(new BorderLayout());

        JPanel pnl = new JPanel();
        pnl.setBackground(Color.BLACK);
        Main.add(pnl);

        Main.revalidate();
        Main.repaint();
    }

    private void showSanPham() {
        Main.removeAll();
        Main.setLayout(new BorderLayout());

        JPanel pnl = new JPanel();
        pnl.setBackground(Color.BLUE);
        Main.add(pnl);

        Main.revalidate();
        Main.repaint();
    }

    private void showKhachHang() {
        Main.removeAll();
        Main.setLayout(new BorderLayout());

        JPanel pnl = new JPanel();
        pnl.setBackground(Color.CYAN);
        Main.add(pnl);

        Main.revalidate();
        Main.repaint();
    }

    private void showNhanVien() {
        Main.removeAll();
        Main.setLayout(new BorderLayout());

        JPanel pnl = new JPanel();
        pnl.setBackground(Color.GRAY);
        Main.add(pnl);

        Main.revalidate();
        Main.repaint();
    }

    private void showDonHang() {
        Main.removeAll();
        Main.setLayout(new BorderLayout());

        JPanel pnl = new JPanel();
        pnl.setBackground(Color.GREEN);
        Main.add(pnl);

        Main.revalidate();
        Main.repaint();
    }

    private void showKho() {
        Main.removeAll();
        Main.setLayout(new BorderLayout());

        JPanel pnl = new JPanel();
        pnl.setBackground(Color.MAGENTA);
        Main.add(pnl);

        Main.revalidate();
        Main.repaint();
    }

    private void showKhuyenMai() {
        Main.removeAll();
        Main.setLayout(new BorderLayout());

        JPanel pnl = new JPanel();
        pnl.setBackground(Color.ORANGE);
        Main.add(pnl);

        Main.revalidate();
        Main.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI GUI = new GUI();
            GUI.setVisible(true);
        });
    }
}
