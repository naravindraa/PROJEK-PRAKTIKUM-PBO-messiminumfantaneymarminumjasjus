package com.brocode.view;


import com.brocode.service.DemoDataStore;
import com.brocode.service.QueueService;

/**
 * Jendela utama: sidebar + area halaman (CardLayout).
 * Tiap halaman adalah JPanel form sendiri: DashboardPanel, CustomersPanel, ServicesPanel,
 * BarbersPanel, QueuePanel, AnalyticsPanel, HistoryPanel (buka masing-masing di tab Design).
 */
public class MainFrame extends javax.swing.JFrame {

    private final DemoDataStore db = new DemoDataStore();
    private final QueueService queueService = new QueueService(db);
    private final java.util.Map<String, javax.swing.JButton> navigationButtons = new java.util.LinkedHashMap<>();
    private final java.util.Map<String, Refreshable> pageViews = new java.util.HashMap<>();

    public MainFrame() {
        initComponents();
        getContentPane().setBackground(Theme.BG);
        Theme.styleButton(btnLogout, false);

        navigationButtons.put("Dashboard", btnDashboard);
        navigationButtons.put("Customers", btnCustomers);
        navigationButtons.put("Services", btnServices);
        navigationButtons.put("Barbers", btnBarbers);
        navigationButtons.put("Queue", btnQueue);
        navigationButtons.put("Analytics", btnAnalytics);
        navigationButtons.put("History", btnHistory);

        addPage("Dashboard", new DashboardPanel(db, queueService));
        addPage("Customers", new CustomersPanel(db));
        addPage("Services", new ServicesPanel(db));
        addPage("Barbers", new BarbersPanel(db));
        addPage("Queue", new QueuePanel(db, queueService));
        addPage("Analytics", new AnalyticsPanel(db, queueService));
        addPage("History", new HistoryPanel(db));

        lblHeaderInfo.setText("Brocode Barbershop Jatinangor  \u2022  " + java.time.LocalDate.now()
                + "  \u2022  " + (db.isDatabaseMode() ? "MySQL" : "Demo (in-memory)"));
        setLocationRelativeTo(null);
        showPage("Dashboard");
    }

    private <T extends javax.swing.JPanel & Refreshable> void addPage(String name, T panel) {
        pageViews.put(name, panel);
        pnlPages.add(panel, name);
    }

    private void showPage(String page) {
        navigationButtons.forEach((name, button) -> Theme.styleNavButton(button, name.equals(page)));
        lblPageTitle.setText(page);
        Refreshable view = pageViews.get(page);
        if (view != null) view.refresh();
        ((java.awt.CardLayout) pnlPages.getLayout()).show(pnlPages, page);
        // Fade-in animation on the visible panel
        if (view instanceof javax.swing.JComponent) {
            Theme.fadeIn((javax.swing.JComponent) view);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSidebar = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblTagline = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnCustomers = new javax.swing.JButton();
        btnServices = new javax.swing.JButton();
        btnBarbers = new javax.swing.JButton();
        btnQueue = new javax.swing.JButton();
        btnAnalytics = new javax.swing.JButton();
        btnHistory = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        pnlContent = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        lblPageTitle = new javax.swing.JLabel();
        lblHeaderInfo = new javax.swing.JLabel();
        pnlPages = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Brocode Queue Intelligence System");
        setMinimumSize(new java.awt.Dimension(1050, 680));
        setPreferredSize(new java.awt.Dimension(1280, 800));
        getContentPane().setLayout(new java.awt.BorderLayout());
        pnlSidebar.setBackground(new java.awt.Color(17, 19, 22));
        pnlSidebar.setPreferredSize(new java.awt.Dimension(220, 100));
        lblLogo.setFont(new java.awt.Font("SansSerif", 1, 24));
        lblLogo.setForeground(new java.awt.Color(213, 166, 111));
        lblLogo.setText("BROCODE");
        lblLogo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 22, 0, 0));

