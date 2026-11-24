package com.brocode.view;


import com.brocode.model.*;
import com.brocode.service.DemoDataStore;
import com.brocode.service.QueueService;
import javax.swing.DefaultComboBoxModel;

/** Dialog pendaftaran antrean baru (modal). Isi combo box dimuat dari data di constructor. */
public class QueueDialog extends javax.swing.JDialog {

    private boolean confirmed;

    public QueueDialog(java.awt.Frame parent, DemoDataStore db, QueueService queueService) {
        super(parent, true);
        initComponents();
        setTitle("Register Queue");
        getContentPane().setBackground(Theme.PANEL);
        cmbCustomer.setModel(new DefaultComboBoxModel<>(db.customers.toArray(new Customer[0])));
        cmbService.setModel(new DefaultComboBoxModel<>(db.services.toArray(new ServiceItem[0])));
        cmbBarber.setModel(new DefaultComboBoxModel<>(db.barbers.stream().filter(Barber::isActive).toArray(Barber[]::new)));
        if (cmbBarber.getItemCount() > 0) cmbBarber.setSelectedItem(queueService.recommendBarber());
        Theme.styleCombo(cmbCustomer);
        Theme.styleCombo(cmbService);
        Theme.styleCombo(cmbBarber);
        Theme.styleButton(btnSave, true);
        Theme.styleButton(btnCancel, false);
        getRootPane().setDefaultButton(btnSave);
        setLocationRelativeTo(parent);
    }

    public boolean isConfirmed() { return confirmed; }
    public Customer getSelectedCustomer() { return (Customer) cmbCustomer.getSelectedItem(); }
    public ServiceItem getSelectedService() { return (ServiceItem) cmbService.getSelectedItem(); }
    public Barber getSelectedBarber() { return (Barber) cmbBarber.getSelectedItem(); }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlForm = new javax.swing.JPanel();
        lblCustomer = new javax.swing.JLabel();
        cmbCustomer = new javax.swing.JComboBox<>();
        lblService = new javax.swing.JLabel();
        cmbService = new javax.swing.JComboBox<>();
        lblBarber = new javax.swing.JLabel();
        cmbBarber = new javax.swing.JComboBox<>();
        pnlButtons = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new java.awt.BorderLayout());
        pnlForm.setBackground(new java.awt.Color(30, 33, 38));
        pnlForm.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 18, 8, 18));
        lblCustomer.setFont(new java.awt.Font("SansSerif", 1, 12));
        lblCustomer.setForeground(new java.awt.Color(239, 237, 232));
        lblCustomer.setText("Customer");

        cmbCustomer.setBackground(new java.awt.Color(39, 43, 49));
        cmbCustomer.setForeground(new java.awt.Color(239, 237, 232));

        lblService.setFont(new java.awt.Font("SansSerif", 1, 12));
        lblService.setForeground(new java.awt.Color(239, 237, 232));
        lblService.setText("Service");

        cmbService.setBackground(new java.awt.Color(39, 43, 49));
        cmbService.setForeground(new java.awt.Color(239, 237, 232));

        lblBarber.setFont(new java.awt.Font("SansSerif", 1, 12));
        lblBarber.setForeground(new java.awt.Color(239, 237, 232));
        lblBarber.setText("Recommended Barber");

        cmbBarber.setBackground(new java.awt.Color(39, 43, 49));
        cmbBarber.setForeground(new java.awt.Color(239, 237, 232));

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBarber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(cmbService, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(cmbBarber, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)))
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBarber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBarber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        getContentPane().add(pnlForm, java.awt.BorderLayout.CENTER);

        pnlButtons.setBackground(new java.awt.Color(30, 33, 38));
        pnlButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 10));
        btnCancel.setBackground(new java.awt.Color(39, 43, 49));
        btnCancel.setFont(new java.awt.Font("SansSerif", 1, 12));
        btnCancel.setForeground(new java.awt.Color(239, 237, 232));
        btnCancel.setFocusPainted(false);
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        pnlButtons.add(btnCancel);

        btnSave.setBackground(new java.awt.Color(91, 58, 39));
        btnSave.setFont(new java.awt.Font("SansSerif", 1, 12));
        btnSave.setForeground(new java.awt.Color(239, 237, 232));
        btnSave.setFocusPainted(false);
        btnSave.setText("OK");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        pnlButtons.add(btnSave);

        getContentPane().add(pnlButtons, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        confirmed = true;
        dispose();
    }//GEN-LAST:event_btnSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<com.brocode.model.Barber> cmbBarber;
    private javax.swing.JComboBox<com.brocode.model.Customer> cmbCustomer;
    private javax.swing.JComboBox<com.brocode.model.ServiceItem> cmbService;
    private javax.swing.JLabel lblBarber;
    private javax.swing.JLabel lblCustomer;
    private javax.swing.JLabel lblService;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlForm;
    // End of variables declaration//GEN-END:variables
}
