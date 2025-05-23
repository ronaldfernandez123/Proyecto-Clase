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
import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        setLocationRelativeTo(null);
        this.documentoUsuario = documentoUsuario;
        instanciaActiva = this;
        cargarReservasEnTabla();
        setLocationRelativeTo(null);// si quieres cargar al iniciar
    }
    
    // Variable que almacena el documento del usuario logueado
private String documentoUsuario;

// Método para cargar reservas en la tabla
public void cargarReservasEnTabla() {
    ReservaDAO dao = new ReservaDAO();
    List<Reserva> lista = dao.cargarReservas();

    DefaultTableModel modelo = (DefaultTableModel) tablaReservas.getModel();
    modelo.setRowCount(0); // Limpiar la tabla

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    for (Reserva reserva : lista) {
        if (reserva == null) continue;

Date checkIn = reserva.getCheckIn();
Date checkOut = reserva.getCheckOut();

String checkInStr = (checkIn != null) ? formato.format(checkIn) : "Sin fecha";
String checkOutStr = (checkOut != null) ? formato.format(checkOut) : "Sin fecha";


        modelo.addRow(new Object[]{
reserva.getNombres(),
    reserva.getApellidos(),
    reserva.getTipoIdentificacion(),
    reserva.getDocumento(),
    reserva.getLugar(),
      String.valueOf(reserva.getCantidadPersonas()),
    reserva.getHabitacion(),
    reserva.getHabitaciones(),
     checkInStr,
    checkOutStr
        });
    }
}




// Método para eliminar una reserva seleccionada
private void eliminarReservaSeleccionada() {
    int fila = tablaReservas.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Selecciona una reserva para eliminar.");
        return;
    }

    DefaultTableModel modelo = (DefaultTableModel) tablaReservas.getModel();
    String documento = (String) modelo.getValueAt(fila, 3);
    String checkIn = (String) modelo.getValueAt(fila, 5);

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

