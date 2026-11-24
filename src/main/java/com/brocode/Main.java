package com.brocode;

import com.brocode.view.LoginFrame;
import com.brocode.view.Theme;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); }
        catch (Exception ignored) {}
        Theme.configure();
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
