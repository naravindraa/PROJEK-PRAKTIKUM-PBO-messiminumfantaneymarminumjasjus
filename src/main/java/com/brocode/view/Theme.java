package com.brocode.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.*;

/** Centralized visual system. All Swing screens and dialogs use this palette. */
public final class Theme {
    // Restrained charcoal + copper palette for a premium barbershop identity.
    public static final Color BG = new Color(22, 24, 27);
    public static final Color SIDEBAR = new Color(17, 19, 22);
    public static final Color PANEL = new Color(30, 33, 38);
    public static final Color PANEL2 = new Color(39, 43, 49);
    public static final Color PANEL_HOVER = new Color(48, 52, 59);
    public static final Color BORDER = new Color(57, 62, 70);
    public static final Color ACCENT = new Color(190, 132, 82);
    public static final Color ACCENT_DARK = new Color(91, 58, 39);
    public static final Color RED = new Color(157, 76, 64);
    public static final Color GOLD = new Color(213, 166, 111);
    public static final Color TEXT = new Color(239, 237, 232);
    public static final Color MUTED = new Color(160, 166, 174);
    public static final Color GREEN = new Color(104, 169, 133);
    public static final Color BLUE = new Color(113, 154, 190);

    private Theme() {}

    public static void configure() {
        Color field = PANEL2;
        UIManager.put("Panel.background", BG);
        UIManager.put("OptionPane.background", PANEL);
        UIManager.put("OptionPane.messageForeground", TEXT);
        UIManager.put("OptionPane.foreground", TEXT);
        UIManager.put("Button.background", PANEL2);
        UIManager.put("Button.foreground", TEXT);
        UIManager.put("Button.select", PANEL_HOVER);
        UIManager.put("Label.foreground", TEXT);
        UIManager.put("TextField.background", field);
        UIManager.put("TextField.foreground", TEXT);
        UIManager.put("TextField.caretForeground", TEXT);
        UIManager.put("PasswordField.background", field);
        UIManager.put("PasswordField.foreground", TEXT);
        UIManager.put("PasswordField.caretForeground", TEXT);
        UIManager.put("ComboBox.background", field);
        UIManager.put("ComboBox.foreground", TEXT);
        UIManager.put("ComboBox.selectionBackground", ACCENT_DARK);
        UIManager.put("ComboBox.selectionForeground", TEXT);
        UIManager.put("CheckBox.background", PANEL);
        UIManager.put("CheckBox.foreground", TEXT);
        UIManager.put("Table.background", PANEL);
        UIManager.put("Table.foreground", TEXT);
        UIManager.put("Table.selectionBackground", ACCENT_DARK);
        UIManager.put("Table.selectionForeground", TEXT);
        UIManager.put("TableHeader.background", PANEL2);
        UIManager.put("TableHeader.foreground", GOLD);
        UIManager.put("ScrollPane.background", PANEL);
        UIManager.put("Viewport.background", PANEL);
        UIManager.put("ProgressBar.background", PANEL2);
        UIManager.put("ProgressBar.foreground", ACCENT);
        UIManager.put("ToolTip.background", PANEL2);
        UIManager.put("ToolTip.foreground", TEXT);
        UIManager.put("ToolTip.border", new LineBorder(BORDER));
        UIManager.put("Separator.foreground", BORDER);
    }

