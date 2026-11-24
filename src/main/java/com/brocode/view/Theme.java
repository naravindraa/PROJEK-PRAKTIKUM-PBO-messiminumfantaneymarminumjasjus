package com.brocode.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.*;
import javax.swing.table.*;

/** Centralized visual system. All Swing screens and dialogs use this palette. */
public final class Theme {

    // ── Palette ───────────────────────────────────────────────────────────────
    public static final Color BG          = new Color(22, 24, 27);
    public static final Color SIDEBAR     = new Color(17, 19, 22);
    public static final Color PANEL       = new Color(30, 33, 38);
    public static final Color PANEL2      = new Color(39, 43, 49);
    public static final Color PANEL_HOVER = new Color(48, 52, 59);
    public static final Color BORDER      = new Color(57, 62, 70);
    public static final Color ACCENT      = new Color(190, 132, 82);
    public static final Color ACCENT_DARK = new Color(91, 58, 39);
    public static final Color RED         = new Color(157, 76, 64);
    public static final Color GOLD        = new Color(213, 166, 111);
    public static final Color TEXT        = new Color(239, 237, 232);
    public static final Color MUTED       = new Color(160, 166, 174);
    public static final Color GREEN       = new Color(104, 169, 133);
    public static final Color BLUE        = new Color(113, 154, 190);

    public static final Color STATUS_WAITING   = new Color(190, 132, 82);
    public static final Color STATUS_PROGRESS  = new Color(113, 154, 190);
    public static final Color STATUS_COMPLETED = new Color(104, 169, 133);
    public static final Color STATUS_CANCELLED = new Color(157, 76,  64);

    // Table alternating row colors
    private static final Color ROW_ODD  = PANEL;
    private static final Color ROW_EVEN = new Color(26, 29, 34);
    private static final Color ROW_SEP  = new Color(44, 48, 56);

    private Theme() {}

