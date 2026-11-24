package com.brocode.view;


import com.brocode.model.Barber;
import com.brocode.model.QueueEntry;
import com.brocode.service.DemoDataStore;
import com.brocode.service.QueueService;
import javax.swing.table.DefaultTableModel;

/** Halaman Dashboard. Edit tampilannya lewat tab Design; data diisi di refresh(). */
public class DashboardPanel extends javax.swing.JPanel implements Refreshable {

    private final DemoDataStore db;
    private final QueueService queueService;

    public DashboardPanel(DemoDataStore db, QueueService queueService) {
        this.db = db;
        this.queueService = queueService;
        initComponents();
        Theme.styleCard(cardTotal);
        Theme.styleCard(cardWaiting);
        Theme.styleCard(cardProgress);
        Theme.styleCard(cardCompleted);
        Theme.styleCard(pnlRecent);
        Theme.styleCard(pnlInsight);
        Theme.styleTable(tblRecent);
        Theme.styleScroll(scrollRecent);
        refresh();
    }

    @Override
    public void refresh() {
        long waiting = count("Waiting");
        lblTotalValue.setText(String.valueOf(db.queues.size()));
        lblWaitingValue.setText(String.valueOf(waiting));
        lblProgressValue.setText(String.valueOf(count("In Progress")));
        lblCompletedValue.setText(String.valueOf(count("Completed")));

        DefaultTableModel m = (DefaultTableModel) tblRecent.getModel();
        m.setRowCount(0);
        for (QueueEntry q : db.queues) {
            m.addRow(new Object[]{q.getNumber(), q.getCustomer().getName(), q.getService().getName(),
                q.getBarber() == null ? "-" : q.getBarber().getName(), q.getStatus()});
        }

        Barber rec = queueService.recommendBarber();
        lblRecommended.setText(info("Recommended barber", rec == null ? "No active barber" : rec.getName()));
        lblActiveBarbers.setText(info("Active barbers", String.valueOf(db.barbers.stream().filter(Barber::isActive).count())));
        lblWaitingCustomers.setText(info("Waiting customers", String.valueOf(waiting)));
        lblNote.setText("<html><div style='width:230px'>ETA merupakan estimasi berdasarkan beban antrean, bukan jaminan waktu selesai.</div></html>");
    }

    private long count(String status) {
        return db.queues.stream().filter(q -> q.getStatus().equals(status)).count();
    }

