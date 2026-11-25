package com.brocode.view;


import com.brocode.model.Customer;

/** Dialog tambah/edit customer (modal). Desain bisa diedit lewat tab Design. */
public class CustomerDialog extends javax.swing.JDialog {

    private boolean confirmed;

    public CustomerDialog(java.awt.Frame parent, String title, Customer existing) {
        super(parent, true);
        initComponents();
        setTitle(title);
        getContentPane().setBackground(Theme.PANEL);
        Theme.styleField(txtName);
        Theme.styleField(txtPhone);
        Theme.styleButton(btnSave, true);
        Theme.styleButton(btnCancel, false);
        if (existing != null) {
            txtName.setText(existing.getName());
            txtPhone.setText(existing.getPhone());
        }
        getRootPane().setDefaultButton(btnSave);
        setLocationRelativeTo(parent);
    }

    public boolean isConfirmed() { return confirmed; }
    public String getCustomerName() { return txtName.getText().trim(); }
    public String getPhone() { return txtPhone.getText().trim(); }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlForm = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblPhone = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        pnlButtons = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlForm.setBackground(new java.awt.Color(30, 33, 38));
        pnlForm.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 18, 8, 18));
        pnlForm.setPreferredSize(new java.awt.Dimension(400, 300));

        lblName.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblName.setForeground(new java.awt.Color(239, 237, 232));
        lblName.setText("Name");

        txtName.setBackground(new java.awt.Color(39, 43, 49));
        txtName.setColumns(20);
        txtName.setForeground(new java.awt.Color(239, 237, 232));

        lblPhone.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblPhone.setForeground(new java.awt.Color(239, 237, 232));
        lblPhone.setText("Phone");

        txtPhone.setBackground(new java.awt.Color(39, 43, 49));
        txtPhone.setColumns(20);
        txtPhone.setForeground(new java.awt.Color(239, 237, 232));

        org.jdesktop.layout.GroupLayout pnlFormLayout = new org.jdesktop.layout.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlFormLayout.createSequentialGroup()
                .add(pnlFormLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lblName)
                    .add(lblPhone))
                .add(12, 12, 12)
                .add(pnlFormLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(txtName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .add(txtPhone, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)))
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlFormLayout.createSequentialGroup()
                .add(pnlFormLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblName)
                    .add(txtName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(12, 12, 12)
                .add(pnlFormLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblPhone)
                    .add(txtPhone, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(pnlForm, java.awt.BorderLayout.CENTER);

        pnlButtons.setBackground(new java.awt.Color(30, 33, 38));
        pnlButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 10));

        btnCancel.setBackground(new java.awt.Color(39, 43, 49));
        btnCancel.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
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
        btnSave.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
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
        if (getCustomerName().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Nama customer wajib diisi.", "Brocode", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        confirmed = true;
        dispose();
    }//GEN-LAST:event_btnSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
