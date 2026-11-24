package com.brocode.view;


import com.brocode.model.QueueHistory;
import com.brocode.service.DemoDataStore;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;

/** Halaman History perubahan status antrean. */
public class HistoryPanel extends javax.swing.JPanel implements Refreshable {

    private final DemoDataStore db;

    public HistoryPanel(DemoDataStore db) {
        this.db = db;
        initComponents();
        Theme.styleTable(tblHistory);
        Theme.applyAlternatingRows(tblHistory);
        Theme.styleScroll(scrollTable);
        refresh();
    }

    @Override
    public void refresh() {
        DefaultTableModel m = (DefaultTableModel) tblHistory.getModel();
        m.setRowCount(0);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        for (QueueHistory h : db.history) {
            m.addRow(new Object[]{h.queueNumber(), h.oldStatus() == null ? "-" : h.oldStatus(), h.newStatus(), h.changedAt().format(fmt)});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollTable = new javax.swing.JScrollPane();
        tblHistory = new javax.swing.JTable();

        setBackground(new java.awt.Color(22, 24, 27));
        setLayout(new java.awt.BorderLayout());
        scrollTable.setBackground(new java.awt.Color(30, 33, 38));
        tblHistory.setBackground(new java.awt.Color(30, 33, 38));
        tblHistory.setForeground(new java.awt.Color(239, 237, 232));
        tblHistory.setGridColor(new java.awt.Color(57, 62, 70));
        tblHistory.setRowHeight(38);
        tblHistory.setSelectionBackground(new java.awt.Color(91, 58, 39));
        tblHistory.setSelectionForeground(new java.awt.Color(239, 237, 232));
        tblHistory.setFillsViewportHeight(true);
        tblHistory.setShowVerticalLines(false);
        tblHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Queue No.", "Old Status", "New Status", "Changed At"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollTable.setViewportView(tblHistory);

        add(scrollTable, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTable tblHistory;
    // End of variables declaration//GEN-END:variables
}
