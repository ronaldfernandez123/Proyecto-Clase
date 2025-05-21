/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import DAO.ReservaDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import Datos.GestorReservas;
import Modelo.Reserva;
import java.util.List;


/**
 *
 * @author Administrator
 */
public class MisReservas extends javax.swing.JFrame {
    DefaultTableModel model = new DefaultTableModel();

    /**
     * Creates new form MisReservas
     */
    public MisReservas() {
        initComponents();
          this.documentoUsuario = documentoUsuario;
        instanciaActiva = this;
    }
    
    // Variable que almacena el documento del usuario logueado
private String documentoUsuario;

// Método para cargar reservas en la tabla
public void cargarReservasEnTabla() {
    model.setColumnIdentifiers(new Object[]{"Nombre","Apellido","Tipo ID","Numero ID","Lugar reserva","Check in","Check out"});
    model.setRowCount(0);
    ReservaDAO reservaDAO = new ReservaDAO();
    List<Reserva> lista = reservaDAO.listarReservas();

    for (Reserva r : lista) {
        model.addRow(new Object[]{
            r.getDocumento(),
            r.getNombres(),
            r.getCheckIn(),
            r.getCheckOut(),
            r.getLugar(),
            r.getApellidos(),
            r.getTipoIdentificacion(),
            r.getMotivo()
               
        });
        tablaReservas.setModel(model);
    }
}


// Método para eliminar una reserva seleccionada
private void eliminarReservaSeleccionada() {
    int fila = tablaReservas.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Selecciona una reserva para eliminar.");
        return;
    }

    String documento = (String) tablaReservas.getValueAt(fila, 3); // Documento
    String checkIn = (String) tablaReservas.getValueAt(fila, 6);   // Check-In

    int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar esta reserva?", "Confirmar", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        GestorReservas gestor = new GestorReservas();
        if (gestor.eliminarReserva(documento, checkIn)) {
            JOptionPane.showMessageDialog(this, "Reserva eliminada.");
            cargarReservasEnTabla(); // Recargar la tabla
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar.");
        }
    }
}

// Método para editar una reserva seleccionada
private void editarReservaSeleccionada() {
    int fila = tablaReservas.getSelectedRow();

    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione una reserva para editar.");
        return;
    }

    DefaultTableModel modelo = (DefaultTableModel) tablaReservas.getModel();

           String nombresAntiguo = model.getValueAt(fila, 0).toString();
        String apellidosAntiguo = model.getValueAt(fila, 1).toString();
        String tipoIDAntiguo = model.getValueAt(fila, 2).toString();
        String documentoAntiguo = model.getValueAt(fila, 3).toString();
        String lugarAntiguo = model.getValueAt(fila, 4).toString();
        String checkInAntiguo = model.getValueAt(fila, 5).toString();
        String checkOutAntiguo = model.getValueAt(fila, 6).toString();

        String nuevosNombres = JOptionPane.showInputDialog(this, "Editar nombres:", nombresAntiguo);
        String nuevosApellidos = JOptionPane.showInputDialog(this, "Editar apellidos:", apellidosAntiguo);
        String nuevoTipoID = JOptionPane.showInputDialog(this, "Editar tipo de ID:", tipoIDAntiguo);
        String nuevoDocumento = JOptionPane.showInputDialog(this, "Editar documento:", documentoAntiguo);
        String nuevoLugar = JOptionPane.showInputDialog(this, "Editar lugar:", lugarAntiguo);
        String nuevoCheckIn = JOptionPane.showInputDialog(this, "Editar Check-In:", checkInAntiguo);
        String nuevoCheckOut = JOptionPane.showInputDialog(this, "Editar Check-Out:", checkOutAntiguo);

        if (nuevosNombres != null && nuevosApellidos != null && nuevoTipoID != null &&
            nuevoDocumento != null && nuevoLugar != null && nuevoCheckIn != null &&
            nuevoCheckOut != null) {

            Reserva reservaActualizada = new Reserva(nuevosNombres, nuevosApellidos, nuevoTipoID,
                nuevoDocumento, nuevoLugar, "", nuevoCheckIn, nuevoCheckOut);

            GestorReservas.editarReserva(documentoAntiguo, checkInAntiguo, reservaActualizada);
            cargarReservasEnTabla();
            JOptionPane.showMessageDialog(this, "Reserva actualizada.");
        }
    }


  public static MisReservas instanciaActiva = null; // variable estática
  private void formWindowClosing(java.awt.event.WindowEvent evt) {                                   
    instanciaActiva = null;
}





    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaReservas = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setText("Mis Reservas");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        tablaReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido", "Tipo ID", "Numero ID", "Lugar reserva", "Check in", "Check out"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaReservas);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(0, 0, 560, 340);

        btnEliminar.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnEliminar.setText("Eliminar Reserva");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnActualizar.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnEditar.setText("Editar");

        jButton1.setText("Menu principal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(249, 249, 249))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(btnEliminar)
                        .addGap(47, 47, 47)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnActualizar)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnActualizar)
                    .addComponent(btnEditar))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        eliminarReservaSeleccionada();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        cargarReservasEnTabla();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                new Principal().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MisReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MisReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MisReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MisReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MisReservas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaReservas;
    // End of variables declaration//GEN-END:variables

    
}
