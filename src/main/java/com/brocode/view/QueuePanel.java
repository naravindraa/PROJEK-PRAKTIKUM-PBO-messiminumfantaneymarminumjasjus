package com.brocode.view;


import com.brocode.model.*;
import com.brocode.service.DemoDataStore;
import com.brocode.service.QueueService;
import javax.swing.table.DefaultTableModel;

/** Halaman Queue. Dialog pendaftaran antrean ada di QueueDialog. */
public class QueuePanel extends javax.swing.JPanel implements Refreshable {

    private final DemoDataStore db;
    private final QueueService queueService;

    public QueuePanel(DemoDataStore db, QueueService queueService) {
        this.db = db;
        this.queueService = queueService;
        initComponents();
        Theme.styleTable(tblQueue);
        Theme.styleScroll(scrollTable);
        tblQueue.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Theme.styleButton(btnNew, true);
        Theme.styleButton(btnAssign, false);
        Theme.styleButton(btnStart, false);
        Theme.styleButton(btnComplete, false);
        Theme.styleButton(btnCancel, false);
        refresh();
    }

    @Override
    public void refresh() {
        DefaultTableModel m = (DefaultTableModel) tblQueue.getModel();
        m.setRowCount(0);
        for (QueueEntry q : db.queues) {
            m.addRow(new Object[]{q.getNumber(), q.getCustomer().getName(), q.getService().getName(),
                q.getBarber() == null ? "-" : q.getBarber().getName(), q.getDurationSnapshot() + " min", q.getStatus(),
                q.getStatus().equals("Waiting") ? queueService.estimatedWait(q.getBarber()) + " min" : "-"});
        }
    }

    private java.awt.Frame frame() {
        return (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this);
    }

    private void warn(String message) {
        javax.swing.JOptionPane.showMessageDialog(this, message, "Brocode", javax.swing.JOptionPane.WARNING_MESSAGE);
    }

    private QueueEntry selectedQueue() {
        int r = tblQueue.getSelectedRow();
        if (r < 0) {
            warn("Pilih antrean terlebih dahulu.");
            return null;
        }
        return db.queues.get(r);
    }

    private void changeSelected(String status) {
        QueueEntry q = selectedQueue();
        if (q == null) return;
        if (status.equals("In Progress") && !q.getStatus().equals("Waiting")) { warn("Hanya antrean Waiting yang dapat dimulai."); return; }
        if (status.equals("Completed") && !q.getStatus().equals("In Progress")) { warn("Hanya antrean In Progress yang dapat diselesaikan."); return; }
        if (status.equals("Cancelled") && !(q.getStatus().equals("Waiting") || q.getStatus().equals("In Progress"))) { warn("Status ini tidak dapat dibatalkan."); return; }
        queueService.changeStatus(q, status);
        refresh();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlActions = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnAssign = new javax.swing.JButton();
        btnStart = new javax.swing.JButton();
        btnComplete = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        scrollTable = new javax.swing.JScrollPane();
        tblQueue = new javax.swing.JTable();

        setBackground(new java.awt.Color(22, 24, 27));
        setLayout(new java.awt.BorderLayout(0, 14));
        pnlActions.setOpaque(false);
        pnlActions.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 0));
        btnNew.setBackground(new java.awt.Color(91, 58, 39));
        btnNew.setFont(new java.awt.Font("SansSerif", 1, 12));
        btnNew.setForeground(new java.awt.Color(239, 237, 232));
        btnNew.setFocusPainted(false);
        btnNew.setText(" + New Queue");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        pnlActions.add(btnNew);

        btnAssign.setBackground(new java.awt.Color(39, 43, 49));
        btnAssign.setFont(new java.awt.Font("SansSerif", 1, 12));
        btnAssign.setForeground(new java.awt.Color(239, 237, 232));
        btnAssign.setFocusPainted(false);
        btnAssign.setText("Assign Recommended");
        btnAssign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssignActionPerformed(evt);
            }
        });
        pnlActions.add(btnAssign);

        btnStart.setBackground(new java.awt.Color(39, 43, 49));
        btnStart.setFont(new java.awt.Font("SansSerif", 1, 12));
        btnStart.setForeground(new java.awt.Color(239, 237, 232));
        btnStart.setFocusPainted(false);
        btnStart.setText("Start Service");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        pnlActions.add(btnStart);

        btnComplete.setBackground(new java.awt.Color(39, 43, 49));
        btnComplete.setFont(new java.awt.Font("SansSerif", 1, 12));
        btnComplete.setForeground(new java.awt.Color(239, 237, 232));
        btnComplete.setFocusPainted(false);
        btnComplete.setText("Complete");
        btnComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompleteActionPerformed(evt);
            }
        });
        pnlActions.add(btnComplete);

        btnCancel.setBackground(new java.awt.Color(39, 43, 49));
        btnCancel.setFont(new java.awt.Font("SansSerif", 1, 12));
        btnCancel.setForeground(new java.awt.Color(239, 237, 232));
        btnCancel.setFocusPainted(false);
        btnCancel.setText("Cancel Queue");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        pnlActions.add(btnCancel);

        add(pnlActions, java.awt.BorderLayout.NORTH);

        scrollTable.setBackground(new java.awt.Color(30, 33, 38));
        tblQueue.setBackground(new java.awt.Color(30, 33, 38));
        tblQueue.setForeground(new java.awt.Color(239, 237, 232));
        tblQueue.setGridColor(new java.awt.Color(57, 62, 70));
        tblQueue.setRowHeight(38);
        tblQueue.setSelectionBackground(new java.awt.Color(91, 58, 39));
        tblQueue.setSelectionForeground(new java.awt.Color(239, 237, 232));
        tblQueue.setFillsViewportHeight(true);
        tblQueue.setShowVerticalLines(false);
        tblQueue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Queue No.", "Customer", "Service", "Barber", "Duration", "Status", "ETA Start"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollTable.setViewportView(tblQueue);

        add(scrollTable, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        if (db.customers.isEmpty() || db.services.isEmpty()) {
            warn("Tambahkan customer dan service terlebih dahulu.");
            return;
        }
        QueueDialog d = new QueueDialog(frame(), db, queueService);
        d.setVisible(true);
        if (d.isConfirmed()) {
            ServiceItem sv = d.getSelectedService();
            QueueEntry q = new QueueEntry(db.nextQueueId(), queueService.nextQueueNumber(), d.getSelectedCustomer(), sv,
                    d.getSelectedBarber(), sv.getDurationMin(), "Waiting", java.time.LocalDateTime.now());
            db.queues.add(q);
            db.history.add(new QueueHistory(q.getNumber(), null, "Waiting", java.time.LocalDateTime.now()));
            refresh();
        }
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnAssignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignActionPerformed
        QueueEntry q = selectedQueue();
        if (q == null) return;
        Barber rec = queueService.recommendBarber();
        if (rec != null) {
            q.setBarber(rec);
            refresh();
        }
    }//GEN-LAST:event_btnAssignActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        changeSelected("In Progress");
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompleteActionPerformed
        changeSelected("Completed");
    }//GEN-LAST:event_btnCompleteActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        changeSelected("Cancelled");
    }//GEN-LAST:event_btnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAssign;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnComplete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnStart;
    private javax.swing.JPanel pnlActions;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTable tblQueue;
    // End of variables declaration//GEN-END:variables
}
