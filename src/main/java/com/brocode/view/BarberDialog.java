package com.brocode.view;


import com.brocode.model.Barber;

/** Dialog tambah/edit barber (modal). Desain bisa diedit lewat tab Design. */
public class BarberDialog extends javax.swing.JDialog {

    private boolean confirmed;

    public BarberDialog(java.awt.Frame parent, String title, Barber existing) {
        super(parent, true);
        initComponents();
        setTitle(title);
        getContentPane().setBackground(Theme.PANEL);
        Theme.styleField(txtName);
        Theme.styleCheckBox(chkActive);
        Theme.styleButton(btnSave, true);
        Theme.styleButton(btnCancel, false);
        if (existing != null) {
            txtName.setText(existing.getName());
            chkActive.setSelected(existing.isActive());
        }
        getRootPane().setDefaultButton(btnSave);
        setLocationRelativeTo(parent);
    }

    public boolean isConfirmed() { return confirmed; }
    public String getBarberName() { return txtName.getText().trim(); }
    public boolean isBarberActive() { return chkActive.isSelected(); }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlForm = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblAvailability = new javax.swing.JLabel();
        chkActive = new javax.swing.JCheckBox();
        pnlButtons = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new java.awt.BorderLayout());
        pnlForm.setBackground(new java.awt.Color(30, 33, 38));
        pnlForm.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 18, 8, 18));
        lblName.setFont(new java.awt.Font("SansSerif", 1, 12));
        lblName.setForeground(new java.awt.Color(239, 237, 232));
        lblName.setText("Name");

        txtName.setBackground(new java.awt.Color(39, 43, 49));
        txtName.setForeground(new java.awt.Color(239, 237, 232));
        txtName.setColumns(20);
        txtName.setText("");

        lblAvailability.setFont(new java.awt.Font("SansSerif", 1, 12));
        lblAvailability.setForeground(new java.awt.Color(239, 237, 232));
        lblAvailability.setText("Availability");

        chkActive.setBackground(new java.awt.Color(30, 33, 38));
        chkActive.setForeground(new java.awt.Color(239, 237, 232));
        chkActive.setSelected(true);
        chkActive.setText("Active");

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(chkActive, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)))
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkActive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        if (getBarberName().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Nama barber wajib diisi.", "Brocode", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        confirmed = true;
        dispose();
    }//GEN-LAST:event_btnSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox chkActive;
    private javax.swing.JLabel lblAvailability;
    private javax.swing.JLabel lblName;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