    private String info(String title, String value) {
        return "<html><span style='color:#aaa092'>" + title + "</span><br><b style='font-size:18px;color:#f2ebdc'>" + value + "</b></html>";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlStats = new javax.swing.JPanel();
        cardTotal = new javax.swing.JPanel();
        lblTotalTitle = new javax.swing.JLabel();
        lblTotalValue = new javax.swing.JLabel();
        lblTotalDesc = new javax.swing.JLabel();
        cardWaiting = new javax.swing.JPanel();
        lblWaitingTitle = new javax.swing.JLabel();
        lblWaitingValue = new javax.swing.JLabel();
        lblWaitingDesc = new javax.swing.JLabel();
        cardProgress = new javax.swing.JPanel();
        lblProgressTitle = new javax.swing.JLabel();
        lblProgressValue = new javax.swing.JLabel();
        lblProgressDesc = new javax.swing.JLabel();
        cardCompleted = new javax.swing.JPanel();
        lblCompletedTitle = new javax.swing.JLabel();
        lblCompletedValue = new javax.swing.JLabel();
        lblCompletedDesc = new javax.swing.JLabel();
        pnlLower = new javax.swing.JPanel();
        pnlRecent = new javax.swing.JPanel();
        lblRecentTitle = new javax.swing.JLabel();
        scrollRecent = new javax.swing.JScrollPane();
        tblRecent = new javax.swing.JTable();
        pnlInsight = new javax.swing.JPanel();
        lblInsightTitle = new javax.swing.JLabel();
        lblRecommended = new javax.swing.JLabel();
        lblActiveBarbers = new javax.swing.JLabel();
        lblWaitingCustomers = new javax.swing.JLabel();
        lblNote = new javax.swing.JLabel();

        setBackground(new java.awt.Color(22, 24, 27));
        setLayout(new java.awt.BorderLayout(0, 20));
        pnlStats.setOpaque(false);
        pnlStats.setLayout(new java.awt.GridLayout(1, 4, 14, 0));
        cardTotal.setBackground(new java.awt.Color(30, 33, 38));
        cardTotal.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        lblTotalTitle.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblTotalTitle.setForeground(new java.awt.Color(160, 166, 174));
        lblTotalTitle.setText("TOTAL QUEUE");

        lblTotalValue.setFont(new java.awt.Font("SansSerif", 1, 32));
        lblTotalValue.setForeground(new java.awt.Color(190, 132, 82));
        lblTotalValue.setText("0");

        lblTotalDesc.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblTotalDesc.setForeground(new java.awt.Color(160, 166, 174));
        lblTotalDesc.setText("Today's registrations");

        javax.swing.GroupLayout cardTotalLayout = new javax.swing.GroupLayout(cardTotal);
        cardTotal.setLayout(cardTotalLayout);
        cardTotalLayout.setHorizontalGroup(
            cardTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblTotalTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotalValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotalDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cardTotalLayout.setVerticalGroup(
            cardTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTotalLayout.createSequentialGroup()
                .addComponent(lblTotalTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(lblTotalValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblTotalDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlStats.add(cardTotal);

        cardWaiting.setBackground(new java.awt.Color(30, 33, 38));
        cardWaiting.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        lblWaitingTitle.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblWaitingTitle.setForeground(new java.awt.Color(160, 166, 174));
        lblWaitingTitle.setText("WAITING");

        lblWaitingValue.setFont(new java.awt.Font("SansSerif", 1, 32));
        lblWaitingValue.setForeground(new java.awt.Color(190, 132, 82));
        lblWaitingValue.setText("0");

        lblWaitingDesc.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblWaitingDesc.setForeground(new java.awt.Color(160, 166, 174));
        lblWaitingDesc.setText("Menunggu barber");

        javax.swing.GroupLayout cardWaitingLayout = new javax.swing.GroupLayout(cardWaiting);
        cardWaiting.setLayout(cardWaitingLayout);
        cardWaitingLayout.setHorizontalGroup(
            cardWaitingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardWaitingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblWaitingTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblWaitingValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblWaitingDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cardWaitingLayout.setVerticalGroup(
            cardWaitingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardWaitingLayout.createSequentialGroup()
                .addComponent(lblWaitingTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(lblWaitingValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblWaitingDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlStats.add(cardWaiting);

        cardProgress.setBackground(new java.awt.Color(30, 33, 38));
        cardProgress.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        lblProgressTitle.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblProgressTitle.setForeground(new java.awt.Color(160, 166, 174));
        lblProgressTitle.setText("IN PROGRESS");

        lblProgressValue.setFont(new java.awt.Font("SansSerif", 1, 32));
        lblProgressValue.setForeground(new java.awt.Color(190, 132, 82));
        lblProgressValue.setText("0");

        lblProgressDesc.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblProgressDesc.setForeground(new java.awt.Color(160, 166, 174));
        lblProgressDesc.setText("Sedang dilayani");

        javax.swing.GroupLayout cardProgressLayout = new javax.swing.GroupLayout(cardProgress);
        cardProgress.setLayout(cardProgressLayout);
        cardProgressLayout.setHorizontalGroup(
            cardProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblProgressTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblProgressValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblProgressDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cardProgressLayout.setVerticalGroup(
            cardProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardProgressLayout.createSequentialGroup()
                .addComponent(lblProgressTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(lblProgressValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblProgressDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlStats.add(cardProgress);

        cardCompleted.setBackground(new java.awt.Color(30, 33, 38));
        cardCompleted.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        lblCompletedTitle.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblCompletedTitle.setForeground(new java.awt.Color(160, 166, 174));
        lblCompletedTitle.setText("COMPLETED");

        lblCompletedValue.setFont(new java.awt.Font("SansSerif", 1, 32));
        lblCompletedValue.setForeground(new java.awt.Color(190, 132, 82));
        lblCompletedValue.setText("0");

        lblCompletedDesc.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblCompletedDesc.setForeground(new java.awt.Color(160, 166, 174));
        lblCompletedDesc.setText("Layanan selesai");

        javax.swing.GroupLayout cardCompletedLayout = new javax.swing.GroupLayout(cardCompleted);
        cardCompleted.setLayout(cardCompletedLayout);
        cardCompletedLayout.setHorizontalGroup(
            cardCompletedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardCompletedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblCompletedTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCompletedValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCompletedDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cardCompletedLayout.setVerticalGroup(
            cardCompletedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardCompletedLayout.createSequentialGroup()
                .addComponent(lblCompletedTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(lblCompletedValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblCompletedDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlStats.add(cardCompleted);

        add(pnlStats, java.awt.BorderLayout.NORTH);

        pnlLower.setOpaque(false);
        pnlLower.setLayout(new java.awt.GridLayout(1, 2, 14, 0));
        pnlRecent.setBackground(new java.awt.Color(30, 33, 38));
        pnlRecent.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        pnlRecent.setLayout(new java.awt.BorderLayout(0, 10));
        lblRecentTitle.setFont(new java.awt.Font("SansSerif", 1, 16));
        lblRecentTitle.setForeground(new java.awt.Color(213, 166, 111));
        lblRecentTitle.setText("Recent Queue");
        pnlRecent.add(lblRecentTitle, java.awt.BorderLayout.NORTH);

        scrollRecent.setBackground(new java.awt.Color(30, 33, 38));
        tblRecent.setBackground(new java.awt.Color(30, 33, 38));
        tblRecent.setForeground(new java.awt.Color(239, 237, 232));
        tblRecent.setGridColor(new java.awt.Color(57, 62, 70));
        tblRecent.setRowHeight(38);
        tblRecent.setSelectionBackground(new java.awt.Color(91, 58, 39));
        tblRecent.setSelectionForeground(new java.awt.Color(239, 237, 232));
        tblRecent.setFillsViewportHeight(true);
        tblRecent.setShowVerticalLines(false);
        tblRecent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Queue No.", "Customer", "Service", "Barber", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollRecent.setViewportView(tblRecent);

        pnlRecent.add(scrollRecent, java.awt.BorderLayout.CENTER);

        pnlLower.add(pnlRecent);

        pnlInsight.setBackground(new java.awt.Color(30, 33, 38));
        pnlInsight.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        lblInsightTitle.setFont(new java.awt.Font("SansSerif", 1, 16));
        lblInsightTitle.setForeground(new java.awt.Color(213, 166, 111));
        lblInsightTitle.setText("Operational Snapshot");

        lblRecommended.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblRecommended.setForeground(new java.awt.Color(239, 237, 232));
        lblRecommended.setText("Recommended barber");

        lblActiveBarbers.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblActiveBarbers.setForeground(new java.awt.Color(239, 237, 232));
        lblActiveBarbers.setText("Active barbers");

        lblWaitingCustomers.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblWaitingCustomers.setForeground(new java.awt.Color(239, 237, 232));
        lblWaitingCustomers.setText("Waiting customers");

        lblNote.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblNote.setForeground(new java.awt.Color(160, 166, 174));
        lblNote.setText("ETA merupakan estimasi berdasarkan beban antrean, bukan jaminan waktu selesai.");

        javax.swing.GroupLayout pnlInsightLayout = new javax.swing.GroupLayout(pnlInsight);
        pnlInsight.setLayout(pnlInsightLayout);
        pnlInsightLayout.setHorizontalGroup(
            pnlInsightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInsightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblInsightTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblRecommended, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblActiveBarbers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblWaitingCustomers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlInsightLayout.setVerticalGroup(
            pnlInsightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInsightLayout.createSequentialGroup()
                .addComponent(lblInsightTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblRecommended, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblActiveBarbers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblWaitingCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlLower.add(pnlInsight);

        add(pnlLower, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cardCompleted;
    private javax.swing.JPanel cardProgress;
    private javax.swing.JPanel cardTotal;
    private javax.swing.JPanel cardWaiting;
    private javax.swing.JLabel lblActiveBarbers;
    private javax.swing.JLabel lblCompletedDesc;
    private javax.swing.JLabel lblCompletedTitle;
    private javax.swing.JLabel lblCompletedValue;
    private javax.swing.JLabel lblInsightTitle;
    private javax.swing.JLabel lblNote;
    private javax.swing.JLabel lblProgressDesc;
    private javax.swing.JLabel lblProgressTitle;
    private javax.swing.JLabel lblProgressValue;
    private javax.swing.JLabel lblRecentTitle;
    private javax.swing.JLabel lblRecommended;
    private javax.swing.JLabel lblTotalDesc;
    private javax.swing.JLabel lblTotalTitle;
    private javax.swing.JLabel lblTotalValue;
    private javax.swing.JLabel lblWaitingCustomers;
    private javax.swing.JLabel lblWaitingDesc;
    private javax.swing.JLabel lblWaitingTitle;
    private javax.swing.JLabel lblWaitingValue;
    private javax.swing.JPanel pnlInsight;
    private javax.swing.JPanel pnlLower;
    private javax.swing.JPanel pnlRecent;
    private javax.swing.JPanel pnlStats;
    private javax.swing.JScrollPane scrollRecent;
    private javax.swing.JTable tblRecent;
    // End of variables declaration//GEN-END:variables
}