    // ── UIManager defaults ────────────────────────────────────────────────────
    public static void configure() {
        UIManager.put("Panel.background",             BG);
        UIManager.put("OptionPane.background",        PANEL);
        UIManager.put("OptionPane.messageForeground", TEXT);
        UIManager.put("OptionPane.foreground",        TEXT);
        UIManager.put("Button.background",            PANEL2);
        UIManager.put("Button.foreground",            TEXT);
        UIManager.put("Button.select",                PANEL_HOVER);
        UIManager.put("Label.foreground",             TEXT);
        UIManager.put("TextField.background",         PANEL2);
        UIManager.put("TextField.foreground",         TEXT);
        UIManager.put("TextField.caretForeground",    TEXT);
        UIManager.put("PasswordField.background",     PANEL2);
        UIManager.put("PasswordField.foreground",     TEXT);
        UIManager.put("PasswordField.caretForeground",TEXT);
        UIManager.put("ComboBox.background",          PANEL2);
        UIManager.put("ComboBox.foreground",          TEXT);
        UIManager.put("ComboBox.selectionBackground", ACCENT_DARK);
        UIManager.put("ComboBox.selectionForeground", TEXT);
        UIManager.put("CheckBox.background",          PANEL);
        UIManager.put("CheckBox.foreground",          TEXT);
        UIManager.put("Table.background",             PANEL);
        UIManager.put("Table.foreground",             TEXT);
        UIManager.put("Table.selectionBackground",    ACCENT_DARK);
        UIManager.put("Table.selectionForeground",    TEXT);
        UIManager.put("TableHeader.background",       PANEL2);
        UIManager.put("TableHeader.foreground",       GOLD);
        UIManager.put("ScrollPane.background",        PANEL);
        UIManager.put("Viewport.background",          PANEL);
        UIManager.put("ProgressBar.background",       PANEL2);
        UIManager.put("ProgressBar.foreground",       ACCENT);
        UIManager.put("ToolTip.background",           PANEL2);
        UIManager.put("ToolTip.foreground",           TEXT);
        UIManager.put("ToolTip.border",               new LineBorder(BORDER));
        UIManager.put("Separator.foreground",         BORDER);
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  BUTTONS — rounded corners + hover
    // ══════════════════════════════════════════════════════════════════════════

    public static void styleButton(JButton b, boolean primary) {
        Color normalBg  = primary ? ACCENT_DARK : PANEL2;
        Color hoverBg   = primary ? new Color(112, 74, 50) : PANEL_HOVER;
        Color pressedBg = primary ? new Color(70, 44, 28)  : new Color(55, 60, 68);
        Color borderCol = primary ? ACCENT : BORDER;

        b.setUI(new RoundedButtonUI());
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setForeground(TEXT);
        b.setBackground(normalBg);
        b.setFont(new Font("SansSerif", Font.BOLD, 12));
        b.setBorder(new EmptyBorder(9, 16, 9, 16));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.putClientProperty("btn.borderColor", borderCol);
        b.putClientProperty("btn.hoverBg", hoverBg);
        b.putClientProperty("btn.normalBg", normalBg);
        b.putClientProperty("btn.pressedBg", pressedBg);

        // Remove stale hover listeners, add fresh ones
        for (MouseListener ml : b.getMouseListeners()) {
            if (ml instanceof BtnHoverListener) b.removeMouseListener(ml);
        }
        b.addMouseListener(new BtnHoverListener());
    }

    private static class BtnHoverListener extends MouseAdapter {
        @Override public void mouseEntered(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            b.setBackground((Color) b.getClientProperty("btn.hoverBg"));
            b.repaint();
        }
        @Override public void mouseExited(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            b.setBackground((Color) b.getClientProperty("btn.normalBg"));
            b.repaint();
        }
        @Override public void mousePressed(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            b.setBackground((Color) b.getClientProperty("btn.pressedBg"));
            b.repaint();
        }
        @Override public void mouseReleased(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            boolean inside = b.getBounds().contains(SwingUtilities.convertPoint(b, e.getPoint(), b.getParent()));
            b.setBackground((Color) b.getClientProperty(inside ? "btn.hoverBg" : "btn.normalBg"));
            b.repaint();
        }
    }

    /** Paints a rounded-rectangle background + border for action buttons. */
    private static class RoundedButtonUI extends BasicButtonUI {
        private static final int ARC = 10;

        @Override public void installUI(JComponent c) {
            super.installUI(c);
            c.setOpaque(false);
        }

        @Override public void paint(Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = c.getWidth(), h = c.getHeight();
            // Fill
            g2.setColor(b.getBackground());
            g2.fill(new RoundRectangle2D.Float(1, 1, w - 2, h - 2, ARC, ARC));
            // Border line
            Object bc = b.getClientProperty("btn.borderColor");
            g2.setColor(bc instanceof Color ? (Color) bc : BORDER);
            g2.setStroke(new BasicStroke(1.2f));
            g2.draw(new RoundRectangle2D.Float(0.5f, 0.5f, w - 1, h - 1, ARC, ARC));
            g2.dispose();
            super.paint(g, c);
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  SIDEBAR NAV BUTTONS — hover glow, left accent bar when selected
    // ══════════════════════════════════════════════════════════════════════════

    public static void styleNavButton(JButton b, boolean selected) {
        Color selBg    = new Color(50, 38, 28);
        Color normalBg = SIDEBAR;
        Color hoverBg  = new Color(30, 33, 38);

        b.setUI(new NavButtonUI(selected));
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setForeground(selected ? GOLD : MUTED);
        b.setBackground(selected ? selBg : normalBg);
        b.setFont(new Font("SansSerif", Font.BOLD, 13));
        b.setBorder(new EmptyBorder(13, 26, 13, 10));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.putClientProperty("nav.selected", selected);
        b.putClientProperty("nav.selBg",    selBg);
        b.putClientProperty("nav.normalBg", normalBg);
        b.putClientProperty("nav.hoverBg",  hoverBg);

        for (MouseListener ml : b.getMouseListeners()) {
            if (ml instanceof NavHoverListener) b.removeMouseListener(ml);
        }
        b.addMouseListener(new NavHoverListener());
    }

    private static class NavHoverListener extends MouseAdapter {
        @Override public void mouseEntered(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            if (!(Boolean) b.getClientProperty("nav.selected")) {
                b.setBackground((Color) b.getClientProperty("nav.hoverBg"));
                b.setForeground(TEXT);
                b.repaint();
            }
        }
        @Override public void mouseExited(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            if (!(Boolean) b.getClientProperty("nav.selected")) {
                b.setBackground((Color) b.getClientProperty("nav.normalBg"));
                b.setForeground(MUTED);
                b.repaint();
            }
        }
    }

    /** Paints sidebar nav button with a copper accent bar on the left when selected. */
    private static class NavButtonUI extends BasicButtonUI {
        private final boolean selected;
        NavButtonUI(boolean s) { selected = s; }

        @Override public void installUI(JComponent c) {
            super.installUI(c);
            c.setOpaque(false);
        }

        @Override public void paint(Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = c.getWidth(), h = c.getHeight();
            // Background
            g2.setColor(b.getBackground());
            g2.fillRect(0, 0, w, h);
            // Left accent bar when selected
            if (selected) {
                g2.setColor(ACCENT);
                g2.fillRoundRect(0, 6, 4, h - 12, 4, 4);
            }
            g2.dispose();
            super.paint(g, c);
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  CARDS — rounded corners
    // ══════════════════════════════════════════════════════════════════════════

    /** Applies rounded card styling (replaces styleCard). */
    public static void makeRoundedCard(JComponent c) {
        c.setOpaque(false);
        c.setBorder(new RoundedCardBorder(BORDER, PANEL, 14, 18));
    }

    /** Legacy styleCard for backward compat — now delegates to makeRoundedCard. */
    public static void styleCard(JComponent c) {
        makeRoundedCard(c);
    }

    public static JPanel card() {
        JPanel p = new JPanel();
        p.setBackground(PANEL);
        makeRoundedCard(p);
        return p;
    }

    private static class RoundedCardBorder extends AbstractBorder {
        private final Color borderColor, fillColor;
        private final int radius, pad;

        RoundedCardBorder(Color border, Color fill, int radius, int pad) {
            this.borderColor = border; this.fillColor = fill;
            this.radius = radius;      this.pad = pad;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // Draw filled rounded background (behind children)
            g2.setColor(fillColor);
            g2.fill(new RoundRectangle2D.Float(x, y, w - 1, h - 1, radius, radius));
            // Draw rounded border line
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(1.1f));
            g2.draw(new RoundRectangle2D.Float(x + 0.5f, y + 0.5f, w - 2, h - 2, radius, radius));
            g2.dispose();
        }

        @Override public Insets getBorderInsets(Component c)                       { return new Insets(pad, pad, pad, pad); }
        @Override public Insets getBorderInsets(Component c, Insets in)            { in.set(pad, pad, pad, pad); return in; }
        @Override public boolean isBorderOpaque()                                   { return false; }
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  TABLES — alternating rows + separator + status badges
    // ══════════════════════════════════════════════════════════════════════════

    public static void styleTable(JTable t) {
        t.setBackground(ROW_ODD);
        t.setForeground(TEXT);
        t.setGridColor(ROW_SEP);
        t.setRowHeight(42);
        t.setFont(new Font("SansSerif", Font.PLAIN, 13));
        t.setSelectionBackground(ACCENT_DARK);
        t.setSelectionForeground(TEXT);
        t.setFillsViewportHeight(true);
        t.setShowVerticalLines(false);
        t.setShowHorizontalLines(false);  // we draw our own via renderer
        t.setIntercellSpacing(new Dimension(0, 0));
        t.getTableHeader().setBackground(PANEL2);
        t.getTableHeader().setForeground(GOLD);
        t.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        t.getTableHeader().setOpaque(true);
        t.getTableHeader().setReorderingAllowed(false);
        t.getTableHeader().setPreferredSize(new Dimension(0, 38));
        t.getTableHeader().setBorder(new MatteBorder(0, 0, 2, 0, ACCENT_DARK));
    }

    /** Apply alternating row colors + separator lines to ALL columns of a table. */
    public static void applyAlternatingRows(JTable table) {
        AlternatingRowRenderer r = new AlternatingRowRenderer();
        table.setDefaultRenderer(Object.class, r);
        table.setDefaultRenderer(String.class, r);
        table.setDefaultRenderer(Integer.class, r);
        table.setDefaultRenderer(Long.class, r);
    }

    /** Apply status badge renderer on the given column index. */
    public static void applyStatusRenderer(JTable table, int statusColumnIndex) {
        table.getColumnModel().getColumn(statusColumnIndex).setCellRenderer(new StatusBadgeRenderer());
    }

    /** Base renderer: alternating row bg + bottom separator line. */
    public static class AlternatingRowRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setFont(new Font("SansSerif", Font.PLAIN, 13));
            if (!isSelected) {
                setBackground(row % 2 == 0 ? ROW_ODD : ROW_EVEN);
                setForeground(TEXT);
            } else {
                setBackground(ACCENT_DARK);
                setForeground(TEXT);
            }
            // Bottom separator — slightly lighter on even rows, darker on odd
            setBorder(new MatteBorder(0, 0, 1, 0, ROW_SEP));
            return this;
        }
    }

    /** Status badge renderer — extends AlternatingRowRenderer for consistent row bg. */
    public static class StatusBadgeRenderer extends AlternatingRowRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(new Font("SansSerif", Font.BOLD, 11));
            if (!isSelected) {
                String status = value == null ? "" : value.toString();
                switch (status) {
                    case "Waiting"     -> { setForeground(STATUS_WAITING);   setBackground(new Color(58, 40, 22)); }
                    case "In Progress" -> { setForeground(STATUS_PROGRESS);  setBackground(new Color(25, 38, 52)); }
                    case "Completed"   -> { setForeground(STATUS_COMPLETED); setBackground(new Color(24, 42, 33)); }
                    case "Cancelled"   -> { setForeground(STATUS_CANCELLED); setBackground(new Color(44, 22, 20)); }
                    default            -> { setForeground(MUTED); }
                }
            }
            setBorder(new MatteBorder(0, 0, 1, 0, ROW_SEP));
            return this;
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  SCROLLPANE + SCROLLBAR
    // ══════════════════════════════════════════════════════════════════════════

    public static void styleScroll(JScrollPane sp) {
        sp.getViewport().setBackground(ROW_ODD);
        sp.setBackground(ROW_ODD);
        sp.setBorder(new LineBorder(BORDER));
        sp.getVerticalScrollBar().setUI(new DarkScrollBarUI());
        sp.getHorizontalScrollBar().setUI(new DarkScrollBarUI());
        sp.getVerticalScrollBar().setUnitIncrement(18);
    }

    public static JScrollPane darkScroll(JTable table) {
        JScrollPane sp = new JScrollPane(table);
        styleScroll(sp);
        return sp;
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  FIELDS, COMBO, CHECKBOX
    // ══════════════════════════════════════════════════════════════════════════

    public static void styleField(JTextField f) {
        f.setOpaque(true);
        f.setBackground(PANEL2); f.setForeground(TEXT); f.setCaretColor(TEXT);
        f.setSelectionColor(ACCENT_DARK); f.setSelectedTextColor(TEXT);
        f.setFont(new Font("SansSerif", Font.PLAIN, 13));
        f.setBorder(new CompoundBorder(
            new RoundedFieldBorder(BORDER, 8),
            new EmptyBorder(8, 10, 8, 10)
        ));
    }

    private static class RoundedFieldBorder extends AbstractBorder {
        private final Color color;
        private final int arc;
        RoundedFieldBorder(Color c, int arc) { this.color = c; this.arc = arc; }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(1.2f));
            g2.draw(new RoundRectangle2D.Float(x + 0.5f, y + 0.5f, w - 2, h - 2, arc, arc));
            g2.dispose();
        }

        @Override public Insets getBorderInsets(Component c)            { return new Insets(1, 1, 1, 1); }
        @Override public Insets getBorderInsets(Component c, Insets in) { in.set(1,1,1,1); return in; }
    }

    public static void styleCombo(JComboBox<?> combo) {
        combo.setBackground(PANEL2); combo.setForeground(TEXT); combo.setOpaque(true);
        combo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        combo.setBorder(new LineBorder(BORDER));
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean selected, boolean focus) {
                JLabel c = (JLabel) super.getListCellRendererComponent(list, value, index, selected, focus);
                c.setOpaque(true);
                c.setBackground(selected ? ACCENT_DARK : PANEL2);
                c.setForeground(TEXT);
                c.setBorder(new EmptyBorder(6, 10, 6, 10));
                return c;
            }
        });
    }

    public static void styleCheckBox(JCheckBox c) {
        c.setOpaque(true); c.setBackground(PANEL); c.setForeground(TEXT);
        c.setFont(new Font("SansSerif", Font.PLAIN, 13));
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  PROGRESS BAR — smooth rounded
    // ══════════════════════════════════════════════════════════════════════════

    public static void styleProgressBar(JProgressBar bar) {
        bar.setUI(new SmoothProgressBarUI());
        bar.setOpaque(true); bar.setBorderPainted(false);
        bar.setBackground(PANEL2); bar.setForeground(ACCENT);
        bar.setStringPainted(true); bar.setFont(new Font("SansSerif", Font.BOLD, 11));
    }

    private static class SmoothProgressBarUI extends BasicProgressBarUI {
        @Override protected Color getSelectionBackground() { return TEXT; }
        @Override protected Color getSelectionForeground() { return TEXT; }

        @Override public void paint(Graphics g, JComponent c) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Insets b = progressBar.getInsets();
            int w = progressBar.getWidth()  - b.left - b.right;
            int h = progressBar.getHeight() - b.top  - b.bottom;
            int arc = h;
            g2.setColor(progressBar.getBackground());
            g2.fill(new RoundRectangle2D.Float(b.left, b.top, w, h, arc, arc));
            int filled = (int) (w * progressBar.getPercentComplete());
            if (filled > 0) {
                g2.setColor(progressBar.getForeground());
                g2.fill(new RoundRectangle2D.Float(b.left, b.top, filled, h, arc, arc));
            }
            if (progressBar.isStringPainted()) {
                g2.setFont(progressBar.getFont());
                g2.setColor(TEXT);
                FontMetrics fm = g2.getFontMetrics();
                String s = progressBar.getString();
                g2.drawString(s, b.left + (w - fm.stringWidth(s)) / 2,
                              b.top + (h + fm.getAscent() - fm.getDescent()) / 2);
            }
            g2.dispose();
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  LABEL HELPERS
    // ══════════════════════════════════════════════════════════════════════════

    public static JLabel title(String s) {
        JLabel l = new JLabel(s); l.setForeground(TEXT);
        l.setFont(new Font("SansSerif", Font.BOLD, 25)); return l;
    }
    public static JLabel muted(String s) {
        JLabel l = new JLabel(s); l.setForeground(MUTED);
        l.setFont(new Font("SansSerif", Font.PLAIN, 12)); return l;
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  ANIMATIONS
    // ══════════════════════════════════════════════════════════════════════════

    /** Animates a JLabel counter from 0 → target with ease-out over ~400 ms. */
    public static void animateCounter(JLabel label, int target) {
        int steps = 22;
        Timer t = new Timer(18, null);
        int[] frame = {0};
        t.addActionListener(e -> {
            frame[0]++;
            int val = (int) Math.round(target * easeOut((double) frame[0] / steps));
            label.setText(String.valueOf(val));
            if (frame[0] >= steps) {
                label.setText(String.valueOf(target));
                ((Timer) e.getSource()).stop();
            }
        });
        t.start();
    }
    private static double easeOut(double t) { return 1 - Math.pow(1 - t, 3); }

    /** Triggers a quick repaint fade-in on the component. */
    public static void fadeIn(JComponent comp) {
        comp.putClientProperty("alpha", 0.0f);
        Timer t = new Timer(16, null);
        t.addActionListener(e -> {
            float alpha = (float) comp.getClientProperty("alpha");
            alpha = Math.min(1.0f, alpha + 0.1f);
            comp.putClientProperty("alpha", alpha);
            comp.repaint();
            if (alpha >= 1.0f) ((Timer) e.getSource()).stop();
        });
        t.start();
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  DARK SCROLLBAR
    // ══════════════════════════════════════════════════════════════════════════

    private static class DarkScrollBarUI extends BasicScrollBarUI {
        @Override protected void configureScrollBarColors() {
            thumbColor = new Color(72, 78, 88);
            trackColor = BG;
        }
        @Override protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
            if (r.isEmpty() || !scrollbar.isEnabled()) return;
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(thumbColor);
            int arc = Math.min(r.width, r.height);
            g2.fillRoundRect(r.x + 2, r.y + 2, r.width - 4, r.height - 4, arc, arc);
            g2.dispose();
        }
        @Override protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
            g.setColor(trackColor); g.fillRect(r.x, r.y, r.width, r.height);
        }
        @Override protected JButton createDecreaseButton(int o) { return zeroBtn(); }
        @Override protected JButton createIncreaseButton(int o) { return zeroBtn(); }
        private JButton zeroBtn() {
            JButton b = new JButton();
            b.setPreferredSize(new Dimension(0, 0));
            b.setMinimumSize(new Dimension(0, 0));
            b.setMaximumSize(new Dimension(0, 0));
            return b;
        }
    }
}