        lblTagline.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblTagline.setForeground(new java.awt.Color(160, 166, 174));
        lblTagline.setText("QUEUE INTELLIGENCE");
        lblTagline.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 22, 0, 0));

        btnDashboard.setBackground(new java.awt.Color(17, 19, 22));
        btnDashboard.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnDashboard.setForeground(new java.awt.Color(160, 166, 174));
        btnDashboard.setText("Dashboard");
        btnDashboard.setBorder(javax.swing.BorderFactory.createEmptyBorder(13, 22, 13, 10));
        btnDashboard.setBorderPainted(false);
        btnDashboard.setFocusPainted(false);
        btnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });

        btnCustomers.setBackground(new java.awt.Color(17, 19, 22));
        btnCustomers.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnCustomers.setForeground(new java.awt.Color(160, 166, 174));
        btnCustomers.setText("Customers");
        btnCustomers.setBorder(javax.swing.BorderFactory.createEmptyBorder(13, 22, 13, 10));
        btnCustomers.setBorderPainted(false);
        btnCustomers.setFocusPainted(false);
        btnCustomers.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCustomers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomersActionPerformed(evt);
            }
        });

        btnServices.setBackground(new java.awt.Color(17, 19, 22));
        btnServices.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnServices.setForeground(new java.awt.Color(160, 166, 174));
        btnServices.setText("Services");
        btnServices.setBorder(javax.swing.BorderFactory.createEmptyBorder(13, 22, 13, 10));
        btnServices.setBorderPainted(false);
        btnServices.setFocusPainted(false);
        btnServices.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnServices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServicesActionPerformed(evt);
            }
        });

        btnBarbers.setBackground(new java.awt.Color(17, 19, 22));
        btnBarbers.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnBarbers.setForeground(new java.awt.Color(160, 166, 174));
        btnBarbers.setText("Barbers");
        btnBarbers.setBorder(javax.swing.BorderFactory.createEmptyBorder(13, 22, 13, 10));
        btnBarbers.setBorderPainted(false);
        btnBarbers.setFocusPainted(false);
        btnBarbers.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBarbers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarbersActionPerformed(evt);
            }
        });

        btnQueue.setBackground(new java.awt.Color(17, 19, 22));
        btnQueue.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnQueue.setForeground(new java.awt.Color(160, 166, 174));
        btnQueue.setText("Queue");
        btnQueue.setBorder(javax.swing.BorderFactory.createEmptyBorder(13, 22, 13, 10));
        btnQueue.setBorderPainted(false);
        btnQueue.setFocusPainted(false);
        btnQueue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQueue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQueueActionPerformed(evt);
            }
        });

        btnAnalytics.setBackground(new java.awt.Color(17, 19, 22));
        btnAnalytics.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnAnalytics.setForeground(new java.awt.Color(160, 166, 174));
        btnAnalytics.setText("Analytics");
        btnAnalytics.setBorder(javax.swing.BorderFactory.createEmptyBorder(13, 22, 13, 10));
        btnAnalytics.setBorderPainted(false);
        btnAnalytics.setFocusPainted(false);
        btnAnalytics.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAnalytics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalyticsActionPerformed(evt);
            }
        });

        btnHistory.setBackground(new java.awt.Color(17, 19, 22));
        btnHistory.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnHistory.setForeground(new java.awt.Color(160, 166, 174));
        btnHistory.setText("History");
        btnHistory.setBorder(javax.swing.BorderFactory.createEmptyBorder(13, 22, 13, 10));
        btnHistory.setBorderPainted(false);
        btnHistory.setFocusPainted(false);
        btnHistory.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryActionPerformed(evt);
            }
        });

        btnLogout.setBackground(new java.awt.Color(39, 43, 49));
        btnLogout.setFont(new java.awt.Font("SansSerif", 1, 12));
        btnLogout.setForeground(new java.awt.Color(239, 237, 232));
        btnLogout.setFocusPainted(false);
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSidebarLayout = new javax.swing.GroupLayout(pnlSidebar);
        pnlSidebar.setLayout(pnlSidebarLayout);
        pnlSidebarLayout.setHorizontalGroup(
            pnlSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTagline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCustomers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnServices, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBarbers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnQueue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAnalytics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlSidebarLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(16, 16, 16)))
        );
        pnlSidebarLayout.setVerticalGroup(
            pnlSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSidebarLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lblTagline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnServices, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnBarbers, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnQueue, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnAnalytics, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        getContentPane().add(pnlSidebar, java.awt.BorderLayout.WEST);

        pnlContent.setBackground(new java.awt.Color(22, 24, 27));
        pnlContent.setBorder(javax.swing.BorderFactory.createEmptyBorder(28, 30, 28, 30));
        pnlContent.setLayout(new java.awt.BorderLayout(0, 18));
        pnlHeader.setOpaque(false);
        pnlHeader.setLayout(new java.awt.BorderLayout());
        lblPageTitle.setFont(new java.awt.Font("SansSerif", 1, 25));
        lblPageTitle.setForeground(new java.awt.Color(239, 237, 232));
        lblPageTitle.setText("Dashboard");
        pnlHeader.add(lblPageTitle, java.awt.BorderLayout.WEST);

        lblHeaderInfo.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblHeaderInfo.setForeground(new java.awt.Color(160, 166, 174));
        lblHeaderInfo.setText("Brocode Barbershop Jatinangor");
        pnlHeader.add(lblHeaderInfo, java.awt.BorderLayout.EAST);

        pnlContent.add(pnlHeader, java.awt.BorderLayout.NORTH);

        pnlPages.setOpaque(false);
        pnlPages.setLayout(new java.awt.CardLayout());
        pnlContent.add(pnlPages, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlContent, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        showPage("Dashboard");
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnCustomersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomersActionPerformed
        showPage("Customers");
    }//GEN-LAST:event_btnCustomersActionPerformed

    private void btnServicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServicesActionPerformed
        showPage("Services");
    }//GEN-LAST:event_btnServicesActionPerformed

    private void btnBarbersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarbersActionPerformed
        showPage("Barbers");
    }//GEN-LAST:event_btnBarbersActionPerformed

    private void btnQueueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQueueActionPerformed
        showPage("Queue");
    }//GEN-LAST:event_btnQueueActionPerformed

    private void btnAnalyticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalyticsActionPerformed
        showPage("Analytics");
    }//GEN-LAST:event_btnAnalyticsActionPerformed

    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed
        showPage("History");
    }//GEN-LAST:event_btnHistoryActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        dispose();
        new LoginFrame().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalytics;
    private javax.swing.JButton btnBarbers;
    private javax.swing.JButton btnCustomers;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnHistory;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnQueue;
    private javax.swing.JButton btnServices;
    private javax.swing.JLabel lblHeaderInfo;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPageTitle;
    private javax.swing.JLabel lblTagline;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlPages;
    private javax.swing.JPanel pnlSidebar;
    // End of variables declaration//GEN-END:variables
}
