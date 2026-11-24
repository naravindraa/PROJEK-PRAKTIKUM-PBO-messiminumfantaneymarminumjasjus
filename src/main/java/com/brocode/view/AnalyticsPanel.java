package com.brocode.view;


import com.brocode.service.DemoDataStore;
import com.brocode.service.QueueService;
import java.util.Map;

/** Halaman Analytics. Batang jam ramai dibuat dinamis di refresh(); kartu & judul diedit lewat Design. */
public class AnalyticsPanel extends javax.swing.JPanel implements Refreshable {

    private final DemoDataStore db;
    private final QueueService queueService;

    public AnalyticsPanel(DemoDataStore db, QueueService queueService) {
        this.db = db;
        this.queueService = queueService;
        initComponents();
        Theme.makeRoundedCard(cardVisits);
        Theme.makeRoundedCard(cardDone);
        Theme.makeRoundedCard(cardCancelled);
        Theme.makeRoundedCard(pnlChart);
        refresh();
    }

    @Override
    public void refresh() {
        // Animated counters
        Theme.animateCounter(lblVisitsValue,    db.queues.size());
        Theme.animateCounter(lblDoneValue,      (int) count("Completed"));
        Theme.animateCounter(lblCancelledValue, (int) count("Cancelled"));

        pnlBars.removeAll();
        Map<Integer, Long> peak = queueService.peakHours();
        long max = peak.values().stream().mapToLong(Long::longValue).max().orElse(1);
        peak.forEach((hour, total) -> {
            javax.swing.JPanel line = new javax.swing.JPanel(new java.awt.BorderLayout(10, 0));
            line.setOpaque(false);
            javax.swing.JLabel l = new javax.swing.JLabel(String.format("%02d:00", hour));
            l.setForeground(Theme.TEXT);
            l.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 12));
            l.setPreferredSize(new java.awt.Dimension(52, 0));
            javax.swing.JProgressBar bar = new javax.swing.JProgressBar(0, (int) max);
            bar.setValue(total.intValue());
            bar.setString(total + " queue");
            Theme.styleProgressBar(bar);
            line.add(l, java.awt.BorderLayout.WEST);
            line.add(bar, java.awt.BorderLayout.CENTER);
            pnlBars.add(line);
        });
        pnlBars.revalidate();
        pnlBars.repaint();
    }

    private long count(String status) {
        return db.queues.stream().filter(q -> q.getStatus().equals(status)).count();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCards = new javax.swing.JPanel();
        cardVisits = new javax.swing.JPanel();
        lblVisitsTitle = new javax.swing.JLabel();
        lblVisitsValue = new javax.swing.JLabel();
        lblVisitsDesc = new javax.swing.JLabel();
        cardDone = new javax.swing.JPanel();
        lblDoneTitle = new javax.swing.JLabel();
        lblDoneValue = new javax.swing.JLabel();
        lblDoneDesc = new javax.swing.JLabel();
        cardCancelled = new javax.swing.JPanel();
        lblCancelledTitle = new javax.swing.JLabel();
        lblCancelledValue = new javax.swing.JLabel();
        lblCancelledDesc = new javax.swing.JLabel();
        pnlChart = new javax.swing.JPanel();
        lblChartTitle = new javax.swing.JLabel();
        pnlBarsHolder = new javax.swing.JPanel();
        pnlBars = new javax.swing.JPanel();
        lblChartNote = new javax.swing.JLabel();

        setBackground(new java.awt.Color(22, 24, 27));
        setLayout(new java.awt.BorderLayout(0, 16));
        pnlCards.setOpaque(false);
        pnlCards.setLayout(new java.awt.GridLayout(1, 3, 12, 0));
        cardVisits.setBackground(new java.awt.Color(30, 33, 38));
        cardVisits.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        lblVisitsTitle.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblVisitsTitle.setForeground(new java.awt.Color(160, 166, 174));
        lblVisitsTitle.setText("TOTAL VISITS");

        lblVisitsValue.setFont(new java.awt.Font("SansSerif", 1, 32));
        lblVisitsValue.setForeground(new java.awt.Color(190, 132, 82));
        lblVisitsValue.setText("0");

        lblVisitsDesc.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblVisitsDesc.setForeground(new java.awt.Color(160, 166, 174));
        lblVisitsDesc.setText("All demo records");

        javax.swing.GroupLayout cardVisitsLayout = new javax.swing.GroupLayout(cardVisits);
        cardVisits.setLayout(cardVisitsLayout);
        cardVisitsLayout.setHorizontalGroup(
            cardVisitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardVisitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblVisitsTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblVisitsValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblVisitsDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cardVisitsLayout.setVerticalGroup(
            cardVisitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardVisitsLayout.createSequentialGroup()
                .addComponent(lblVisitsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(lblVisitsValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblVisitsDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlCards.add(cardVisits);

        cardDone.setBackground(new java.awt.Color(30, 33, 38));
        cardDone.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        lblDoneTitle.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblDoneTitle.setForeground(new java.awt.Color(160, 166, 174));
        lblDoneTitle.setText("COMPLETED");

        lblDoneValue.setFont(new java.awt.Font("SansSerif", 1, 32));
        lblDoneValue.setForeground(new java.awt.Color(190, 132, 82));
        lblDoneValue.setText("0");

        lblDoneDesc.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblDoneDesc.setForeground(new java.awt.Color(160, 166, 174));
        lblDoneDesc.setText("Completed services");

        javax.swing.GroupLayout cardDoneLayout = new javax.swing.GroupLayout(cardDone);
        cardDone.setLayout(cardDoneLayout);
        cardDoneLayout.setHorizontalGroup(
            cardDoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblDoneTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDoneValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDoneDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cardDoneLayout.setVerticalGroup(
            cardDoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDoneLayout.createSequentialGroup()
                .addComponent(lblDoneTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(lblDoneValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblDoneDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlCards.add(cardDone);

        cardCancelled.setBackground(new java.awt.Color(30, 33, 38));
        cardCancelled.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        lblCancelledTitle.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblCancelledTitle.setForeground(new java.awt.Color(160, 166, 174));
        lblCancelledTitle.setText("CANCELLATION");

        lblCancelledValue.setFont(new java.awt.Font("SansSerif", 1, 32));
        lblCancelledValue.setForeground(new java.awt.Color(190, 132, 82));
        lblCancelledValue.setText("0");

        lblCancelledDesc.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblCancelledDesc.setForeground(new java.awt.Color(160, 166, 174));
        lblCancelledDesc.setText("Cancelled queue");

        javax.swing.GroupLayout cardCancelledLayout = new javax.swing.GroupLayout(cardCancelled);
        cardCancelled.setLayout(cardCancelledLayout);
        cardCancelledLayout.setHorizontalGroup(
            cardCancelledLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardCancelledLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblCancelledTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCancelledValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCancelledDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cardCancelledLayout.setVerticalGroup(
            cardCancelledLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardCancelledLayout.createSequentialGroup()
                .addComponent(lblCancelledTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(lblCancelledValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblCancelledDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlCards.add(cardCancelled);

        add(pnlCards, java.awt.BorderLayout.NORTH);

        pnlChart.setBackground(new java.awt.Color(30, 33, 38));
        pnlChart.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        pnlChart.setLayout(new java.awt.BorderLayout(0, 16));
        lblChartTitle.setFont(new java.awt.Font("SansSerif", 1, 16));
        lblChartTitle.setForeground(new java.awt.Color(213, 166, 111));
        lblChartTitle.setText("Peak Hours \u00b7 Historical registration data");
        pnlChart.add(lblChartTitle, java.awt.BorderLayout.NORTH);

        pnlBarsHolder.setOpaque(false);
        pnlBarsHolder.setLayout(new java.awt.BorderLayout());
        pnlBars.setOpaque(false);
        pnlBars.setLayout(new java.awt.GridLayout(0, 1, 0, 9));
        pnlBarsHolder.add(pnlBars, java.awt.BorderLayout.NORTH);

        pnlChart.add(pnlBarsHolder, java.awt.BorderLayout.CENTER);

        lblChartNote.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblChartNote.setForeground(new java.awt.Color(160, 166, 174));
        lblChartNote.setText("Analitik jam ramai berasal dari waktu registrasi yang tersimpan, bukan prediksi AI.");
        pnlChart.add(lblChartNote, java.awt.BorderLayout.SOUTH);

        add(pnlChart, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cardCancelled;
    private javax.swing.JPanel cardDone;
    private javax.swing.JPanel cardVisits;
    private javax.swing.JLabel lblCancelledDesc;
    private javax.swing.JLabel lblCancelledTitle;
    private javax.swing.JLabel lblCancelledValue;
    private javax.swing.JLabel lblChartNote;
    private javax.swing.JLabel lblChartTitle;
    private javax.swing.JLabel lblDoneDesc;
    private javax.swing.JLabel lblDoneTitle;
    private javax.swing.JLabel lblDoneValue;
    private javax.swing.JLabel lblVisitsDesc;
    private javax.swing.JLabel lblVisitsTitle;
    private javax.swing.JLabel lblVisitsValue;
    private javax.swing.JPanel pnlBars;
    private javax.swing.JPanel pnlBarsHolder;
    private javax.swing.JPanel pnlCards;
    private javax.swing.JPanel pnlChart;
    // End of variables declaration//GEN-END:variables
}
