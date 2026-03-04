import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class GUI extends JFrame {
    private JButton btnTrangChu, btnSanPham, btnKhachHang, btnNhanVien, btnDonHang, btnKho, btnKhuyenMai, btnUser;
    private JPanel Main;
    private CardLayout cardLayout;

    private int editingRow = -1;

    private JTextField tfTen;
    private JTextField tfMa;
    private JTextField tfSL;
    private JTextField tfGia;
    private JTextField tfDate;
    private JTextField tfKM;

    private JTextField khTenKH;
    private JRadioButton rbNam, rbNu;
    private ButtonGroup bgGioiTinh;
    private JTextField khNgaySinh;
    private JTextField khHang;
    private JTextField khDiem;
    private JTextField khDiaChi;
    private JTextField khEmail;
    private JTextField khSDT;

    private int khEditingRow  = -1;
    private int khViewingRow  = -1;

    private static final String TRANG_CHU  = "TRANG_CHU";
    private static final String SAN_PHAM   = "SAN_PHAM";
    private static final String KHACH_HANG = "KHACH_HANG";
    private static final String NHAN_VIEN  = "NHAN_VIEN";
    private static final String DON_HANG   = "DON_HANG";
    private static final String KHO        = "KHO";
    private static final String KHUYEN_MAI = "KHUYEN_MAI";
    private static final String USER = "USER";

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
        btnUser = createStyledButton("👤 Tài khoản");

        
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
        Left.add(btnUser);

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
        Main.add(createPanelUser(), USER);

        add(Main, BorderLayout.CENTER);
    
        // Sau khi tạo các button
        btnTrangChu.addActionListener(e -> cardLayout.show(Main, TRANG_CHU));
        btnSanPham.addActionListener(e -> cardLayout.show(Main, SAN_PHAM));
        btnKhachHang.addActionListener(e -> cardLayout.show(Main, KHACH_HANG));
        btnNhanVien.addActionListener(e -> cardLayout.show(Main, NHAN_VIEN));
        btnDonHang.addActionListener(e -> cardLayout.show(Main, DON_HANG));
        btnKho.addActionListener(e -> cardLayout.show(Main, KHO));
        btnUser.addActionListener(e -> cardLayout.show(Main, USER));
    }

     private JPanel createPanelTrangChu() {
        JPanel panel = new JPanel(new BorderLayout(0, 0));
    panel.setBackground(new Color(0xF8F7FF));

    // 4 panel ngang trên cùng
    JPanel topCards = new JPanel(new GridLayout(1, 4, 30, 0)); //só hàng, số cột, k/c ngang, k/c dọc
    topCards.setBackground(new Color(0xF8F7FF));
    topCards.setBorder(BorderFactory.createEmptyBorder(18, 18, 12, 18));

    Object[][] cardData = {
        { "💳", "Doanh thu:",    "15.000.000 VND", new Color(0xD4F4E2), new Color(0x5CB85C) }, //màu thẻ, màu viền
        { "🛒", "Đơn hàng mới:", "25 đơn",         new Color(0xCDE8FF), new Color(0x4A90D9) },
        { "⚠",  "Sản phẩm sắp hết:", "5 mặt hàng", new Color(0xFFF3CD), new Color(0xF0AD4E) },
        { "👤", "Khách hàng mới:", "+10 khách",    new Color(0xF5D0F5), new Color(0xAB47BC) },
    };

    for (Object[] d : cardData) {
        JPanel card = new JPanel(new BorderLayout(10, 0)) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
                g2.setColor(((Color) d[4]).darker());
                g2.setStroke(new BasicStroke(2f));
                g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 18, 18);
                g2.dispose();
            }
        };
        card.setBackground((Color) d[3]);
        card.setOpaque(false);
        card.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));

        JLabel icon = new JLabel((String) d[0]);
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        icon.setHorizontalAlignment(SwingConstants.CENTER);
        icon.setPreferredSize(new Dimension(42, 42));

        JPanel txtPanel = new JPanel(new GridLayout(2, 1, 0, 2));
        txtPanel.setOpaque(false);

        JLabel lbTitle = new JLabel((String) d[1]);
        lbTitle.setFont(new Font("Arial", Font.BOLD, 15));
        lbTitle.setForeground(new Color(0x444444));

        JLabel lbVal = new JLabel((String) d[2]);
        lbVal.setFont(new Font("Arial", Font.BOLD, 17));
        lbVal.setForeground(new Color(0x222222));

        txtPanel.add(lbTitle);
        txtPanel.add(lbVal);

        card.add(icon,     BorderLayout.WEST);
        card.add(txtPanel, BorderLayout.CENTER);
        topCards.add(card);
    }

    // panel của đồ thị và đơn hàng
    JPanel centerPanel = new JPanel(new GridLayout(1, 2, 14, 0));
    centerPanel.setBackground(new Color(0xF8F7FF));
    centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 18, 18, 18));

    // đô thị
    JPanel chartCard = createCard();

    JLabel chartTitle = new JLabel("Sales Growth (Tạm thời để z đi r mốt có thông số đầy đủ r sửa lại)");
    chartTitle.setFont(new Font("Playfair Display", Font.BOLD, 20));
    chartTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

    // Vẽ biểu đồ đường đơn giản bằng canvas
    int[] chartValues = { 10, 22, 38, 32, 55, 52, 88, 100 };
    String[] chartLabels = { "T2", "T3", "T4", "T5", "T6", "T7", "CN" };

    JPanel chart = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
            int padL = 40, padR = 20, padT = 20, padB = 30;
            int chartW = w - padL - padR;
            int chartH = h - padT - padB;
            int n = chartValues.length;

            // Đường lưới ngang
            g2.setColor(new Color(0xDDDDDD));
            g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4}, 0));
            for (int i = 0; i <= 4; i++) {
                int y = padT + chartH * i / 4;
                g2.drawLine(padL, y, padL + chartW, y);
                g2.setColor(new Color(0x999999));
                g2.setFont(new Font("Arial", Font.PLAIN, 11));
                g2.drawString(String.valueOf(100 - 25 * i), 2, y + 4);
                g2.setColor(new Color(0xDDDDDD));
            }

            // Vùng tô bên dưới đường
            int[] xs = new int[n + 2];
            int[] ys = new int[n + 2];
            for (int i = 0; i < n; i++) {
                xs[i] = padL + i * chartW / (n - 1);
                ys[i] = padT + chartH - chartValues[i] * chartH / 100;
            }
            xs[n] = padL + chartW; ys[n] = padT + chartH;
            xs[n + 1] = padL;       ys[n + 1] = padT + chartH;

            g2.setColor(new Color(0xB8A9D9));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.35f));
            g2.fillPolygon(xs, ys, n + 2);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

            // Đường chính
            g2.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.setColor(new Color(0x7B68AE));
            for (int i = 0; i < n - 1; i++) {
                g2.drawLine(xs[i], ys[i], xs[i + 1], ys[i + 1]);
            }

            // Điểm tròn
            g2.setColor(new Color(0x7B68AE));
            for (int i = 0; i < n; i++) {
                g2.fillOval(xs[i] - 4, ys[i] - 4, 8, 8);
            }

            // Nhãn trục X
            g2.setColor(new Color(0x666666));
            g2.setFont(new Font("Arial", Font.PLAIN, 11));
            for (int i = 0; i < chartLabels.length; i++) {
                int xi = padL + i * chartW / (n - 1);
                g2.drawString(chartLabels[i], xi - 8, h - 6);
            }
        }
    };
    chart.setOpaque(false);
    chart.setPreferredSize(new Dimension(0, 260));

    chartCard.add(chartTitle, BorderLayout.NORTH);
    chartCard.add(chart,      BorderLayout.CENTER);

    //Bảng đơn hàng gần đây 
    JPanel orderCard = createCard();

    JLabel orderTitle = new JLabel("Đơn hàng gần đây");
    orderTitle.setFont(new Font("Playfair Display", Font.BOLD, 20));
    orderTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

    String[] orderCols = { "Mã đơn", "Khách hàng", "Trạng thái" };
    Object[][] orderRows = {
        { "HD001", "Nguyễn Văn A", "Đang giao" },
        { "HD002", "Nguyễn Văn N", "Đã giao"   },
        { "HD003", "Nguyễn Văn D", "Bị boom"   },
    };

    DefaultTableModel orderModel = new DefaultTableModel(orderRows, orderCols) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    JTable orderTable = new JTable(orderModel);
    orderTable.setFont(new Font("Arial", Font.PLAIN, 16));
    orderTable.setRowHeight(38);
    orderTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
    orderTable.getTableHeader().setBackground(new Color(0xAF9FCB));
    orderTable.getTableHeader().setForeground(Color.WHITE);
    orderTable.setShowVerticalLines(false);
    orderTable.setGridColor(new Color(0xEEEEEE));

    // Màu trạng thái
    DefaultTableCellRenderer statusRenderer = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable t, Object v,
                boolean sel, boolean foc, int row, int col) {
            super.getTableCellRendererComponent(t, v, sel, foc, row, col);
            setHorizontalAlignment(SwingConstants.CENTER);
            if (!sel) {
                setBackground(row % 2 == 0 ? Color.WHITE : new Color(0xF3F0FA));
            }
            if (col == 2) {
                String val = v == null ? "" : v.toString();
                switch (val) {
                    case "Đang giao" -> setForeground(new Color(0x1976D2));
                    case "Đã giao"   -> setForeground(new Color(0x388E3C));
                    case "Bị boom"   -> setForeground(new Color(0xC62828));
                    default          -> setForeground(Color.BLACK);
                }
            } else {
                setForeground(Color.BLACK);
            }
            return this;
        }
    };
    for (int i = 0; i < 3; i++)
        orderTable.getColumnModel().getColumn(i).setCellRenderer(statusRenderer);

    orderCard.add(orderTitle,              BorderLayout.NORTH);
    orderCard.add(new JScrollPane(orderTable), BorderLayout.CENTER);

    centerPanel.add(chartCard);
    centerPanel.add(orderCard);

    panel.add(topCards,    BorderLayout.NORTH);
    panel.add(centerPanel, BorderLayout.CENTER);
    return panel;
    }

    private JPanel createCard() {
    JPanel card = new JPanel(new BorderLayout(0, 8)) {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // Bóng đổ nhẹ
            g2.setColor(new Color(0, 0, 0, 40));
            g2.fillRoundRect(6, 8, getWidth() - 4, getHeight() - 4, 16, 16);
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth() - 2, getHeight() - 2, 16, 16);
            g2.dispose();
        }
    };
    card.setOpaque(false);
    card.setBorder(BorderFactory.createEmptyBorder(16, 18, 16, 18));
    return card;
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

        bang.setRowHeight(52);
        bang.setFont(new Font("Arial", Font.PLAIN, 16));
        bang.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        bang.getTableHeader().setPreferredSize(new Dimension(1166, 52));
        bang.getTableHeader().setBackground(new Color(0xAF9FCB));  // tím giống bảng đơn hàng
        bang.getTableHeader().setForeground(Color.WHITE);
        bang.getTableHeader().setReorderingAllowed(false);
        bang.setShowVerticalLines(false);         // bỏ đường dọc
        bang.setGridColor(new Color(0xEEEEEE));   // đường ngang nhạt
        bang.setIntercellSpacing(new Dimension(0, 1));
        bang.getColumnModel().getColumn(0).setPreferredWidth(70);
        bang.getColumnModel().getColumn(1).setPreferredWidth(60);
        bang.getColumnModel().getColumn(2).setPreferredWidth(180);
        bang.getColumnModel().getColumn(4).setPreferredWidth(70);
        bang.getColumnModel().getColumn(8).setPreferredWidth(100);

        // Renderer xen kẽ màu row giống bảng đơn hàng gần đây
        DefaultTableCellRenderer altRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(0xF3F0FA)); // trắng / tím rất nhạt
                }
                setHorizontalAlignment(SwingConstants.CENTER);

                // Tô màu cột "Kho" theo trạng thái
                if (column == 5 && !isSelected) {
                    String val = value == null ? "" : value.toString();
                    switch (val) {
                        case "Còn hàng" -> setForeground(new Color(0x388E3C));
                        case "Hết hàng" -> setForeground(new Color(0xC62828));
                        default         -> setForeground(Color.BLACK);
                    }
                } else {
                    setForeground(Color.BLACK);
                }
                return c;
            }
        };
        for (int i = 0; i < bang.getColumnCount() - 1; i++) // bỏ cột thao tác
            bang.getColumnModel().getColumn(i).setCellRenderer(altRenderer);

        // Renderer nút Sửa/Xóa — gọn lại, bo tròn hơn
        bang.getColumnModel().getColumn(8).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 8));
                p.setBackground(row % 2 == 0 ? Color.WHITE : new Color(0xF3F0FA));
                JButton sua = makeActionButton("Sửa", new Color(0x6677C8));
                JButton xoa = makeActionButton("Xóa", new Color(0xB83434));
                p.add(sua); p.add(xoa);
                return p;
            }
        });

        bang.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            private final JPanel p    = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 8));
            private final JButton sua = makeActionButton("Sửa", new Color(0x6677C8));
            private final JButton xoa = makeActionButton("Xóa", new Color(0xB83434));
            private int currentRow = -1;
            {
                p.setOpaque(true);
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
                p.setBackground(row % 2 == 0 ? Color.WHITE : new Color(0xF3F0FA));
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

    //text field chung
    private JTextField makeField() {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Arial", Font.PLAIN, 20));
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xAAAAAA), 1, true),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        return tf;
    }

    private JButton makeActionButton(String text, Color bg) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setOpaque(false);
        btn.setPreferredSize(new Dimension(60, 32));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JPanel createPanelKhachHang() {
        CardLayout innerCard = new CardLayout();
        JPanel panel = new JPanel(innerCard);

        // Cột: 0-HoTen, 1-GioiTinh, 2-NgaySinh, 3-Hang, 4-Diem, 5-DiaChi, 6-Email, 7-SDT, 8-ThaoTac
        String[] columns = {
            "Họ tên", "Giới tính", "Ngày sinh",
            "Hạng", "Điểm", "Địa chỉ", "Email", "SĐT", "Thao tác"
        };
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return column == 8; }
        };
        model.addRow(new Object[]{ "Nhan Thị Ngọc Trân", "Nữ",  "10/11/2006", "Vàng", "45", "Sao Hỏa",  "ngoctran@gmail.com",    "0904254315", "" });
        model.addRow(new Object[]{ "Nguyễn Thái Thảo",   "Nữ",  "12/07/2006", "Bạc",  "36", "Sao Thủy", "meolanhmanh@gmail.com", "0123456789", "" });

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

        // index: 0=Tất cả, 1=Nam, 2=Nữ, 3=Đồng, 4=Bạc, 5=Vàng, 6=Kim cương
        String[] boloc = { "Tất cả", "Nam", "Nữ", "Đồng", "Bạc", "Vàng", "Kim cương" };
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

        JButton them = new JButton("+ Thêm khách hàng");
        them.setFocusPainted(false);
        them.setBackground(new Color(0xD9D9D9));
        them.setPreferredSize(new Dimension(254, 42));
        them.setFont(new Font("Arial", Font.BOLD, 22));
        them.setCursor(new Cursor(Cursor.HAND_CURSOR));
        them.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { them.setBackground(new Color(0xC5B3E6)); }
            public void mouseExited(java.awt.event.MouseEvent evt)  { them.setBackground(new Color(0xD9D9D9)); }
        });
        // Khai báo sớm để dùng trong tất cả lambda
        JButton btnLuu = new JButton("LƯU");
        btnLuu.setFont(new Font("Arial", Font.BOLD, 24));
        btnLuu.setBackground(new Color(0xB83434));
        btnLuu.setForeground(Color.WHITE);
        btnLuu.setFocusPainted(false);
        btnLuu.setBorderPainted(false);
        btnLuu.setPreferredSize(new Dimension(160, 52));
        btnLuu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        them.addActionListener(e -> {
            khEditingRow = -1;
            // Re-enable fields phòng trường hợp vừa xem
            khTenKH.setEditable(true);   khNgaySinh.setEditable(true);
            khHang.setEditable(true);    khDiem.setEditable(true);
            khDiaChi.setEditable(true);  khEmail.setEditable(true);
            khSDT.setEditable(true);
            rbNam.setEnabled(true);      rbNu.setEnabled(true);
            btnLuu.setText("LƯU");
            btnLuu.setBackground(new Color(0xB83434));
            khViewingRow = -1;
            khTenKH.setText(""); rbNam.setSelected(true); khNgaySinh.setText("");
            khHang.setText(""); khDiem.setText(""); khDiaChi.setText("");
            khEmail.setText(""); khSDT.setText("");
            innerCard.show(panel, CARD_THEM);
        });

        // Filter: cột 1 = Giới tính, cột 3 = Hạng; tìm kiếm theo cột 0 = Họ tên
        Runnable applyFilter = () -> {
            String tuKhoa = tim.getText().trim();
            int idxLoc = cbLoc.getSelectedIndex();

            RowFilter<DefaultTableModel, Integer> filterLoc = switch (idxLoc) {
                case 1 -> RowFilter.regexFilter("(?i)^Nam$",        1);  // Giới tính Nam
                case 2 -> RowFilter.regexFilter("(?i)^Nữ$",         1);  // Giới tính Nữ
                case 3 -> RowFilter.regexFilter("(?i)^Đồng$",       3);  // Hạng Đồng
                case 4 -> RowFilter.regexFilter("(?i)^Bạc$",        3);  // Hạng Bạc
                case 5 -> RowFilter.regexFilter("(?i)^Vàng$",       3);  // Hạng Vàng
                case 6 -> RowFilter.regexFilter("(?i)^Kim cương$",  3);  // Hạng Kim cương
                default -> null;                                          // Tất cả
            };

            RowFilter<DefaultTableModel, Integer> filterTim = tuKhoa.isEmpty()
                ? null
                : RowFilter.regexFilter("(?i)" + tuKhoa, 0);  // tìm theo Họ tên (cột 0)

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

        bang.setRowHeight(52);
        bang.setFont(new Font("Arial", Font.PLAIN, 16));
        bang.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        bang.getTableHeader().setPreferredSize(new Dimension(1166, 52));
        bang.getTableHeader().setBackground(new Color(0xAF9FCB));  // tím giống bảng đơn hàng
        bang.getTableHeader().setForeground(Color.WHITE);
        bang.getTableHeader().setReorderingAllowed(false);
        bang.setShowVerticalLines(false);         // bỏ đường dọc
        bang.setGridColor(new Color(0xEEEEEE));   // đường ngang nhạt
        bang.setIntercellSpacing(new Dimension(0, 1));
        bang.getColumnModel().getColumn(0).setPreferredWidth(70);
        bang.getColumnModel().getColumn(1).setPreferredWidth(60);
        bang.getColumnModel().getColumn(2).setPreferredWidth(180);
        bang.getColumnModel().getColumn(4).setPreferredWidth(70);
        bang.getColumnModel().getColumn(8).setPreferredWidth(100);

        // Renderer xen kẽ màu row giống bảng đơn hàng gần đây
        DefaultTableCellRenderer altRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(0xF3F0FA)); // trắng / tím rất nhạt
                }
                setHorizontalAlignment(SwingConstants.CENTER);

                // Tô màu cột "Kho" theo trạng thái
                if (column == 5 && !isSelected) {
                    String val = value == null ? "" : value.toString();
                    switch (val) {
                        case "Còn hàng" -> setForeground(new Color(0x388E3C));
                        case "Hết hàng" -> setForeground(new Color(0xC62828));
                        default         -> setForeground(Color.BLACK);
                    }
                } else {
                    setForeground(Color.BLACK);
                }
                return c;
            }
        };
        for (int i = 0; i < bang.getColumnCount() - 1; i++) // bỏ cột thao tác
            bang.getColumnModel().getColumn(i).setCellRenderer(altRenderer);

        // Renderer nút Sửa/Xóa — gọn lại, bo tròn hơn
        bang.getColumnModel().getColumn(8).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 8));
                p.setBackground(row % 2 == 0 ? Color.WHITE : new Color(0xF3F0FA));
                JButton sua = makeActionButton("Sửa", new Color(0x6677C8));
                JButton xoa = makeActionButton("Xóa", new Color(0xB83434));
                p.add(sua); p.add(xoa);
                return p;
            }
        });

        bang.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            private final JPanel p    = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 8));
            private final JButton sua = makeActionButton("Sửa", new Color(0x6677C8));
            private final JButton xoa = makeActionButton("Xóa", new Color(0xB83434));
            private int currentRow = -1;
            {
                p.setOpaque(true);
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
                p.setBackground(row % 2 == 0 ? Color.WHITE : new Color(0xF3F0FA));
                return p;
            }
            @Override public Object getCellEditorValue() { return ""; }
        });

        content.add(new JScrollPane(bang), BorderLayout.CENTER);
        tableCard.add(top,     BorderLayout.NORTH);
        tableCard.add(content, BorderLayout.CENTER);

        // ── CARD 2: Form thêm / sửa khách hàng ─────────────────────
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
        btnQuayLai.addActionListener(e -> {
            // Re-enable fields khi quay lại
            khTenKH.setEditable(true);   khNgaySinh.setEditable(true);
            khHang.setEditable(true);    khDiem.setEditable(true);
            khDiaChi.setEditable(true);  khEmail.setEditable(true);
            khSDT.setEditable(true);
            rbNam.setEnabled(true);      rbNu.setEnabled(true);
            btnLuu.setText("LƯU");
            btnLuu.setBackground(new Color(0xB83434));
            khEditingRow = -1;
            khViewingRow = -1;
            innerCard.show(panel, CARD_TABLE);
        });
        formHeader.add(btnQuayLai);
        themCard.add(formHeader, BorderLayout.NORTH);

        // Form với 2 cột để hiển thị đủ 8 trường gọn gàng
        JPanel form = new JPanel(null);
        form.setBackground(new Color(0xF0EFF8));
        Font labelFont = new Font("Arial", Font.BOLD, 20);

        // Cột trái
        JLabel lbTenKH = new JLabel("Họ tên");        lbTenKH.setFont(labelFont);    lbTenKH.setBounds(40, 40, 180, 30);
        khTenKH   = makeField(); khTenKH.setBounds(220, 35, 340, 42);

        JLabel lbGT = new JLabel("Giới tính");         lbGT.setFont(labelFont);       lbGT.setBounds(40, 110, 180, 30);
        rbNam  = new JRadioButton("Nam");
        rbNu   = new JRadioButton("Nữ");
        bgGioiTinh = new ButtonGroup();
        bgGioiTinh.add(rbNam); bgGioiTinh.add(rbNu);
        rbNam.setSelected(true);
        Font radioFont = new Font("Arial", Font.PLAIN, 20);
        rbNam.setFont(radioFont);  rbNu.setFont(radioFont);
        rbNam.setBackground(new Color(0xF0EFF8));
        rbNu.setBackground(new Color(0xF0EFF8));
        rbNam.setBounds(220, 105, 90, 42);
        rbNu.setBounds(320, 105, 80, 42);

        JLabel lbNS = new JLabel("Ngày sinh");         lbNS.setFont(labelFont);       lbNS.setBounds(40, 180, 180, 30);
        khNgaySinh = makeField(); khNgaySinh.setBounds(220, 175, 340, 42);
        JLabel lbNSHint = new JLabel("dd/MM/yyyy");
        lbNSHint.setFont(new Font("Arial", Font.ITALIC, 16));
        lbNSHint.setForeground(new Color(0x888888));
        lbNSHint.setBounds(570, 183, 120, 30);

        JLabel lbHang = new JLabel("Hạng KH");         lbHang.setFont(labelFont);     lbHang.setBounds(40, 250, 180, 30);
        khHang    = makeField(); khHang.setBounds(220, 245, 340, 42);
        JLabel lbHangHint = new JLabel("Đồng / Bạc / Vàng / Kim cương");
        lbHangHint.setFont(new Font("Arial", Font.ITALIC, 15));
        lbHangHint.setForeground(new Color(0x888888));
        lbHangHint.setBounds(570, 253, 280, 30);

        JLabel lbDiem = new JLabel("Điểm");             lbDiem.setFont(labelFont);     lbDiem.setBounds(40, 320, 180, 30);
        khDiem    = makeField(); khDiem.setBounds(220, 315, 340, 42);

        // Cột phải
        JLabel lbDC = new JLabel("Địa chỉ");           lbDC.setFont(labelFont);       lbDC.setBounds(700, 40, 180, 30);
        khDiaChi  = makeField(); khDiaChi.setBounds(870, 35, 340, 42);

        JLabel lbEmail = new JLabel("Email");           lbEmail.setFont(labelFont);    lbEmail.setBounds(700, 110, 180, 30);
        khEmail   = makeField(); khEmail.setBounds(870, 105, 340, 42);

        JLabel lbSDT = new JLabel("SĐT");               lbSDT.setFont(labelFont);      lbSDT.setBounds(700, 180, 180, 30);
        khSDT     = makeField(); khSDT.setBounds(870, 175, 340, 42);

        form.add(lbTenKH);    form.add(khTenKH);
        form.add(lbGT);       form.add(rbNam); form.add(rbNu);
        form.add(lbNS);       form.add(khNgaySinh); form.add(lbNSHint);
        form.add(lbHang);     form.add(khHang);     form.add(lbHangHint);
        form.add(lbDiem);     form.add(khDiem);
        form.add(lbDC);       form.add(khDiaChi);
        form.add(lbEmail);    form.add(khEmail);
        form.add(lbSDT);      form.add(khSDT);

        themCard.add(form, BorderLayout.CENTER);

        JPanel formFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 16));
        formFooter.setBackground(new Color(0xF0EFF8));
        formFooter.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0xCCCCCC)));

        formFooter.add(btnLuu);
        themCard.add(formFooter, BorderLayout.SOUTH);

        btnLuu.addActionListener(e -> {
            // ── Chế độ CẬP NHẬT: chỉ cập nhật Hạng và Điểm ──────
            if (khViewingRow >= 0) {
                String hang    = khHang.getText().trim();
                String diemTxt = khDiem.getText().trim();
                if (hang.isEmpty() || diemTxt.isEmpty()) {
                    JOptionPane.showMessageDialog(themCard,
                        "Vui lòng nhập Hạng và Điểm.",
                        "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int diem;
                try { diem = Integer.parseInt(diemTxt); }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(themCard,
                        "Điểm phải là số nguyên.",
                        "Sai định dạng", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                model.setValueAt(hang,                 khViewingRow, 3);
                model.setValueAt(String.valueOf(diem), khViewingRow, 4);
                khViewingRow = -1;

            // ── Chế độ THÊM MỚI ───────────────────────────────────
            } else {
                String ten    = khTenKH.getText().trim();
                String gt     = rbNam.isSelected() ? "Nam" : "Nữ";
                String ns     = khNgaySinh.getText().trim();
                String hang   = khHang.getText().trim();
                String diemTxt= khDiem.getText().trim();
                String dc     = khDiaChi.getText().trim();
                String email  = khEmail.getText().trim();
                String sdt    = khSDT.getText().trim();
                if (ten.isEmpty() || ns.isEmpty() || sdt.isEmpty()) {
                    JOptionPane.showMessageDialog(themCard,
                        "Vui lòng nhập đủ các trường bắt buộc:\nHọ tên, Ngày sinh, SĐT.",
                        "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int diem = 0;
                if (!diemTxt.isEmpty()) {
                    try { diem = Integer.parseInt(diemTxt); }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(themCard,
                            "Điểm phải là số nguyên.",
                            "Sai định dạng", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                if (hang.isEmpty()) {
                    if      (diem >= 500) hang = "Kim cương";
                    else if (diem >= 200) hang = "Vàng";
                    else if (diem >= 100) hang = "Bạc";
                    else                  hang = "Đồng";
                }
                model.addRow(new Object[]{ ten, gt, ns, hang, String.valueOf(diem), dc, email, sdt, "" });
            }

            // Reset form và khôi phục trạng thái
            khTenKH.setText(""); rbNam.setSelected(true); khNgaySinh.setText("");
            khHang.setText(""); khDiem.setText(""); khDiaChi.setText("");
            khEmail.setText(""); khSDT.setText("");
            khTenKH.setEditable(true);   khNgaySinh.setEditable(true);
            khHang.setEditable(true);    khDiem.setEditable(true);
            khDiaChi.setEditable(true);  khEmail.setEditable(true);
            khSDT.setEditable(true);
            rbNam.setEnabled(true);      rbNu.setEnabled(true);
            btnLuu.setText("LƯU");
            btnLuu.setBackground(new Color(0xB83434));
            innerCard.show(panel, CARD_TABLE);
        });

        panel.add(tableCard, CARD_TABLE);
        panel.add(themCard,  CARD_THEM);
        innerCard.show(panel, CARD_TABLE);

        return panel;
    }

    private JPanel createPanelNhanVien() {
        CardLayout innerCard = new CardLayout();
        JPanel panel = new JPanel(innerCard);

        String[] columns = {
            "Mã chấm công", "Họ tên", "Giới tính", "Ngày sinh",
            "Chức vụ", "Địa chỉ", "Email", "SĐT", "Thao tác"
        };
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return c == 8; }
        };
        model.addRow(new Object[]{ "A123",   "Nhan Thị Ngọc Trân", "Nữ", "10/11/2006", "Nhân viên",   "A33/19D Bình Hưng Bình Chánh", "ngoctran@gmail.com",    "0904254315", "" });
        model.addRow(new Object[]{ "C66859", "Nguyễn Thái Thảo",   "Nữ", "12/07/2006", "Nhân viên", "Saohoa/1233",                   "meolanhmanh@gmail.com", "0123456789", "" });

        // ── Fields riêng cho Nhân viên ─────────────────────────────
        JTextField nvMa       = makeField();
        JTextField nvTen      = makeField();
        JRadioButton nvRbNam  = new JRadioButton("Nam");
        JRadioButton nvRbNu   = new JRadioButton("Nữ");
        ButtonGroup  nvBgGT   = new ButtonGroup();
        nvBgGT.add(nvRbNam); nvBgGT.add(nvRbNu);
        nvRbNam.setSelected(true);
        JTextField nvNgaySinh = makeField();
        JTextField nvChucVu   = makeField();
        JTextField nvDiaChi   = makeField();
        JTextField nvEmail    = makeField();
        JTextField nvSDT      = makeField();

        int[] nvEditingRow = { -1 };

        JButton btnLuuNV = new JButton("LƯU");
        btnLuuNV.setFont(new Font("Arial", Font.BOLD, 24));
        btnLuuNV.setBackground(new Color(0xB83434)); btnLuuNV.setForeground(Color.WHITE);
        btnLuuNV.setFocusPainted(false); btnLuuNV.setBorderPainted(false);
        btnLuuNV.setPreferredSize(new Dimension(160, 52));
        btnLuuNV.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ── CARD 1: Bảng danh sách ──────────────────────────────────
        JPanel tableCard = new JPanel(new BorderLayout());

        JTable bang = new JTable(model);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        bang.setRowSorter(sorter);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        top.setPreferredSize(new Dimension(1174, 94));
        top.setBackground(new Color(0xF8F7FF));
        top.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 1),
            BorderFactory.createEmptyBorder(20, 10, 20, 10)));

        String[] boloc = { "Tất cả", "Nam", "Nữ" };
        JComboBox<String> cbLoc = new JComboBox<>(boloc);
        cbLoc.setPreferredSize(new Dimension(220, 42));
        cbLoc.setFont(new Font("Arial", Font.PLAIN, 22));
        cbLoc.setBackground(new Color(0xD9D9D9));

        JPanel timkiem = new JPanel(new BorderLayout());
        timkiem.setPreferredSize(new Dimension(229, 42));
        timkiem.setBackground(new Color(0xD9D9D9));
        JTextField tim = new JTextField();
        tim.setFont(new Font("Arial", Font.PLAIN, 22));
        timkiem.add(tim, BorderLayout.CENTER);

        JButton nuttim = new JButton("🔍");
        nuttim.setBorderPainted(false); nuttim.setContentAreaFilled(false);
        nuttim.setFocusPainted(false);  nuttim.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nuttim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                nuttim.setContentAreaFilled(true); nuttim.setBackground(new Color(0xC5B3E6)); nuttim.setOpaque(true);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                nuttim.setContentAreaFilled(false); nuttim.setOpaque(false);
            }
        });
        timkiem.add(nuttim, BorderLayout.EAST);

        JButton btnThem = new JButton("+ Thêm nhân viên");
        btnThem.setFocusPainted(false); btnThem.setBackground(new Color(0xD9D9D9));
        btnThem.setPreferredSize(new Dimension(230, 42));
        btnThem.setFont(new Font("Arial", Font.BOLD, 20));
        btnThem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { btnThem.setBackground(new Color(0xC5B3E6)); }
            public void mouseExited(java.awt.event.MouseEvent e)  { btnThem.setBackground(new Color(0xD9D9D9)); }
        });

        Runnable applyFilter = () -> {
            String kw = tim.getText().trim();
            int idx = cbLoc.getSelectedIndex();
            RowFilter<DefaultTableModel, Integer> fLoc = switch (idx) {
                case 1 -> RowFilter.regexFilter("(?i)^Nam$",       2);
                case 2 -> RowFilter.regexFilter("(?i)^Nữ$",        2);
                default -> null;
            };
            RowFilter<DefaultTableModel, Integer> fTim = kw.isEmpty()
                ? null : RowFilter.regexFilter("(?i)" + kw, 1);
            if (fLoc != null && fTim != null)
                sorter.setRowFilter(RowFilter.andFilter(java.util.List.of(fLoc, fTim)));
            else if (fLoc != null) sorter.setRowFilter(fLoc);
            else if (fTim != null) sorter.setRowFilter(fTim);
            else sorter.setRowFilter(null);
        };
        cbLoc.addActionListener(e -> applyFilter.run());
        nuttim.addActionListener(e -> applyFilter.run());
        tim.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e)  { applyFilter.run(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e)  { applyFilter.run(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { applyFilter.run(); }
        });

        top.add(cbLoc); top.add(timkiem); top.add(btnThem); 

        bang.setRowHeight(52);
        bang.setFont(new Font("Arial", Font.PLAIN, 16));
        bang.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        bang.getTableHeader().setPreferredSize(new Dimension(1166, 52));
        bang.getTableHeader().setBackground(new Color(0xAF9FCB));  // tím giống bảng đơn hàng
        bang.getTableHeader().setForeground(Color.WHITE);
        bang.getTableHeader().setReorderingAllowed(false);
        bang.setShowVerticalLines(false);         // bỏ đường dọc
        bang.setGridColor(new Color(0xEEEEEE));   // đường ngang nhạt
        bang.setIntercellSpacing(new Dimension(0, 1));
        bang.getColumnModel().getColumn(0).setPreferredWidth(70);
        bang.getColumnModel().getColumn(1).setPreferredWidth(60);
        bang.getColumnModel().getColumn(2).setPreferredWidth(180);
        bang.getColumnModel().getColumn(4).setPreferredWidth(70);
        bang.getColumnModel().getColumn(8).setPreferredWidth(100);

        // Renderer xen kẽ màu row giống bảng đơn hàng gần đây
        DefaultTableCellRenderer altRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(0xF3F0FA)); // trắng / tím rất nhạt
                }
                setHorizontalAlignment(SwingConstants.CENTER);

                // Tô màu cột "Kho" theo trạng thái
                if (column == 5 && !isSelected) {
                    String val = value == null ? "" : value.toString();
                    switch (val) {
                        case "Còn hàng" -> setForeground(new Color(0x388E3C));
                        case "Hết hàng" -> setForeground(new Color(0xC62828));
                        default         -> setForeground(Color.BLACK);
                    }
                } else {
                    setForeground(Color.BLACK);
                }
                return c;
            }
        };
        for (int i = 0; i < bang.getColumnCount() - 1; i++) // bỏ cột thao tác
            bang.getColumnModel().getColumn(i).setCellRenderer(altRenderer);

        // Renderer nút Sửa/Xóa — gọn lại, bo tròn hơn
        bang.getColumnModel().getColumn(8).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 8));
                p.setBackground(row % 2 == 0 ? Color.WHITE : new Color(0xF3F0FA));
                JButton sua = makeActionButton("Sửa", new Color(0x6677C8));
                JButton xoa = makeActionButton("Xóa", new Color(0xB83434));
                p.add(sua); p.add(xoa);
                return p;
            }
        });

        bang.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            private final JPanel p    = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 8));
            private final JButton sua = makeActionButton("Sửa", new Color(0x6677C8));
            private final JButton xoa = makeActionButton("Xóa", new Color(0xB83434));
            private int currentRow = -1;
            {
                p.setOpaque(true);
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
                p.setBackground(row % 2 == 0 ? Color.WHITE : new Color(0xF3F0FA));
                return p;
            }
            @Override public Object getCellEditorValue() { return ""; }
        });

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(new Color(0xF8F7FF));
        content.add(new JScrollPane(bang), BorderLayout.CENTER);
        tableCard.add(top,     BorderLayout.NORTH);
        tableCard.add(content, BorderLayout.CENTER);

        content.add(new JScrollPane(bang), BorderLayout.CENTER);
        tableCard.add(top,     BorderLayout.NORTH);
        tableCard.add(content, BorderLayout.CENTER);

        // ── CARD 2: Form thêm / sửa nhân viên ──────────────────────
        JPanel themCard = new JPanel(new BorderLayout());
        themCard.setBackground(new Color(0xF0EFF8));

        btnLuuNV.setVisible(true);
        JPanel formHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 18));
        formHeader.setBackground(new Color(0xF0EFF8));
        formHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0xCCCCCC)));
        JButton btnQuayLai = new JButton("← Quay lại danh sách");
        btnQuayLai.setFont(new Font("Arial", Font.BOLD, 22));
        btnQuayLai.setBackground(new Color(0x9B8EA8)); btnQuayLai.setForeground(Color.WHITE);
        btnQuayLai.setFocusPainted(false); btnQuayLai.setBorderPainted(false);
        btnQuayLai.setPreferredSize(new Dimension(300, 48));
        btnQuayLai.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnQuayLai.addActionListener(e -> {
            nvEditingRow[0] = -1;
            nvMa.setEditable(true); nvTen.setEditable(true);
            nvNgaySinh.setEditable(true); nvChucVu.setEditable(true);
            nvDiaChi.setEditable(true); nvEmail.setEditable(true);
            nvSDT.setEditable(true); nvRbNam.setEnabled(true); nvRbNu.setEnabled(true);
            innerCard.show(panel, CARD_TABLE);
        });
        formHeader.add(btnQuayLai);
        themCard.add(formHeader, BorderLayout.NORTH);

        JPanel form = new JPanel(null);
        form.setBackground(new Color(0xF0EFF8));
        Font lf = new Font("Arial", Font.BOLD, 20);

        JLabel lbMaNV = new JLabel("Mã chấm công"); lbMaNV.setFont(lf); lbMaNV.setBounds(40, 40, 200, 30);
        nvMa.setBounds(240, 35, 320, 42);

        JLabel lbTenNV = new JLabel("Họ tên"); lbTenNV.setFont(lf); lbTenNV.setBounds(40, 110, 200, 30);
        nvTen.setBounds(240, 105, 320, 42);

        JLabel lbGTNV = new JLabel("Giới tính"); lbGTNV.setFont(lf); lbGTNV.setBounds(40, 180, 200, 30);
        Font rfNV = new Font("Arial", Font.PLAIN, 20);
        nvRbNam.setFont(rfNV); nvRbNu.setFont(rfNV);
        nvRbNam.setBackground(new Color(0xF0EFF8)); nvRbNu.setBackground(new Color(0xF0EFF8));
        nvRbNam.setBounds(240, 175, 90, 42); nvRbNu.setBounds(340, 175, 80, 42);

        JLabel lbNSNV = new JLabel("Ngày sinh"); lbNSNV.setFont(lf); lbNSNV.setBounds(40, 250, 200, 30);
        nvNgaySinh.setBounds(240, 245, 320, 42);
        JLabel lbNSHintNV = new JLabel("dd/MM/yyyy");
        lbNSHintNV.setFont(new Font("Arial", Font.ITALIC, 16)); lbNSHintNV.setForeground(new Color(0x888888));
        lbNSHintNV.setBounds(570, 253, 120, 30);

        JLabel lbCVNV = new JLabel("Chức vụ"); lbCVNV.setFont(lf); lbCVNV.setBounds(40, 320, 200, 30);
        nvChucVu.setBounds(240, 315, 320, 42);
        JLabel lbCVHint = new JLabel(" Nhân viên");
        lbCVHint.setFont(new Font("Arial", Font.ITALIC, 16)); lbCVHint.setForeground(new Color(0x888888));
        lbCVHint.setBounds(570, 323, 200, 30);

        JLabel lbDCNV = new JLabel("Địa chỉ"); lbDCNV.setFont(lf); lbDCNV.setBounds(720, 40, 180, 30);
        nvDiaChi.setBounds(890, 35, 320, 42);

        JLabel lbEmailNV = new JLabel("Email"); lbEmailNV.setFont(lf); lbEmailNV.setBounds(720, 110, 180, 30);
        nvEmail.setBounds(890, 105, 320, 42);

        JLabel lbSDTNV = new JLabel("SĐT"); lbSDTNV.setFont(lf); lbSDTNV.setBounds(720, 180, 180, 30);
        nvSDT.setBounds(890, 175, 320, 42);

        form.add(lbMaNV);  form.add(nvMa);
        form.add(lbTenNV); form.add(nvTen);
        form.add(lbGTNV);  form.add(nvRbNam); form.add(nvRbNu);
        form.add(lbNSNV);  form.add(nvNgaySinh); form.add(lbNSHintNV);
        form.add(lbCVNV);  form.add(nvChucVu);   form.add(lbCVHint);
        form.add(lbDCNV);  form.add(nvDiaChi);
        form.add(lbEmailNV); form.add(nvEmail);
        form.add(lbSDTNV); form.add(nvSDT);
        themCard.add(form, BorderLayout.CENTER);

        JPanel formFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 16));
        formFooter.setBackground(new Color(0xF0EFF8));
        formFooter.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0xCCCCCC)));
        formFooter.add(btnLuuNV);
        themCard.add(formFooter, BorderLayout.SOUTH);

        btnLuuNV.addActionListener(e -> {
            String ma    = nvMa.getText().trim();
            String ten   = nvTen.getText().trim();
            String gt    = nvRbNam.isSelected() ? "Nam" : "Nữ";
            String ns    = nvNgaySinh.getText().trim();
            String cv    = nvChucVu.getText().trim();
            String dc    = nvDiaChi.getText().trim();
            String email = nvEmail.getText().trim();
            String sdt   = nvSDT.getText().trim();
            if (ma.isEmpty() || ten.isEmpty() || ns.isEmpty() || sdt.isEmpty()) {
                JOptionPane.showMessageDialog(themCard,
                    "Vui lòng nhập đủ các trường bắt buộc:\nMã, Họ tên, Ngày sinh, SĐT.",
                    "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (nvEditingRow[0] >= 0) {
                model.setValueAt(ma,    nvEditingRow[0], 0);
                model.setValueAt(ten,   nvEditingRow[0], 1);
                model.setValueAt(gt,    nvEditingRow[0], 2);
                model.setValueAt(ns,    nvEditingRow[0], 3);
                model.setValueAt(cv,    nvEditingRow[0], 4);
                model.setValueAt(dc,    nvEditingRow[0], 5);
                model.setValueAt(email, nvEditingRow[0], 6);
                model.setValueAt(sdt,   nvEditingRow[0], 7);
                nvEditingRow[0] = -1;
            } else {
                model.addRow(new Object[]{ ma, ten, gt, ns, cv, dc, email, sdt, "" });
            }
            nvMa.setText(""); nvTen.setText(""); nvRbNam.setSelected(true);
            nvNgaySinh.setText(""); nvChucVu.setText(""); nvDiaChi.setText("");
            nvEmail.setText(""); nvSDT.setText("");
            innerCard.show(panel, CARD_TABLE);
        });

        
        // ── Gán action các nút top bar ──────────────────────────────
        btnThem.addActionListener(e -> {
            nvEditingRow[0] = -1;
            nvMa.setText(""); nvTen.setText(""); nvRbNam.setSelected(true);
            nvNgaySinh.setText(""); nvChucVu.setText(""); nvDiaChi.setText("");
            nvEmail.setText(""); nvSDT.setText("");
            nvMa.setEditable(true); nvTen.setEditable(true);
            nvNgaySinh.setEditable(true); nvChucVu.setEditable(true);
            nvDiaChi.setEditable(true); nvEmail.setEditable(true);
            nvSDT.setEditable(true); nvRbNam.setEnabled(true); nvRbNu.setEnabled(true);
            btnLuuNV.setVisible(true);
            innerCard.show(panel, CARD_THEM);
        });

       

        panel.add(tableCard, CARD_TABLE);
        panel.add(themCard,  CARD_THEM);
        innerCard.show(panel, CARD_TABLE);

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

    private JPanel createPanelUser() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0xF8F7FF));

        JLabel title = new JLabel("Người dùng", SwingConstants.CENTER);
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