    public static void styleButton(JButton b, boolean primary) {
        b.setUI(new BasicButtonUI());
        b.setOpaque(true); b.setContentAreaFilled(true); b.setBorderPainted(true); b.setFocusPainted(false);
        b.setForeground(TEXT);
        b.setBackground(primary ? ACCENT_DARK : PANEL2);
        b.setFont(new Font("SansSerif", Font.BOLD, 12));
        b.setBorder(new CompoundBorder(new LineBorder(primary ? ACCENT : BORDER), new EmptyBorder(9, 14, 9, 14)));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static void styleNavButton(JButton b, boolean selected) {
        b.setUI(new BasicButtonUI()); b.setOpaque(true); b.setContentAreaFilled(true);
        b.setBorderPainted(false); b.setFocusPainted(false); b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setForeground(selected ? GOLD : MUTED);
        b.setBackground(selected ? new Color(55, 43, 35) : SIDEBAR);
        b.setFont(new Font("SansSerif", Font.BOLD, 13));
        b.setBorder(new EmptyBorder(13, 22, 13, 10));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static void styleProgressBar(JProgressBar bar) {
        bar.setUI(new BasicProgressBarUI() {
            @Override protected Color getSelectionBackground() { return TEXT; }
            @Override protected Color getSelectionForeground() { return TEXT; }
        });
        bar.setOpaque(true); bar.setBorderPainted(false);
        bar.setBackground(PANEL2); bar.setForeground(ACCENT);
        bar.setStringPainted(true); bar.setFont(new Font("SansSerif", Font.BOLD, 11));
    }

    public static JLabel title(String s) {
        JLabel l = new JLabel(s); l.setForeground(TEXT); l.setFont(new Font("SansSerif", Font.BOLD, 25)); return l;
    }
    public static JLabel muted(String s) {
        JLabel l = new JLabel(s); l.setForeground(MUTED); l.setFont(new Font("SansSerif", Font.PLAIN, 12)); return l;
    }
    public static JPanel card() {
        JPanel p = new JPanel(); p.setBackground(PANEL);
        p.setBorder(new CompoundBorder(new LineBorder(BORDER), new EmptyBorder(16, 16, 16, 16))); return p;
    }
    /** Tambahkan garis tepi card di luar border bawaan (mis. EmptyBorder dari tab Design). */
    public static void styleCard(JComponent c) {
        Border inner = c.getBorder();
        c.setBorder(inner == null ? new LineBorder(BORDER) : new CompoundBorder(new LineBorder(BORDER), inner));
    }
    /** Styling JScrollPane (viewport, border, scrollbar gelap) tanpa membuat scroll pane baru. */
    public static void styleScroll(JScrollPane sp) {
        sp.getViewport().setBackground(PANEL); sp.setBackground(PANEL);
        sp.setBorder(new LineBorder(BORDER));
        sp.getVerticalScrollBar().setUI(new DarkScrollBarUI());
        sp.getHorizontalScrollBar().setUI(new DarkScrollBarUI());
    }
    public static void styleTable(JTable t) {
        t.setBackground(PANEL); t.setForeground(TEXT); t.setGridColor(BORDER); t.setRowHeight(38);
        t.setFont(new Font("SansSerif", Font.PLAIN, 12));
        t.setSelectionBackground(ACCENT_DARK); t.setSelectionForeground(TEXT);
        t.setFillsViewportHeight(true); t.setShowVerticalLines(false); t.setShowHorizontalLines(true);
        t.getTableHeader().setBackground(PANEL2); t.getTableHeader().setForeground(GOLD);
        t.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        t.getTableHeader().setOpaque(true); t.getTableHeader().setReorderingAllowed(false);
    }
    public static void styleField(JTextField f) {
        f.setOpaque(true); f.setBackground(PANEL2); f.setForeground(TEXT); f.setCaretColor(TEXT);
        f.setSelectionColor(ACCENT_DARK); f.setSelectedTextColor(TEXT);
        f.setBorder(new CompoundBorder(new LineBorder(BORDER), new EmptyBorder(8, 9, 8, 9)));
    }
    public static void styleCombo(JComboBox<?> combo) {
        combo.setBackground(PANEL2); combo.setForeground(TEXT); combo.setOpaque(true);
        combo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        combo.setBorder(new LineBorder(BORDER));
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean selected, boolean focus) {
                JLabel c = (JLabel) super.getListCellRendererComponent(list, value, index, selected, focus);
                c.setOpaque(true); c.setBackground(selected ? ACCENT_DARK : PANEL2); c.setForeground(TEXT);
                c.setBorder(new EmptyBorder(6, 8, 6, 8)); return c;
            }
        });
    }
    public static void styleCheckBox(JCheckBox c) {
        c.setOpaque(true); c.setBackground(PANEL); c.setForeground(TEXT);
        c.setFont(new Font("SansSerif", Font.PLAIN, 12));
    }
    public static JScrollPane darkScroll(JTable table) {
        JScrollPane sp = new JScrollPane(table);
        sp.getViewport().setBackground(PANEL); sp.setBackground(PANEL);
        sp.setBorder(new LineBorder(BORDER));
        sp.getVerticalScrollBar().setUI(new DarkScrollBarUI());
        sp.getHorizontalScrollBar().setUI(new DarkScrollBarUI());
        return sp;
    }
    private static class DarkScrollBarUI extends BasicScrollBarUI {
        @Override protected void configureScrollBarColors() { thumbColor = new Color(83, 88, 96); trackColor = PANEL; }
        @Override protected JButton createDecreaseButton(int orientation) { return zeroButton(); }
        @Override protected JButton createIncreaseButton(int orientation) { return zeroButton(); }
        private JButton zeroButton() { JButton b = new JButton(); b.setPreferredSize(new Dimension(0,0)); b.setMinimumSize(new Dimension(0,0)); b.setMaximumSize(new Dimension(0,0)); return b; }
    }
}