private void editarReservaSeleccionada() {
    int fila = tablaReservas.getSelectedRow();

    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione una reserva para editar.");
        return;
    }

    DefaultTableModel modelo = (DefaultTableModel) tablaReservas.getModel();

    // Obtener datos actuales de la fila seleccionada
    String nombresAntiguo = modelo.getValueAt(fila, 0).toString();
    String apellidosAntiguo = modelo.getValueAt(fila, 1).toString();
    String tipoIDAntiguo = modelo.getValueAt(fila, 2).toString();
    String documentoAntiguo = modelo.getValueAt(fila, 3).toString();
    String lugarAntiguo = modelo.getValueAt(fila, 4).toString();
    String habitacionAntigua = modelo.getValueAt(fila, 5).toString();
    String checkInAntiguoStr = modelo.getValueAt(fila, 6).toString();
    String checkOutAntiguoStr = modelo.getValueAt(fila, 7).toString();
    String cantidadPersonasStr = modelo.getValueAt(fila, 8).toString();
    String habitacionesStr = modelo.getValueAt(fila, 9).toString();

    // Pedir nuevos datos al usuario (puedes adaptar a tus componentes de entrada)
    String nuevosNombres = JOptionPane.showInputDialog(this, "Editar nombres:", nombresAntiguo);
    String nuevosApellidos = JOptionPane.showInputDialog(this, "Editar apellidos:", apellidosAntiguo);
    String nuevoTipoID = JOptionPane.showInputDialog(this, "Editar tipo de ID:", tipoIDAntiguo);
    String nuevoDocumento = JOptionPane.showInputDialog(this, "Editar documento:", documentoAntiguo);
    String nuevoLugar = JOptionPane.showInputDialog(this, "Editar lugar:", lugarAntiguo);
    String nuevaHabitacion = JOptionPane.showInputDialog(this, "Editar habitación:", habitacionAntigua);
    String nuevoCheckInStr = JOptionPane.showInputDialog(this, "Editar Check-In (dd/MM/yyyy):", checkInAntiguoStr);
    String nuevoCheckOutStr = JOptionPane.showInputDialog(this, "Editar Check-Out (dd/MM/yyyy):", checkOutAntiguoStr);
    String nuevaCantidadPersonasStr = JOptionPane.showInputDialog(this, "Editar cantidad de personas:", cantidadPersonasStr);
    String nuevaHabitacionesStr = JOptionPane.showInputDialog(this, "Editar habitaciones:", habitacionesStr);

    if (nuevosNombres != null && nuevosApellidos != null && nuevoTipoID != null &&
        nuevoDocumento != null && nuevoLugar != null && nuevaHabitacion != null &&
        nuevoCheckInStr != null && nuevoCheckOutStr != null &&
        nuevaCantidadPersonasStr != null && nuevaHabitacionesStr != null) {

        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            Date nuevoCheckIn = formatoFecha.parse(nuevoCheckInStr);
            Date nuevoCheckOut = formatoFecha.parse(nuevoCheckOutStr);

            int nuevaCantidadPersonas = Integer.parseInt(nuevaCantidadPersonasStr);
            int nuevaHabitaciones = Integer.parseInt(nuevaHabitacionesStr);

            Reserva reservaActualizada = new Reserva(
                nuevosNombres,
                nuevosApellidos,
                nuevoTipoID,
                nuevoDocumento,
                nuevoLugar,
                nuevaHabitacion,
                nuevoCheckIn,
                nuevoCheckOut,
                nuevaCantidadPersonas,
                nuevaHabitaciones
            );

            GestorReservas gestor = new GestorReservas();

            // Asegúrate que editarReserva recibe el documento y fecha anterior para identificar la reserva a modificar
            Date checkInAntiguo = formatoFecha.parse(checkInAntiguoStr);
            gestor.editarReserva(documentoAntiguo, checkInAntiguo, reservaActualizada);

            cargarReservasEnTabla();

            JOptionPane.showMessageDialog(this, "Reserva actualizada correctamente.");

        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Error al convertir las fechas. Use formato dd/MM/yyyy.");
            ex.printStackTrace();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad de personas o habitaciones inválida. Deben ser números.");
            ex.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(this, "Edición cancelada o datos incompletos.");
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
        jPanel2 = new javax.swing.JPanel();
        cbxTipoId = new javax.swing.JComboBox<>();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtDocumento = new javax.swing.JTextField();
        cbxLugar = new javax.swing.JComboBox<>();
        txtHabitaciones = new javax.swing.JTextField();
        jCalendarCheckIn = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jCalendarCheckOut = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbxNumeroHabitacion = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtCantidadPersonas = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Guardar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaReservas = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        jLabel1.setText("Mis Reservas");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        cbxTipoId.setBackground(new java.awt.Color(204, 204, 204));
        cbxTipoId.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        cbxTipoId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "C.C", "N.I.T", "Pasaporte", "Licencia De Conducir" }));
        jPanel2.add(cbxTipoId);
        cbxTipoId.setBounds(20, 120, 180, 26);

        txtNombres.setBackground(new java.awt.Color(204, 204, 204));
        txtNombres.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jPanel2.add(txtNombres);
        txtNombres.setBounds(20, 40, 180, 23);

        txtApellidos.setBackground(new java.awt.Color(204, 204, 204));
        txtApellidos.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jPanel2.add(txtApellidos);
        txtApellidos.setBounds(20, 80, 180, 23);

        txtDocumento.setBackground(new java.awt.Color(204, 204, 204));
        txtDocumento.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jPanel2.add(txtDocumento);
        txtDocumento.setBounds(20, 170, 180, 23);

        cbxLugar.setBackground(new java.awt.Color(204, 204, 204));
        cbxLugar.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        cbxLugar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Habitacion", "Penthouse", "Salon de Eventos", "Restaurante", " " }));
        jPanel2.add(cbxLugar);
        cbxLugar.setBounds(20, 230, 180, 20);

        txtHabitaciones.setBackground(new java.awt.Color(204, 204, 204));
        txtHabitaciones.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jPanel2.add(txtHabitaciones);
        txtHabitaciones.setBounds(320, 80, 200, 20);

        jCalendarCheckIn.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.add(jCalendarCheckIn);
        jCalendarCheckIn.setBounds(320, 170, 200, 22);

        jLabel7.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Check In:");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(320, 150, 80, 20);

        jLabel8.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Check Out:");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(320, 200, 90, 20);

        jCalendarCheckOut.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.add(jCalendarCheckOut);
        jCalendarCheckOut.setBounds(320, 220, 200, 22);

        jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(20, 20, 100, 20);

        jLabel4.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Apellido:");
        jLabel4.setToolTipText("");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(20, 60, 80, 20);

        jLabel5.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tipo ID:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(20, 100, 70, 20);

        jLabel6.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Numero ID:");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(20, 150, 110, 20);

        jLabel9.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Lugar reserva:");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(20, 200, 140, 25);

        jLabel10.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Numero de Habitaciones:");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(320, 60, 190, 20);

        jLabel11.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Numero de Habitacion:");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(320, 100, 180, 20);

        cbxNumeroHabitacion.setBackground(new java.awt.Color(204, 204, 204));
        cbxNumeroHabitacion.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        cbxNumeroHabitacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", " " }));
        jPanel2.add(cbxNumeroHabitacion);
        cbxNumeroHabitacion.setBounds(320, 120, 200, 26);

        jLabel12.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Cantidad de Personas:");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(320, 20, 150, 20);

        txtCantidadPersonas.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.add(txtCantidadPersonas);
        txtCantidadPersonas.setBounds(320, 40, 200, 22);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Fondo (4).jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(0, -20, 540, 310);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 40, 530, 260);

        Guardar.setBackground(new java.awt.Color(0, 255, 153));
        Guardar.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        jPanel1.add(Guardar);
        Guardar.setBounds(290, 310, 90, 27);

        jButton2.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 51, 51));
        jButton2.setText("Vaciar");
        jPanel1.add(jButton2);
        jButton2.setBounds(190, 310, 90, 30);

        btnEliminar.setBackground(new java.awt.Color(255, 102, 102));
        btnEliminar.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar Reserva");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(51, 204, 255));
        btnActualizar.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(51, 204, 255));
        btnEditar.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 204, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Menu principal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tablaReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido", "Tipo ID", "Numero ID", "Lugar reserva", "Personas", "Numero de Habitacion", "Numero habitacion", "Check in", "Check out"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaReservasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaReservas);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reservas.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(142, 142, 142)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jLabel13)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btnEditar)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        editarReservaSeleccionada();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        // TODO add your handling code here:
        Gson gson = new Gson();

        // Obtener datos del formulario
        String nombres = txtNombres.getText();
        String apellidos = txtApellidos.getText();
        String tipoIdentificacion = (String) cbxTipoId.getSelectedItem();
        String documento = txtDocumento.getText();
        String lugar = (String) cbxLugar.getSelectedItem();
        Date fechaCheckIn = jCalendarCheckIn.getDate();
        Date fechaCheckOut = jCalendarCheckOut.getDate();
        int cantidadPersonas = Integer.parseInt(txtCantidadPersonas.getText());
        int habitaciones = Integer.parseInt(txtHabitaciones.getText());
        String numeroHabitacionStr = (String) cbxNumeroHabitacion.getSelectedItem();

        // Validar valores positivos
        if (cantidadPersonas <= 0 || habitaciones <= 0) {
            JOptionPane.showMessageDialog(this, "La cantidad de personas y habitaciones debe ser un número positivo.");
            return;
        }

        // Validar máximo de habitaciones según cantidad de personas
        int maxHabitaciones = 0;
        if (cantidadPersonas <= 4) {
            maxHabitaciones = 1;
        } else if (cantidadPersonas <= 8) {
            maxHabitaciones = 2;
        } else if (cantidadPersonas <= 12) {
            maxHabitaciones = 3;
        } else {
            JOptionPane.showMessageDialog(this, "No se permiten reservas para más de 12 personas.");
            return;
        }

        if (habitaciones > maxHabitaciones) {
            JOptionPane.showMessageDialog(this, "Para " + cantidadPersonas + " personas solo se permiten hasta " + maxHabitaciones + " habitaciones.");
            return;
        }

        // Validar que la habitación no esté reservada para las fechas seleccionadas
        ReservaDAO gestor = new ReservaDAO();
        if (gestor.habitacionYaReservada(numeroHabitacionStr, fechaCheckIn, fechaCheckOut)) {
            JOptionPane.showMessageDialog(this, "¡La habitación " + numeroHabitacionStr + " ya está reservada para esas fechas!");
            return;
        }

        // Crear objeto Reserva con todos los datos
        Reserva nuevaReserva = new Reserva(
            nombres,
            apellidos,
            tipoIdentificacion,
            documento,
            lugar,
            numeroHabitacionStr,
            fechaCheckIn,
            fechaCheckOut,
            cantidadPersonas,
            habitaciones
        );

        // Guardar la reserva en archivo JSON
        gestor.agregarReserva(nuevaReserva);

        // Actualizar tabla de reservas si está abierta
        if (MisReservas.instanciaActiva != null) {
            MisReservas.instanciaActiva.cargarReservasEnTabla();
        }

        // Mensaje de éxito
        JOptionPane.showMessageDialog(this, "Reserva guardada exitosamente.");
    }//GEN-LAST:event_GuardarActionPerformed

    private void tablaReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaReservasMouseClicked
        // TODO add your handling code here:
                                                
   int fila = tablaReservas.getSelectedRow();
    if (fila != -1) {
        DefaultTableModel modelor = (DefaultTableModel) tablaReservas.getModel();

        String nombres = safeToString(modelor, fila, 0);
        String apellidos = safeToString(modelor, fila, 1);
        String tipoID = safeToString(modelor, fila, 2);
        String documento = safeToString(modelor, fila, 3);
        String lugar = safeToString(modelor, fila, 4);
        String cantidadPersonasStr = safeToString(modelor, fila, 5);
        String cantidadHabitacionesStr = safeToString(modelor, fila, 6);
        String habitacion = safeToString(modelor, fila, 7);
        String checkInStr = safeToString(modelor, fila, 8);
        String checkOutStr = safeToString(modelor, fila, 9);

        // Asignar valores a campos de texto y combos
        txtNombres.setText(nombres);
        txtApellidos.setText(apellidos);
        cbxTipoId.setSelectedItem(tipoID);
        txtDocumento.setText(documento);
        cbxLugar.setSelectedItem(lugar);
        txtHabitaciones.setText(habitacion);
        txtCantidadPersonas.setText(cantidadPersonasStr);
        cbxNumeroHabitacion.setSelectedItem(cantidadHabitacionesStr);

        // Convertir fechas y asignar a jCalendar
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);

        try {
            if (!checkInStr.isEmpty()) {
                Date checkIn = formato.parse(checkInStr);
                jCalendarCheckIn.setDate(checkIn);
            } else {
                jCalendarCheckIn.setDate(null);
            }

            if (!checkOutStr.isEmpty()) {
                Date checkOut = formato.parse(checkOutStr);
                jCalendarCheckOut.setDate(checkOut);
            } else {
                jCalendarCheckOut.setDate(null);
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Error al convertir las fechas: " + e.getMessage());
            jCalendarCheckIn.setDate(null);
            jCalendarCheckOut.setDate(null);
        }
    }
}

// Método auxiliar para evitar errores al obtener valores
private String safeToString(DefaultTableModel model, int fila, int columna) {
    if (fila >= 0 && fila < model.getRowCount() && columna >= 0 && columna < model.getColumnCount()) {
        Object val = model.getValueAt(fila, columna);
        if (val != null) {
            return val.toString();
        }
    }
    return "";
    }//GEN-LAST:event_tablaReservasMouseClicked

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
    private javax.swing.JButton Guardar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<String> cbxLugar;
    private javax.swing.JComboBox<String> cbxNumeroHabitacion;
    private javax.swing.JComboBox<String> cbxTipoId;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jCalendarCheckIn;
    private com.toedter.calendar.JDateChooser jCalendarCheckOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaReservas;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCantidadPersonas;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtHabitaciones;
    private javax.swing.JTextField txtNombres;
    // End of variables declaration//GEN-END:variables

    
}
