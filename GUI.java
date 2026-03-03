import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import java.awt.*;

public class GUI extends JFrame {
    private JButton btnTrangChu, btnSanPham, btnKhachHang, btnNhanVien, btnDonHang, btnKho, btnKhuyenMai;
    private JPanel Main;
    private CardLayout cardLayout;

    private int editingRow = -1;

    private JTextField tfTen;
    private JTextField tfMa;
    private JTextField tfSL;
    private JTextField tfGia;
    private JTextField tfDate;
    private JTextField tfKM;

    private static final String TRANG_CHU  = "TRANG_CHU";
    private static final String SAN_PHAM   = "SAN_PHAM";
    private static final String KHACH_HANG = "KHACH_HANG";
    private static final String NHAN_VIEN  = "NHAN_VIEN";
    private static final String DON_HANG   = "DON_HANG";
    private static final String KHO        = "KHO";
    private static final String KHUYEN_MAI = "KHUYEN_MAI";

    private static final String CARD_TABLE = "TABLE";
    private static final String CARD_THEM  = "THEM";


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

        CardLayout innerCard = new CardLayout();
        JPanel panel = new JPanel(innerCard);

        String[] columns = {
            "Mã SP", "Ảnh", "Tên sản phẩm", "Giá",
            "Số lượng", "Kho", "Date", "Khuyến mãi", "Thao tác"
        };
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return column == 8; }
        };
        model.addRow(new Object[]{ "MD01", "", "Nước F trái K", "25.000đ", 7, "Còn hàng", "26/10/2026", "-2.000đ", "" });
        model.addRow(new Object[]{ "MD02", "", "Thịt mèo cháy", "17.000đ", 7, "Còn hàng", "10/11/2026", "-7.000đ", "" });

        // ── CARD 1: Bảng danh sách ───────────────────────────────────
        JPanel tableCard = new JPanel(new BorderLayout());

        JTable bang = new JTable(model);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        bang.setRowSorter(sorter);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        top.setPreferredSize(new Dimension(1174, 94));
        top.setBackground(new Color(0xF8F7FF));
        top.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 1),
            BorderFactory.createEmptyBorder(20, 10, 20, 10)
        ));

        // index: 0=Tất cả, 1=Còn hàng, 2=Hết hàng, 3=Có khuyến mãi, 4=Cận date
        String[] boloc = { "Tất cả", "Còn hàng", "Hết hàng", "Có khuyến mãi", "Cận date" };
        JComboBox<String> cbLoc = new JComboBox<>(boloc);
        cbLoc.setPreferredSize(new Dimension(254, 42));
        cbLoc.setFont(new Font("Arial", Font.PLAIN, 24));
        cbLoc.setBackground(new Color(0xD9D9D9));

        JPanel timkiem = new JPanel(new BorderLayout());
        timkiem.setPreferredSize(new Dimension(229, 42));
        timkiem.setBackground(new Color(0xD9D9D9));
        JTextField tim = new JTextField();
        tim.setFont(new Font("Arial", Font.PLAIN, 24));
        timkiem.add(tim, BorderLayout.CENTER);

        JButton nuttim = new JButton("🔍");
        nuttim.setBorderPainted(false);
        nuttim.setContentAreaFilled(false);
        nuttim.setFocusPainted(false);
        nuttim.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nuttim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nuttim.setContentAreaFilled(true);
                nuttim.setBackground(new Color(0xC5B3E6));
                nuttim.setOpaque(true);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nuttim.setContentAreaFilled(false);
                nuttim.setOpaque(false);
            }
        });
        timkiem.add(nuttim, BorderLayout.EAST);

        JButton them = new JButton("+ Thêm sản phẩm");
        them.setFocusPainted(false);
        them.setBackground(new Color(0xD9D9D9));
        them.setPreferredSize(new Dimension(254, 42));
        them.setFont(new Font("Arial", Font.BOLD, 24));
        them.setCursor(new Cursor(Cursor.HAND_CURSOR));
        them.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { them.setBackground(new Color(0xC5B3E6)); }
            public void mouseExited(java.awt.event.MouseEvent evt)  { them.setBackground(new Color(0xD9D9D9)); }
        });
        them.addActionListener(e -> innerCard.show(panel, CARD_THEM));

        Runnable applyFilter = () -> {
            String tuKhoa = tim.getText().trim();
            // Dùng index thay vì so sánh string để tránh lỗi encoding tiếng Việt
            int idxLoc = cbLoc.getSelectedIndex();

            RowFilter<DefaultTableModel, Integer> filterLoc = switch (idxLoc) {
                case 1 -> RowFilter.regexFilter("(?i)Còn hàng", 5);   // Còn hàng
                case 2 -> RowFilter.regexFilter("(?i)Hết hàng", 5);   // Hết hàng
                case 3 -> RowFilter.regexFilter("^-\\d", 7);           // Có khuyến mãi
                case 4 -> new RowFilter<>() {                           // Cận date: hôm nay đến 7 ngày tới
                    @Override
                    public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                        String dateStr = entry.getStringValue(6).trim();
                        try {
                            java.time.LocalDate date = java.time.LocalDate.parse(
                                dateStr,
                                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")
                            );
                            java.time.LocalDate today = java.time.LocalDate.now();
                            return !date.isBefore(today) && date.isBefore(today.plusDays(7));
                        } catch (Exception ex) {
                            return false;
                        }
                    }
                };
                default -> null; // Tất cả
            };

            RowFilter<DefaultTableModel, Integer> filterTim = tuKhoa.isEmpty()
                ? null
                : RowFilter.regexFilter("(?i)" + tuKhoa, 2);

            if (filterLoc != null && filterTim != null)
                sorter.setRowFilter(RowFilter.andFilter(java.util.List.of(filterLoc, filterTim)));
            else if (filterLoc != null)
                sorter.setRowFilter(filterLoc);
            else if (filterTim != null)
                sorter.setRowFilter(filterTim);
            else
                sorter.setRowFilter(null);
        };

        cbLoc.addActionListener(e -> applyFilter.run());
        nuttim.addActionListener(e -> applyFilter.run());
        tim.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e)  { applyFilter.run(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e)  { applyFilter.run(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { applyFilter.run(); }
        });

        top.add(cbLoc);
        top.add(timkiem);
        top.add(them);

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(new Color(0xF8F7FF));

        bang.setRowHeight(76);
        bang.setFont(new Font("Arial", Font.PLAIN, 20));
        bang.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        bang.getTableHeader().setPreferredSize(new Dimension(1166, 76));
        bang.getTableHeader().setBackground(new Color(0xAF9FCB));
        bang.getColumnModel().getColumn(0).setPreferredWidth(50);
        bang.getColumnModel().getColumn(2).setPreferredWidth(150);
        bang.getColumnModel().getColumn(4).setPreferredWidth(50);

        DefaultTableCellRenderer altRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
                if (!isSelected)
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(0xD3E8F3));
                setHorizontalAlignment(SwingConstants.CENTER);
                return c;
            }
        };
        for (int i = 0; i < bang.getColumnCount(); i++)
            bang.getColumnModel().getColumn(i).setCellRenderer(altRenderer);

        bang.getColumnModel().getColumn(8).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                JPanel p = new JPanel(new GridLayout(2, 1, 5, 5));
                JButton sua = new JButton("Sửa");
                JButton xoa = new JButton("Xóa");
                sua.setFocusPainted(false); xoa.setFocusPainted(false);
                sua.setBackground(new Color(0x6677C8)); xoa.setBackground(new Color(0xB83434));
                sua.setForeground(Color.WHITE);         xoa.setForeground(Color.WHITE);
                p.add(sua); p.add(xoa);
                return p;
            }
        });

        bang.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            private final JPanel p    = new JPanel(new GridLayout(2, 1, 5, 5));
            private final JButton sua = new JButton("Sửa");
            private final JButton xoa = new JButton("Xóa");
            private int currentRow = -1;
            {
                sua.setFocusPainted(false); xoa.setFocusPainted(false);
                sua.setBackground(new Color(0x6677C8)); xoa.setBackground(new Color(0xB83434));
                sua.setForeground(Color.WHITE);         xoa.setForeground(Color.WHITE);
                p.add(sua); p.add(xoa);
                sua.addActionListener(e -> {
                fireEditingStopped();

                int modelRow = bang.convertRowIndexToModel(currentRow);
                editingRow = modelRow;

                tfMa.setText(model.getValueAt(modelRow, 0).toString());
                tfTen.setText(model.getValueAt(modelRow, 2).toString());
                tfGia.setText(model.getValueAt(modelRow, 3).toString());
                tfSL.setText(model.getValueAt(modelRow, 4).toString());
                tfDate.setText(model.getValueAt(modelRow, 6).toString());

                String km = model.getValueAt(modelRow, 7).toString();
                tfKM.setText(km.equals("-") ? "" : km);

                innerCard.show(panel, CARD_THEM);
            });
                xoa.addActionListener(e -> {
                    fireEditingStopped();
                    if (currentRow >= 0 && currentRow < model.getRowCount())
                        model.removeRow(currentRow);
                });
            }
            @Override
            public Component getTableCellEditorComponent(
                    JTable table, Object value, boolean isSelected, int row, int column) {
                currentRow = row;
                return p;
            }
            @Override public Object getCellEditorValue() { return ""; }
        });

        content.add(new JScrollPane(bang), BorderLayout.CENTER);
        tableCard.add(top,     BorderLayout.NORTH);
        tableCard.add(content, BorderLayout.CENTER);

        // ── CARD 2: Form thêm sản phẩm ──────────────────────────────
        JPanel themCard = new JPanel(new BorderLayout());
        themCard.setBackground(new Color(0xF0EFF8));

        JPanel formHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 18));
        formHeader.setBackground(new Color(0xF0EFF8));
        formHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0xCCCCCC)));

        JButton btnQuayLai = new JButton("← Quay lại danh sách");
        btnQuayLai.setFont(new Font("Arial", Font.BOLD, 22));
        btnQuayLai.setBackground(new Color(0x9B8EA8));
        btnQuayLai.setForeground(Color.WHITE);
        btnQuayLai.setFocusPainted(false);
        btnQuayLai.setBorderPainted(false);
        btnQuayLai.setPreferredSize(new Dimension(300, 48));
        btnQuayLai.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnQuayLai.addActionListener(e -> innerCard.show(panel, CARD_TABLE));
        formHeader.add(btnQuayLai);
        themCard.add(formHeader, BorderLayout.NORTH);

        JPanel form = new JPanel(null);
        form.setBackground(new Color(0xF0EFF8));
        Font labelFont = new Font("Arial", Font.BOLD, 20);

        JLabel lbTen = new JLabel("Tên sản phẩm"); lbTen.setFont(labelFont); lbTen.setBounds(60, 40, 220, 30);
        tfTen = makeField(); tfTen.setBounds(250, 35, 380, 42);

        JLabel lbMa = new JLabel("Mã SP"); lbMa.setFont(labelFont); lbMa.setBounds(60, 110, 220, 30);
        tfMa = makeField(); tfMa.setBounds(250, 105, 380, 42);

        JLabel lbSL = new JLabel("Số lượng"); lbSL.setFont(labelFont); lbSL.setBounds(60, 180, 220, 30);
        tfSL = makeField(); tfSL.setBounds(250, 175, 380, 42);

        JLabel lbGia = new JLabel("Giá"); lbGia.setFont(labelFont); lbGia.setBounds(60, 250, 220, 30);
        tfGia = makeField(); tfGia.setBounds(250, 245, 380, 42);

        JLabel lbDate = new JLabel("Date"); lbDate.setFont(labelFont); lbDate.setBounds(60, 320, 220, 30);
        tfDate = makeField(); tfDate.setBounds(250, 315, 380, 42);

        JLabel lbThemDate = new JLabel("+ Thêm date");
        lbThemDate.setFont(new Font("Arial", Font.PLAIN, 18));
        lbThemDate.setForeground(new Color(0x555555));
        lbThemDate.setBounds(645, 323, 160, 30);
        lbThemDate.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lbKM = new JLabel("Khuyến mãi"); lbKM.setFont(labelFont); lbKM.setBounds(60, 390, 220, 30);
        tfKM = makeField(); tfKM.setBounds(250, 385, 380, 42);

        JLabel lbAnh = new JLabel("Ảnh"); lbAnh.setFont(labelFont); lbAnh.setBounds(60, 460, 220, 30);
        JButton btnAnh = new JButton("+ Thêm ảnh");
        btnAnh.setFont(new Font("Arial", Font.PLAIN, 18));
        btnAnh.setBackground(new Color(0xE0DDE8));
        btnAnh.setForeground(new Color(0x666666));
        btnAnh.setFocusPainted(false);
        btnAnh.setBounds(250, 450, 200, 70);
        btnAnh.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAnh.setBorder(BorderFactory.createLineBorder(new Color(0xAAAAAA), 1, true));

        form.add(lbTen);  form.add(tfTen);
        form.add(lbMa);   form.add(tfMa);
        form.add(lbSL);   form.add(tfSL);
        form.add(lbGia);  form.add(tfGia);
        form.add(lbDate); form.add(tfDate); form.add(lbThemDate);
        form.add(lbKM);   form.add(tfKM);
        form.add(lbAnh);  form.add(btnAnh);
        themCard.add(form, BorderLayout.CENTER);

        JPanel formFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 16));
        formFooter.setBackground(new Color(0xF0EFF8));
        formFooter.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0xCCCCCC)));

        JButton btnLuu = new JButton("LƯU");
        btnLuu.setFont(new Font("Arial", Font.BOLD, 24));
        btnLuu.setBackground(new Color(0xB83434));
        btnLuu.setForeground(Color.WHITE);
        btnLuu.setFocusPainted(false);
        btnLuu.setBorderPainted(false);
        btnLuu.setPreferredSize(new Dimension(160, 52));
        btnLuu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        formFooter.add(btnLuu);
        themCard.add(formFooter, BorderLayout.SOUTH);

        btnLuu.addActionListener(e -> {
            String ten   = tfTen.getText().trim();
            String ma    = tfMa.getText().trim();
            String slStr = tfSL.getText().trim();
            String gia   = tfGia.getText().trim();
            String date  = tfDate.getText().trim();

            if (ten.isEmpty() || ma.isEmpty() || slStr.isEmpty() || gia.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(themCard,
                    "Vui lòng nhập đủ các trường bắt buộc:\nTên SP, Mã SP, Số lượng, Giá, Date.",
                    "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int sl;
            try {
                sl = Integer.parseInt(slStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(themCard,
                    "Số lượng phải là số nguyên.",
                    "Sai định dạng", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!gia.endsWith("đ") && !gia.endsWith("d")) gia += "đ";
            String kho = sl > 0 ? "Còn hàng" : "Hết hàng";
            String km  = tfKM.getText().trim().isEmpty() ? "-" : tfKM.getText().trim();

                    if (editingRow >= 0) {
            model.setValueAt(ma, editingRow, 0);
            model.setValueAt(ten, editingRow, 2);
            model.setValueAt(gia, editingRow, 3);
            model.setValueAt(sl, editingRow, 4);
            model.setValueAt(kho, editingRow, 5);
            model.setValueAt(date, editingRow, 6);
            model.setValueAt(km, editingRow, 7);
            editingRow = -1;
        } else {
            model.addRow(new Object[]{ ma, "", ten, gia, sl, kho, date, km, "" });
        }

            tfTen.setText(""); tfMa.setText(""); tfSL.setText("");
            tfGia.setText(""); tfDate.setText(""); tfKM.setText("");

            innerCard.show(panel, CARD_TABLE);
        });

        panel.add(tableCard, CARD_TABLE);
        panel.add(themCard,  CARD_THEM);
        innerCard.show(panel, CARD_TABLE);

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


    //text field chung
    private JTextField makeField() {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Arial", Font.PLAIN, 20));
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xAAAAAA), 1, true),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        return tf;
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
