package com.brocode.view;


import com.brocode.service.DemoDataStore;
import com.brocode.model.*;
import javax.swing.table.DefaultTableModel;

/** Halaman Barbers (tabel + tombol CRUD). Form input ada di BarberDialog. */
public class BarbersPanel extends javax.swing.JPanel implements Refreshable {

    private final DemoDataStore db;

    public BarbersPanel(DemoDataStore db) {
        this.db = db;
        initComponents();
        Theme.styleTable(tblData);
        Theme.styleScroll(scrollTable);
        Theme.styleButton(btnAdd, true);
        Theme.styleButton(btnEdit, false);
        Theme.styleButton(btnDelete, false);
        tblData.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        refresh();
    }

    @Override
    public void refresh() {
        DefaultTableModel m = (DefaultTableModel) tblData.getModel();
        m.setRowCount(0);
        for (Barber b : db.barbers) m.addRow(new Object[]{b.getId(), b.getName(), b.isActive() ? "Active" : "Inactive"});
    }

    private java.awt.Frame frame() {
        return (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this);
    }

    private int selectedIndex() {
        int r = tblData.getSelectedRow();
        if (r < 0) javax.swing.JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu.", "Brocode", javax.swing.JOptionPane.WARNING_MESSAGE);
        return r;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlActions = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        scrollTable = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();

        setBackground(new java.awt.Color(22, 24, 27));
        setLayout(new java.awt.BorderLayout(0, 14));
        pnlActions.setOpaque(false);
        pnlActions.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 5));
        btnAdd.setBackground(new java.awt.Color(91, 58, 39));
        btnAdd.setFont(new java.awt.Font("SansSerif", 1, 12));
        btnAdd.setForeground(new java.awt.Color(239, 237, 232));
        btnAdd.setFocusPainted(false);
        btnAdd.setText("+ Add Barber");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        pnlActions.add(btnAdd);

        btnEdit.setBackground(new java.awt.Color(39, 43, 49));
        btnEdit.setFont(new java.awt.Font("SansSerif", 1, 12));
        btnEdit.setForeground(new java.awt.Color(239, 237, 232));
        btnEdit.setFocusPainted(false);
        btnEdit.setText("Edit Selected");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        pnlActions.add(btnEdit);

        btnDelete.setBackground(new java.awt.Color(39, 43, 49));
        btnDelete.setFont(new java.awt.Font("SansSerif", 1, 12));
        btnDelete.setForeground(new java.awt.Color(239, 237, 232));
        btnDelete.setFocusPainted(false);
        btnDelete.setText("Delete Selected");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        pnlActions.add(btnDelete);

        add(pnlActions, java.awt.BorderLayout.NORTH);

        scrollTable.setBackground(new java.awt.Color(30, 33, 38));
        tblData.setBackground(new java.awt.Color(30, 33, 38));
        tblData.setForeground(new java.awt.Color(239, 237, 232));
        tblData.setGridColor(new java.awt.Color(57, 62, 70));
        tblData.setRowHeight(38);
        tblData.setSelectionBackground(new java.awt.Color(91, 58, 39));
        tblData.setSelectionForeground(new java.awt.Color(239, 237, 232));
        tblData.setFillsViewportHeight(true);
        tblData.setShowVerticalLines(false);
        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Barber Name", "Availability"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollTable.setViewportView(tblData);

        add(scrollTable, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        BarberDialog d = new BarberDialog(frame(), "Add Barber", null);
        d.setVisible(true);
        if (d.isConfirmed()) {
            db.barbers.add(new Barber(db.nextBarberId(), d.getBarberName(), d.isBarberActive()));
            refresh();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int r = selectedIndex();
        if (r < 0) return;
        Barber b = db.barbers.get(r);
        BarberDialog d = new BarberDialog(frame(), "Edit Barber", b);
        d.setVisible(true);
        if (d.isConfirmed()) {
            b.setName(d.getBarberName());
            b.setActive(d.isBarberActive());
            refresh();
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int r = selectedIndex();
        if (r < 0) return;
        db.barbers.remove(r);
        refresh();
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JPanel pnlActions;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTable tblData;
    // End of variables declaration//GEN-END:variables
}
